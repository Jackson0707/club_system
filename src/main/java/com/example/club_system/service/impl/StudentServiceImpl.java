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

	@Override
	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req) {
		if (req.getStudentId() == 0) {
			return new BasicRes(ResMessage.STUDENT_ID_ERROR.getCode(), ResMessage.STUDENT_ID_ERROR.getMessage());
		}
//		Student student;
		// 因為 Quiz 中 questions 的資料格式是 String，所以要將 req 的 List<Question> 轉成 String
		// 透過 ObjectMapper 可以將物件(類別)轉成 JSON 格式的字串
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getStudentId());
//					 若 req 中的 id > 0，表示更新已存在的資料;若 id = 0;則表示要新增
			if (req.getStudentId() > 0) {
				// 以下兩種方式擇一
				// 使用方法1，透過 findById，若有資料，就會回傳一整筆的資料(可能資料量會較大)
				// 使用方法2，因為是透過 existsById 來判斷資料是否存在，所以回傳的資料永遠都只會是一個 bit(0 或 1)
//						//方法1.透過 findById，若有資料，回傳整筆資料
//						Optional<Quiz> op = quizDao.findById(req.getId());
//						//判斷是否有資料
//						if(op.isEmpty()) {//op.isEmpty():表示沒有資料
//							return new BasicRes(ResMessage.UPDATE_ID_NOT_FOUND.getCode(),
//									ResMessage.UPDATE_ID_NOT_FOUND.getMessage());
//						}
//						Quiz quiz = op.get();
//						//設定新值(值從 req 來)
//						//將 req 中的新值設定到舊的 quiz 中，不設定 Id，因為 Id 都一樣
//						quiz.setName(req.getName());
//						quiz.setDescription(req.getDescription());
//						quiz.setStartDate(req.getStartDate());
//						quiz.setEndDate(req.getEndDate());
//						quiz.setQuestions(questionStr);
//						quiz.setPublished(req.isPublished());
				// 方法2.透過 existsById :回傳一個 bit 的值
				// 這邊要判斷從 req 帶進來的 TeacherId 是否真的有存在於 DB 中
				// 因為若 StudentId 不存在，後續程式碼再呼叫 JPA 的 save 方法時，會變成新增
				boolean boo = studentDao.existsBySemester(req.getSemester());
				if (req.getStudentId() < 0 && !boo) {// !boo 表示資料不存在
					return new BasicRes(ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_STUDENT_ID_NOT_FOUND.getMessage());
				}
			}

			// new TeacherDatabase() 中帶入 req.getId() 是PK，在呼叫 save 時，會先去檢查 PK 是否有存在於 DB 中，
			// 若存在-->更新;不存在-->新增
			// req 中沒有該欄位時，預設是 0 ，因為 TeacherId 的資料型態是 int
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

		// 假設 name 是 null 或是全空白字串，可以視為沒有輸入條件值，就表示要取得全部
		// JPA 的 containing 方法，條件值是空字串時，會搜尋全部
		// 所以要把 name 的值是 null 或是全空白字串時，轉換成空字串
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
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<Student> op = studentDao.findFirstBystudentIdOrderByUpdateTimeDesc(req.getStudentId());
		// 確認帳號存在
		if (op.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 Atm 資訊
		Student student = op.get();

		// 判斷密碼是否正確
		if (!encoder.matches(req.getPwd(), student.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
	}



	

}
