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
		// 檢查參數
		if (!StringUtils.hasText(String.valueOf(studentId)) || !StringUtils.hasText(oldpwd)//
				|| !StringUtils.hasText(newpwd)) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<Student> op = studentDao.findBystudentId(studentId);
		// 確認帳號存在
		if (op.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 TeacherDatabase 資訊
		Student student = op.get();
		// 取回舊密碼並判斷
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches(原始密碼,加密後的密碼):比對密碼
		// 原始密碼: 指的是使用者輸入的密碼,及參數中的oldpwd
		// 加密後的密碼: 指的是透過 BCryptPasswordEncoder 加密過的密碼,存在於 DB 中

		if (!encoder.matches(oldpwd, student.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// 設定新密碼(加密後的密碼)
		student.setPwd(encoder.encode(newpwd));
		// 將新的資料存回 DB
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
