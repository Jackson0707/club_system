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
import com.example.club_system.vo.TeacherLoginReq;
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
		// 檢查參數
		if (!StringUtils.hasText(String.valueOf(teacherId)) || !StringUtils.hasText(oldpwd)//
				|| !StringUtils.hasText(newpwd)) {
			System.out.println(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getCode(),
					ResMessage.ACCOUNT_OR_PASSWORD_ERROR.getMessage());
		}
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<TeacherDatabase> op = teacherDatabaseDao.findById(teacherId);
		// 確認帳號存在
		if (op.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			System.out.println(ResMessage.ACCOUNT_NOT_FOUND.getMessage());
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 TeacherDatabase 資訊
		TeacherDatabase teacherDatabase = op.get();
		// 取回舊密碼並判斷
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// encoder.matches(原始密碼,加密後的密碼):比對密碼
		// 原始密碼: 指的是使用者輸入的密碼,及參數中的oldpwd
		// 加密後的密碼: 指的是透過 BCryptPasswordEncoder 加密過的密碼,存在於 DB 中

		if (!encoder.matches(oldpwd, teacherDatabase.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			System.out.println(ResMessage.PSAAWORD_ERROR.getMessage());
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}

		// 設定新密碼(加密後的密碼)
		teacherDatabase.setPwd(encoder.encode(newpwd));
		// 將新的資料存回 DB
		teacherDatabaseDao.save(teacherDatabase);
		System.out.println(ResMessage.SUCCESS.getMessage());
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes createOrUpdate(TeacherDatabaseCreateOrUpdateReq req) {
		TeacherDatabase teacherDatabase;
		// 因為 Quiz 中 questions 的資料格式是 String，所以要將 req 的 List<Question> 轉成 String
		// 透過 ObjectMapper 可以將物件(類別)轉成 JSON 格式的字串
		ObjectMapper mapper = new ObjectMapper();
		try {
			String questionStr = mapper.writeValueAsString(req.getTeacherId());
//					 若 req 中的 id > 0，表示更新已存在的資料;若 id = 0;則表示要新增
			if (req.getTeacherId() > 0) {
				
				boolean boo = teacherDatabaseDao.existsById(req.getTeacherId());
				if (!boo) {// !boo 表示資料不存在
					return new BasicRes(ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getMessage());
				}
			}

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

		// 假設 name 是 null 或是全空白字串，可以視為沒有輸入條件值，就表示要取得全部
		// JPA 的 containing 方法，條件值是空字串時，會搜尋全部
		// 所以要把 name 的值是 null 或是全空白字串時，轉換成空字串
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
		}
//		return new TeacherSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
//				teacherDatabaseDao.findByNameContainingAndStatusContainingOrderByTeacherIdAsc(name, status));
//	}

	@Override
	public BasicRes delete(TeacherDeleteReq req) {
		// 參數檢查
		if (!CollectionUtils.isEmpty(req.getTeacherIdList())) {
			// 刪除問卷
			try {
				teacherDatabaseDao.deleteAllById(req.getTeacherIdList());
			} catch (Exception e) {
				// 當 deleteAllById 方法中，id 的值不存在，JPA 會報錯
				// 因為在刪除之前， JPA會先搜帶入的 id 值，若沒結果就會報錯
				// 由於實際上也沒刪除任資料，因此就不需要對這個Exception 做處理
			}
		}

		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	@Override
	public TeacherLoginRes login(TeacherLoginReq req) {
		// 確認帳號是否已存在m
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
		Optional<TeacherDatabase> clubTeacherId = teacherDatabaseDao.findById(req.getTeacherId());
		// 確認帳號存在
		if (clubTeacherId.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			return new TeacherLoginRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 老師 資訊
		TeacherDatabase teacherDatabase = clubTeacherId.get();

		// 判斷密碼是否正確
		if (!encoder.matches(req.getPwd(), teacherDatabase.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			return new TeacherLoginRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		{
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new TeacherLoginRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),teacherDatabase.getTeacherId()
					);
		}
	}

	@Override
	public BasicRes loginAdmin(int teacherId, String pwd) {
		Optional<TeacherDatabase> op = teacherDatabaseDao.findById(teacherId);
		// 確認帳號存在
		if (op.isEmpty()) {// op.isEmpty() 等同於 op.isEmpty() ==true，表示沒有資料
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}

		// 從 op 中取回 Atm 資訊
		TeacherDatabase teacherDatabase = op.get();

		// 判斷密碼是否正確
		if (!encoder.matches(pwd, teacherDatabase.getPwd())) { // 前面有驚嘆號 表示密碼比對失敗
			return new BasicRes(ResMessage.PSAAWORD_ERROR.getCode(), ResMessage.PSAAWORD_ERROR.getMessage());
		}
		if (teacherDatabase.getType() != "管理員") {
			return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 老師登入後取得的社團及學生資訊
	@Override 
	public TeacherLoginRes teacherClubStudent(TeacherGetStudentReq req) {
		
		// clubTeacherData: 取得老師Id的所有資訊
		Optional<TeacherDatabase> clubTeacherData = teacherDatabaseDao.findById(req.getTeacherId());
		
		if(clubTeacherData.isEmpty()) {
			return new TeacherLoginRes(ResMessage.TEACHER_ID_NOT_FOUND.getCode(), 
					ResMessage.TEACHER_ID_NOT_FOUND.getMessage());
		}
		
		// clubTeacherData:裡面有該老師的所有資訊
		TeacherDatabase teacher = clubTeacherData.get();
		
		// op: 用老師的的社團Id去撈該社團的所有資訊
		Optional<Club> op = clubDao.findById(teacher.getClubId());
		
		if(op.isEmpty()) {
			return new TeacherLoginRes(ResMessage.PARAM_CLUB_ID_NOT_EXIST.getCode(), 
					ResMessage.PARAM_CLUB_ID_NOT_EXIST.getMessage());
		}
		
		// clubData: 拿到該社團Id的所有資訊
		Club clubData = op.get();
		
		List<Student> clubStdentList = studentDao.findByClubId(teacher.getClubId());
		
		List<Student> clubStudent = new ArrayList<>(clubStdentList);
		
		
		
		
		return new TeacherLoginRes(ResMessage.SUCCESS.getCode(), 
				ResMessage.SUCCESS.getMessage(),teacher.getName(),teacher.getClubId(), clubData.getName(),
				clubStudent);
	}



}
