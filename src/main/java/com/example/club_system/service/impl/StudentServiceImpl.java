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
		// 檢查參數
		if (!StringUtils.hasText(String.valueOf(req.getStudentId())) || !StringUtils.hasText(req.getOldPwd())//
				|| !StringUtils.hasText(req.getNewPwd())) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}

		// 確認帳號是否存在
		Optional<Student> op = studentDao.findFirstBystudentIdOrderByUpdateTimeDesc(req.getStudentId());
		// 確認帳號存在
		if (op.isEmpty()) {
			System.out.println(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getCode(),
					ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
		}

		// 取得最新的學生資料
		Student student = op.get();

		// 檢查舊密碼是否正確
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(req.getOldPwd(), student.getPwd())) {
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// 更新密碼並保存
		student.setPwd(encoder.encode(req.getNewPwd()));
		student.setUpdateTime(LocalDateTime.now()); // 更新時間戳記
		studentDao.save(student);

		System.out.println(ResMessage.SUCCESS.getMessage());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 學生創建與更新
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
			// 刪除學生
			try {
				studentDao.deleteAllById(req.getStudentIdList());
			} catch (Exception e) {
				// 當 deleteAllById 方法中，id 的值不存在，JPA 會報錯
				// 因為在刪除之前， JPA會先搜帶入的 id 值，若沒結果就會報錯
				// 由於實際上也沒刪除任資料，因此就不需要對這個Exception 做處理
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public StudentLoginRes login(StudentLoginReq req) {
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<Student> getStudentId = studentDao.findById(req.getStudentId());
		// 確認帳號存在
		if (getStudentId.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			return new StudentLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 studentId 資訊
		Student studentIdData = getStudentId.get();

		// 判斷密碼是否正確
		if (!encoder.matches(req.getPwd(), studentIdData.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			return new StudentLoginRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			return new StudentLoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), studentIdData.getStudentId());
		}
	}



	

}
