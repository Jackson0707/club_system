package com.example.club_system.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Student;
import com.example.club_system.entity.StudentId;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentDeleteList;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public BasicRes updataPwd(StudentUpdataPwdReq req) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getOldPwd())//
				|| !StringUtils.hasText(req.getNewPwd())) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}

		// �T�{�b���O�_�s�b
		Optional<Student> op = studentDao.findFirstBystudentIdOrderByUpdateTimeDesc(req.getStudentId());
		// �T�{�b���s�b
		if (op.isEmpty()) {
			System.out.println(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getCode(),
					ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
		}

		// ���o�̷s���ǥ͸��
		Student student = op.get();

		// �ˬd�±K�X�O�_���T
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(req.getOldPwd(), student.getPwd())) {
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// ��s�K�X�ëO�s
		student.setPwd(encoder.encode(req.getNewPwd()));
		student.setUpdateTime(LocalDateTime.now()); // ��s�ɶ��W�O
		studentDao.save(student);

		System.out.println(ResMessage.SUCCESS.getMessage());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req) {
		if (req.getStudentId() == 0) {
			return new BasicRes(ResMessage.STUDENT_ID_ERROR.getCode(), ResMessage.STUDENT_ID_ERROR.getMessage());
		}
//		Student student;
		// �]�� Quiz �� questions ����Ʈ榡�O String�A�ҥH�n�N req �� List<Question> �ন String
		// �z�L ObjectMapper �i�H�N����(���O)�ন JSON �榡���r��
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getStudentId());
//					 �Y req ���� id > 0�A��ܧ�s�w�s�b�����;�Y id = 0;�h��ܭn�s�W
			if (req.getStudentId() > 0) {
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
				// �]���Y StudentId ���s�b�A����{���X�A�I�s JPA �� save ��k�ɡA�|�ܦ��s�W
				boolean boo = studentDao.existsBySemester(req.getSemester());
				if (req.getStudentId() < 0 && !boo) {// !boo ��ܸ�Ƥ��s�b
					return new BasicRes(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
				}
			}

			// new TeacherDatabase() ���a�J req.getId() �OPK�A�b�I�s save �ɡA�|���h�ˬd PK �O�_���s�b�� DB ���A
			// �Y�s�b-->��s;���s�b-->�s�W
			// req ���S�������ɡA�w�]�O 0 �A�]�� TeacherId ����ƫ��A�O int
			req.setUpdateTime(LocalDateTime.now());
			studentDao.save(
					new Student(req.getStudentId(), req.getSemester(), encoder.encode(req.getPwd()), req.getGrade()//
							, req.getName(), req.getEmail(), req.getClubId(), req.getChoiceList(), req.getStatus(),
							req.getUpdateTime()));

		} catch (JsonProcessingException e) {
			return new BasicRes(ResMessage.PROCESSING_EXCEPTION.getCode(),
					ResMessage.PROCESSING_EXCEPTION.getMessage());
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public StudentSearchRes search(StudentsearchReq req) {

		String name = req.getName();

		String semester = req.getSemester();

		String grade = req.getGrade();

		String status = req.getStatus();

		int studentId = req.getStudentId();

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
		if (!StringUtils.hasText(semester)) {
			semester = "";
		}
		if (!StringUtils.hasText(grade)) {
			grade = "";
		}
		if (studentId == 0 && clubId == 0) {
			return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					studentDao.findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContaining(name,
							status, semester, grade));
		}
		if (studentId == 0 && clubId != 0) {
			return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					studentDao.findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndClubId(
							name, status, semester, grade, clubId));
		}
		if (studentId != 0 && clubId == 0) {
			return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					studentDao
							.findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndStudentId(
									name, status, semester, grade, studentId));
		}
		if (studentId != 0 && clubId != 0) {
			return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					studentDao.findByStudentIdAndClubId(studentId, clubId));
		}
		return new StudentSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), studentDao
				.findByNameContainingAndStatusContainingAndSemesterContainingAndGradeContainingAndStudentIdAndClubId(
						name, status, semester, grade, clubId, studentId));
	}

	@Override
	public BasicRes delete(StudentdeleteReq req) {

		List<StudentDeleteList> studentsToDelete = req.getStudentIdList();
		for (StudentDeleteList student : studentsToDelete) {
			StudentId studentId = new StudentId(student.getStudentId(), student.getSemester());
			studentDao.deleteById(studentId);
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes login(StudentLoginReq req) {
		// �T�{�b���O�_�w�s�b
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
		Optional<Student> op = studentDao.findFirstBystudentIdOrderByUpdateTimeDesc(req.getStudentId());
		// �T�{�b���s�b
		if (op.isEmpty()) {// op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// �q op �����^ Atm ��T
		Student student = op.get();

		// �P�_�K�X�O�_���T
		if (!encoder.matches(req.getPwd(), student.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
	}



	

}
