package com.example.club_system.service.impl;

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
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ForgetPwdReq;
import com.example.club_system.vo.StudentGetClubDataReq;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;

@Service
public class StudentServiceImpl implements StudentService {

	//�H�c
	@Autowired
    private JavaMailSender javaMailSender; 
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private TeacherDatabaseDao teacherDatabaseDao;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public BasicRes updataPwd(StudentUpdataPwdReq req) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getOldPwd())//
				|| !StringUtils.hasText(req.getNewPwd())) {
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}

		// �T�{�b���O�_�s�b
		Optional<Student> op = studentDao.findById(req.getStudentId());
		// �T�{�b���s�b
		if (op.isEmpty()) {
			System.out.println(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
					ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// ���o�̷s���ǥ͸��
		Student student = op.get();

		// �ˬd�±K�X�O�_���T
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(req.getOldPwd(), student.getPwd())) {
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// ��s�K�X�ëO�s
		student.setPwd(encoder.encode(req.getNewPwd()));
		studentDao.save(student);

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// �ǥͳЫػP��s
	@Override
	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req) {
		if(req.getStudentId() > 0) {
			Optional<Student> studentId = studentDao.findById(req.getStudentId());
			if( !studentId.isPresent()) {
				return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			}
			Student studentData = studentId.get();
			studentData.setName(req.getName());
			studentData.setEmail(req.getEmail());
			studentDao.save(studentData);
			return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
		}
		if(req.getStudentId() == null) {
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		studentDao.save(new Student(req.getStudentId(), req.getSemester(), encoder.encode(req.getPwd()), req.getGrade(), req.getName(), 
				req.getEmail(), req.getClubId(),
				req.getChoiceList(),req.getStatus())) ;
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
		
	}

	@Override
	public StudentSearchRes search(StudentsearchReq req) {
		String name = !StringUtils.hasText(req.getName()) ?"":req.getName();

		String status = !StringUtils.hasText(req.getStatus()) ?"":req.getStatus();

		String semester = !StringUtils.hasText(req.getSemester()) ?"":req.getSemester();
		
		String grade = !StringUtils.hasText(req.getGrade()) ?"":req.getGrade();
		
		Integer studentId = req.getStudentId();
		
		return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
				studentDao.findByNameAndStatusAndStudentIdAndSemesterAndGrade(name, status, studentId, semester, grade));

		
	}

	@Override
	public BasicRes delete(StudentdeleteReq req) {

		if (!CollectionUtils.isEmpty(req.getStudentIdList())) {
			// �R���ǥ�
			try {
				studentDao.deleteAllById(req.getStudentIdList());
			} catch (Exception e) {
				// �� deleteAllById ��k���Aid ���Ȥ��s�b�AJPA �|����
				// �]���b�R�����e�A JPA�|���j�a�J�� id �ȡA�Y�S���G�N�|����
				// �ѩ��ڤW�]�S�R������ơA�]���N���ݭn��o��Exception ���B�z
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public StudentLoginRes login(StudentLoginReq req) {
		// �T�{�b���O�_�w�s�b
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
		Optional<Student> getStudentId = studentDao.findById(req.getStudentId());
		// �T�{�b���s�b
		if (getStudentId.isEmpty()) {// op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			return new StudentLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// �q op �����^ studentId ��T
		Student studentIdData = getStudentId.get();

		// �P�_�K�X�O�_���T
		if (!encoder.matches(req.getPwd(), studentIdData.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			return new StudentLoginRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			return new StudentLoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), studentIdData.getStudentId());
		}
	}

	

	@Override
	public StudentLoginRes studentGetClubData(StudentGetClubDataReq req) {
		// studentData: ���o�ǥ�Id���Ҧ���T
				Optional<Student> studentData = studentDao.findById(req.getStudentId());
				
				if(studentData.isEmpty()) {
					return new StudentLoginRes(ResMessage.STUDENT_ID_NOT_FOUND.getCode(), 
							ResMessage.STUDENT_ID_NOT_FOUND.getMessage());
				}
				
				// studentData:�̭����Ӿǥͪ��Ҧ���T
				Student student = studentData.get();
				
				// op: �ξǥͪ�������Id�h���Ӫ��Ϊ��Ҧ���T
				Optional<Club> op = clubDao.findById(student.getClubId());
				
				if(op.isEmpty()) {
					return new StudentLoginRes(ResMessage.PARAM_CLUB_ID_NOT_EXIST.getCode(), 
							ResMessage.PARAM_CLUB_ID_NOT_EXIST.getMessage());
				}
				
				// clubData: ����Ӫ���Id���Ҧ���T
				Club clubData = op.get();
				
				Optional<TeacherDatabase> teacherData = teacherDatabaseDao.findById(clubData.getTeacherId());
				 
				  TeacherDatabase teacher = teacherData.get();
				
				return new StudentLoginRes(ResMessage.SUCCESS.getCode(), 
						ResMessage.SUCCESS.getMessage(),student.getClubId(), student.getName(),clubData.getName(),
						clubData.getClassroom(), clubData.getPay(), teacher.getName());
	}

	@Override
	public StudentLoginRes forgotPwd(ForgetPwdReq req) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(req.getStudentId() == null) {
			return new StudentLoginRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		Optional<Student> studentId1 = studentDao.findBystudentId(req.getStudentId());
		if( !studentId1.isPresent()) {
			return new StudentLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		Student studentData = studentId1.get();
		
		String verificationCodeString = RandomStringUtils.randomNumeric(6);
//        Integer verificationCode = Integer.parseInt(verificationCodeString);
        
        studentData.setPwd(encoder.encode(verificationCodeString));
		studentDao.save(studentData);
		sendVerificationEmail(studentData.getEmail(), verificationCodeString);
		return new StudentLoginRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
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
	
	

	// �ѰO�K�X��Email�{��
//	@Override
//	public BasicRes updataPwdByEmail(StudentForgotPwdByEmailReq req) {
//		// �ˬd�Ѽ�
//				if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getEmail())//
//						|| !StringUtils.hasText(req.getNewPwd())) {
//					return new BasicRes(ResMessage.ACCOUNT_OR_EMAIL_ERROR.getCode(),
//							ResMessage.ACCOUNT_OR_EMAIL_ERROR.getMessage());
//				}
//
//				// �T�{�b���O�_�s�b
//				Optional<Student> op = studentDao.findById(req.getStudentId());
//				// �T�{�b���s�b
//				if (op.isEmpty()) {
//					return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(),
//							ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//				}
//
//				// ���o�̷s���ǥ͸��
//				Student student = op.get();
//
//				// �ˬd�±K�X�O�_���T
//				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//				if (StringUtils.hasText(req.getEmail())) {
//					return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
//				}
//
//				// ��s�K�X�ëO�s
//				student.setPwd(encoder.encode(req.getNewPwd()));
//				studentDao.save(student);
//
//				return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
//		
//		
//	}



	

}
