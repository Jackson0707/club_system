package com.example.club_system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.entity.TeacherDatabase;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.repository.TeacherDatabaseDao;
import com.example.club_system.service.ifs.TeacherDatabaseService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherForgotPwdReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginReq;
import com.example.club_system.vo.TeacherLoginRes;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherSearchRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeacherDatabaseServiceImpl implements TeacherDatabaseService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ClubDao clubDao;

	@Autowired
	private TeacherDatabaseDao teacherDatabaseDao;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public BasicRes updatePwd(Integer teacherId, String oldpwd, String newpwd) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(String.valueOf(teacherId)) || !StringUtils.hasText(oldpwd)//
				|| !StringUtils.hasText(newpwd)) {
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// �T�{�b���O�_�w�s�b
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
		Optional<TeacherDatabase> op = teacherDatabaseDao.findById(teacherId);
		// �T�{�b���s�b
		if (op.isEmpty()) {// op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// �q op �����^ TeacherDatabase ��T
		TeacherDatabase teacherDatabase = op.get();
		// ���^�±K�X�çP�_
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches(��l�K�X,�[�K�᪺�K�X):���K�X
		// ��l�K�X: �����O�ϥΪ̿�J���K�X,�ΰѼƤ���oldpwd
		// �[�K�᪺�K�X: �����O�z�L BCryptPasswordEncoder �[�K�L���K�X,�s�b�� DB ��

		if (!encoder.matches(oldpwd, teacherDatabase.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		// �]�w�s�K�X(�[�K�᪺�K�X)
		teacherDatabase.setPwd(encoder.encode(newpwd));
		// �N�s����Ʀs�^ DB
		teacherDatabaseDao.save(teacherDatabase);
		System.out.println(ResMessage.SUCCESS.getMessage());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes createOrUpdate(TeacherDatabaseCreateOrUpdateReq req) {
		if(req.getTeacherId() > 0) {
			 Optional<TeacherDatabase> teacherId = teacherDatabaseDao.findById(req.getTeacherId());
			if(!teacherId.isPresent() ) {
				return new BasicRes(ResMessage.TEACHER_ID_NOT_FOUND.getCode(), 
						ResMessage.TEACHER_ID_NOT_FOUND.getMessage());
			}
			TeacherDatabase teacherData = teacherId.get();
			teacherData.setName(req.getName());
			teacherData.setEmail(req.getEmail());
			teacherData.setStatus(req.getStatus());
			teacherDatabaseDao.save(teacherData);
			return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
		}
		if(req.getTeacherId() == null) {
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		teacherDatabaseDao.save(new TeacherDatabase(req.getStatus(),req.getTeacherId(),req.getClubId(),encoder.encode(req.getPwd()),
				req.getName(),req.getEmail(), req.getType()));
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage()) ;
		
	}

	@Override
	public TeacherSearchRes search(TeacherSearchReq req) {
		
		String name = !StringUtils.hasText(req.getName()) ?"":req.getName();

		String status = !StringUtils.hasText(req.getStatus()) ?"":req.getStatus();

		Integer clubId = req.getClubId();
		
		Integer teacherId = req.getTeacherId();

		return new TeacherSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
				teacherDatabaseDao.findByNameAndStatusAndClubIdAndTeacherId(name, status, clubId, teacherId));
	}

	@Override
	public BasicRes delete(TeacherDeleteReq req) {
		// �Ѽ��ˬd
		if (!CollectionUtils.isEmpty(req.getTeacherIdList())) {
			// �R���ݨ�
			try {
				teacherDatabaseDao.deleteAllById(req.getTeacherIdList());
			} catch (Exception e) {
				// �� deleteAllById ��k���Aid ���Ȥ��s�b�AJPA �|����
				// �]���b�R�����e�A JPA�|���j�a�J�� id �ȡA�Y�S���G�N�|����
				// �ѩ��ڤW�]�S�R������ơA�]���N���ݭn��o��Exception ���B�z
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public TeacherLoginRes login(TeacherLoginReq req) {
		// �T�{�b���O�_�w�s�bm
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
		Optional<TeacherDatabase> clubTeacherId = teacherDatabaseDao.findById(req.getTeacherId());
		// �T�{�b���s�b
		if (clubTeacherId.isEmpty()) {  // op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			return new TeacherLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		// �q op �����^ �Ѯv ��T
		TeacherDatabase teacherDatabase = clubTeacherId.get();

		// �P�_�K�X�O�_���T
		if (!encoder.matches(req.getPwd(), teacherDatabase.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			return new TeacherLoginRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new TeacherLoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),teacherDatabase.getTeacherId()
					);
	}

	@Override
	public BasicRes loginAdmin(Integer teacherId, String pwd) {
		Optional<TeacherDatabase> op = teacherDatabaseDao.findById(teacherId);
		// �T�{�b���s�b
		if (op.isEmpty()) {// op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// �q op �����^ Atm ��T
		TeacherDatabase teacherDatabase = op.get();

		// �P�_�K�X�O�_���T
		if (!encoder.matches(pwd, teacherDatabase.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		if (teacherDatabase.getType() != "�޲z��") {
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// �Ѯv�n�J����o�����Τξǥ͸�T
	@Override 
	public TeacherLoginRes teacherGetClubStudent(TeacherGetStudentReq req) {
		
		// clubTeacherData: ���o�ѮvId���Ҧ���T
		Optional<TeacherDatabase> clubTeacherData = teacherDatabaseDao.findById(req.getTeacherId());
		
		if(clubTeacherData.isEmpty()) {
			return new TeacherLoginRes(ResMessage.TEACHER_ID_NOT_FOUND.getCode(), 
					ResMessage.TEACHER_ID_NOT_FOUND.getMessage());
		}
		
		// clubTeacherData:�̭����ӦѮv���Ҧ���T
		TeacherDatabase teacher = clubTeacherData.get();
		
		// op: �ΦѮv��������Id�h���Ӫ��Ϊ��Ҧ���T
		Optional<Club> op = clubDao.findById(teacher.getClubId());
		
		if(op.isEmpty()) {
			return new TeacherLoginRes(ResMessage.PARAM_CLUB_ID_NOT_EXIST.getCode(), 
					ResMessage.PARAM_CLUB_ID_NOT_EXIST.getMessage());
		}
		
		// clubData: ����Ӫ���Id���Ҧ���T
		Club clubData = op.get();
		
		List<Student> clubStdentList = studentDao.findByClubId(teacher.getClubId());
		
		List<Student> clubStudent = new ArrayList<>(clubStdentList);
		
		return new TeacherLoginRes(ResMessage.SUCCESS.getCode(), 
				ResMessage.SUCCESS.getMessage(),teacher.getName(),teacher.getClubId(), clubData.getName(),
				clubStudent);
	}

	@Override
	public TeacherLoginRes forgotPwd(TeacherForgotPwdReq req) {
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(req.getTeacherId() == null) {
			return new TeacherLoginRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		 Optional<TeacherDatabase> teacherId1 = teacherDatabaseDao.findById(req.getTeacherId());
		if( !teacherId1.isPresent()) {
			return new TeacherLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		TeacherDatabase teacherData = teacherId1.get();
		
		String verificationCodeString = RandomStringUtils.randomNumeric(6);
//        Integer verificationCode = Integer.parseInt(verificationCodeString);
        
		teacherData.setPwd(encoder.encode(verificationCodeString));
		teacherDatabaseDao.save(teacherData);
		sendVerificationEmail(teacherData.getEmail(), verificationCodeString);
		return new TeacherLoginRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
	}

	private void sendVerificationEmail(String email, String verificationCodeString) {
		  SimpleMailMessage message = new SimpleMailMessage();
		     message.setTo(email);
		     message.setSubject("Verify Your Email Address");
		     message.setText("Your verification code is : " + verificationCodeString);
		     
		     try {
		         // Send email
		         javaMailSender.send(message);
		         System.out.println("Email sent successfully.");
		     } catch (MailException e) {
		         System.err.println("Failed to send email: " + e.getMessage());
		         // Handle exception appropriately
		     }
		
	}

	@Override
	public BasicRes createOrUpdateAll(TeacherDatabaseCreateOrUpdateReq req) {
		
		if(req.getTeacherId() > 0) {
			boolean teacherIdExist = teacherDatabaseDao.existsById(req.getTeacherId());
			if(!teacherIdExist ) {
				return new BasicRes(ResMessage.TEACHER_ID_NOT_FOUND.getCode(), 
						ResMessage.TEACHER_ID_NOT_FOUND.getMessage());
			}
			teacherDatabaseDao.save(new TeacherDatabase(req.getStatus(),req.getTeacherId(),req.getClubId(),encoder.encode(req.getPwd()),
					req.getName(),req.getEmail(), req.getType()));
		}
		if(req.getTeacherId() < 0) {
			req.setTeacherId(0);
		}
		teacherDatabaseDao.save(new TeacherDatabase(req.getStatus(),req.getTeacherId(),req.getClubId(),encoder.encode(req.getPwd()),
				req.getName(),req.getEmail(), req.getType()));
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage()) ;
	}



}
