package com.example.club_system.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.repository.TeacherDatabaseDao;
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private TeacherDatabaseDao teacherDatabaseDao;

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

	// �ǥͳЫػP��s
	@Override
	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req) {
		if(req.getStudentId() > 0) {
			boolean studentIdExist = studentDao.existsById(req.getStudentId());
			if(!studentIdExist ) {
				studentDao.save(new Student(req.getStudentId(), req.getSemester(), req.getPwd(), req.getGrade(), req.getName(), 
						req.getEmail(), req.getClubId(),
						req.getStatus()));
			}
		}
		studentDao.save(new Student(req.getStudentId(), req.getSemester(), req.getPwd(), req.getGrade(), req.getName(), 
				req.getEmail(), req.getClubId(),
				req.getStatus())) ;
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



	

}
