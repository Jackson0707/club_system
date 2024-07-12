package com.example.club_system.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.studentcreateOrUpdateReq;
import com.example.club_system.vo.studentdeleteReq;
import com.example.club_system.vo.studentsearchReq;

@Service
public class studentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public BasicRes updataPwd(int studentId,String oldpwd,String newpwd) {
		// �ˬd�Ѽ�
		if (!StringUtils.hasText(String.valueOf(studentId)) || !StringUtils.hasText(oldpwd)//
				|| !StringUtils.hasText(newpwd)) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// �T�{�b���O�_�w�s�b
		// 1. �]���|�怜���X�Ӫ���Ƨ@�K�X���A�ҥH�n�� findById �Ӽ����
		// 2. �u�ϥ� findById �Ӽ���ƪ���]�O�s�b��Ʈw���� password ��쪺�ȬO�ϥ� BCryptPasswordEncoder
		// �[�K�᪺�ȡA��S�ʬO�@�˪����e�ȡA�C���[�K��o�쪺���G���|���P�A�ҥH�L�k�����z�L�[�K�᪺�ȨӤ���Ʈw�������e
		Optional<Student> op = studentDao.findBystudentId(studentId);
		// �T�{�b���s�b
		if (op.isEmpty()) {// op.isEmpty() ���P�� op.isEmpty() ==true�A��ܨS�����
			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// �q op �����^ TeacherDatabase ��T
		Student student = op.get();
		// ���^�±K�X�çP�_
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches(��l�K�X,�[�K�᪺�K�X):���K�X
		// ��l�K�X: �����O�ϥΪ̿�J���K�X,�ΰѼƤ���oldpwd
		// �[�K�᪺�K�X: �����O�z�L BCryptPasswordEncoder �[�K�L���K�X,�s�b�� DB ��

		if (!encoder.matches(oldpwd, student.getPwd())) { // �e������ĸ� ��ܱK�X��異��
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// �]�w�s�K�X(�[�K�᪺�K�X)
		student.setPwd(encoder.encode(newpwd));
		// �N�s����Ʀs�^ DB
		studentDao.save(student);
		System.out.println(ResMessage.SUCCESS.getMessage());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes createOrUpdate(studentcreateOrUpdateReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentSearchRes search(studentsearchReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicRes delete(studentdeleteReq req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicRes login(int clubId, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

}
