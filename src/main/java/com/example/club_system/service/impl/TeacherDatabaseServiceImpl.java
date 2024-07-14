package com.example.club_system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginRes;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherSearchRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeacherDatabaseServiceImpl implements TeacherDatabaseService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ClubDao clubDao;

	@Autowired
	private TeacherDatabaseDao teacherDatabaseDao;

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public BasicRes updatePwd(int teacherId, String oldpwd, String newpwd) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(String.valueOf(teacherId)) || !StringUtils.hasText(oldpwd)//
				|| !StringUtils.hasText(newpwd)) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
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
		TeacherDatabase teacherDatabase;
		// �]�� Quiz �� questions ����Ʈ榡�O String�A�ҥH�n�N req �� List<Question> �ন String
		// �z�L ObjectMapper �i�H�N����(���O)�ন JSON �榡���r��
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getTeacherId());
//					 �Y req ���� id > 0�A��ܧ�s�w�s�b�����;�Y id = 0;�h��ܭn�s�W
			if (req.getTeacherId() > 0) {
				// �H�U��ؤ覡�ܤ@
				// �ϥΤ�k1�A�z�L findById�A�Y����ơA�N�|�^�Ǥ@�㵧�����(�i���ƶq�|���j)
				// �ϥΤ�k2�A�]���O�z�L existsById �ӧP�_��ƬO�_�s�b�A�ҥH�^�Ǫ���ƥû����u�|�O�@�� bit(0 �� 1)
//						//��k1.�z�L findById�A�Y����ơA�^�Ǿ㵧���
//						Optional<Quiz> op = quizDao.findById(req.getId());
//						//�P�_�O�_�����
//						if(op.isEmpty()) {//op.isEmpty():��ܨS�����
//							return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
//									ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//						}
//						Quiz quiz = op.get();
//						//�]�w�s��(�ȱq req ��)
//						//�N req �����s�ȳ]�w���ª� quiz ���A���]�w Id�A�]�� Id ���@��
//						quiz.setName(req.getName());
//						quiz.setDescription(req.getDescription());
//						quiz.setStartDate(req.getStartDate());
//						quiz.setEndDate(req.getEndDate());
//						quiz.setQuestions(questionStr);
//						quiz.setPublished(req.isPublished());
				// ��k2.�z�L existsById :�^�Ǥ@�� bit ����
				// �o��n�P�_�q req �a�i�Ӫ� TeacherId �O�_�u�����s�b�� DB ��
				// �]���Y TeacherId ���s�b�A����{���X�A�I�s JPA �� save ��k�ɡA�|�ܦ��s�W
				boolean boo = teacherDatabaseDao.existsById(req.getTeacherId());
				if (!boo) {// !boo ��ܸ�Ƥ��s�b
					return new BasicRes(ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getMessage());
				}
			}

			// new TeacherDatabase() ���a�J req.getId() �OPK�A�b�I�s save �ɡA�|���h�ˬd PK �O�_���s�b�� DB ���A
			// �Y�s�b-->��s;���s�b-->�s�W
			// req ���S�������ɡA�w�]�O 0 �A�]�� TeacherId ����ƫ��A�O int
			teacherDatabaseDao.save(new TeacherDatabase(req.getStatus(), req.getTeacherId(), //
					encoder.encode(req.getPwd()), req.getName(), req.getEmail(), req.getType()));

		} catch (JsonProcessingException e) {
			return new BasicRes(ResMessage.PROCESSING_EXCEPTION.getCode(),
					ResMessage.PROCESSING_EXCEPTION.getMessage());
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public TeacherSearchRes search(TeacherSearchReq req) {
		String name = req.getName();

		String status = req.getStatus();

		int clubId = req.getClubId();

		// ���] name �O null �άO���ťզr��A�i�H�����S����J����ȡA�N��ܭn���o����
		// JPA �� containing ��k�A����ȬO�Ŧr��ɡA�|�j�M����
		// �ҥH�n�� name ���ȬO null �άO���ťզr��ɡA�ഫ���Ŧr��
		if (!StringUtils.hasText(name)) {
			name = "";
		}
		if (!StringUtils.hasText(status)) {
			status = "";
		}
//		if (clubId != 0) {
		return new TeacherSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				teacherDatabaseDao.findByNameContainingAndStatusContainingAndClubIdOrderByTeacherIdAsc(name, status,
						clubId));
//		}
//		return new TeacherSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
//				teacherDatabaseDao.findByNameContainingAndStatusContainingOrderByTeacherIdAsc(name, status));
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
	public BasicRes login(int teacherId, String pwd) {
		// �T�{�b���O�_�w�s�b
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
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
		{
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
	}

	@Override
	public BasicRes loginAdmin(int teacherId, String pwd) {
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

	@Override
	public TeacherLoginRes teacherClubStudent(TeacherGetStudentReq req) {
		Optional<TeacherDatabase> clubTeacherData = teacherDatabaseDao.findById(req.getTeacherId());
		
		if(clubTeacherData.isEmpty()) {
			return new TeacherLoginRes(ResMessage.TEACHER_ID_NOT_FOUND.getCode(), 
					ResMessage.TEACHER_ID_NOT_FOUND.getMessage());
		}
		TeacherDatabase teacher = clubTeacherData.get();
		
		Optional<Club> op = clubDao.findById(teacher.getClubId());
		
		if(op.isEmpty()) {
			return new TeacherLoginRes(ResMessage.PARAM_CLUB_ID_NOT_EXIST.getCode(), 
					ResMessage.PARAM_CLUB_ID_NOT_EXIST.getMessage());
		}
		
		Club clubData = op.get();
		
		List<Student> clubStdentList = studentDao.findByClubId(teacher.getClubId());
		
		List<Student> clubStudent = new ArrayList<>(clubStdentList);
		
		
		return new TeacherLoginRes(ResMessage.SUCCESS.getCode(), 
				ResMessage.SUCCESS.getMessage(),teacher.getName(),teacher.getClubId(), clubData.getName(),
				clubStudent);
	}



}
