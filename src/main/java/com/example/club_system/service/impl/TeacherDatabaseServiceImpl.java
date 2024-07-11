package com.example.club_system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
				// 因為若 TeacherId 不存在，後續程式碼再呼叫 JPA 的 save 方法時，會變成新增
				boolean boo = teacherDatabaseDao.existsById(req.getTeacherId());
				if (!boo) {// !boo 表示資料不存在
					return new BasicRes(ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getCode(),
							ResMessage.UPDATE_TEACHER_ID_NOT_FOUND.getMessage());
				}
			}

			// new TeacherDatabase() 中帶入 req.getId() 是PK，在呼叫 save 時，會先去檢查 PK 是否有存在於 DB 中，
			// 若存在-->更新;不存在-->新增
			// req 中沒有該欄位時，預設是 0 ，因為 TeacherId 的資料型態是 int
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

		// 假設 name 是 null 或是全空白字串，可以視為沒有輸入條件值，就表示要取得全部
		// JPA 的 containing 方法，條件值是空字串時，會搜尋全部
		// 所以要把 name 的值是 null 或是全空白字串時，轉換成空字串
		if (!StringUtils.hasText(name)) {
			name = "";
		}
		if (!StringUtils.hasText(status)) {
			status = "";
		}
		return new TeacherSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
				teacherDatabaseDao.findByNameContainingAndStatusContainingOrderByTeacherIdAsc(name, status));
	}

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
	public BasicRes login(int teacherId, String pwd) {
		// 確認帳號是否已存在
		// 1. 因為會對收集出來的資料作密碼比對，所以要用 findById 來撈資料
		// 2. 只使用 findById 來撈資料的原因是存在資料庫中的 password 欄位的值是使用 BCryptPasswordEncoder
		// 加密後的值，其特性是一樣的內容值，每次加密後得到的結果都會不同，所以無法直接透過加密後的值來比對資料庫中的內容
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
		{
			System.out.println(ResMessage.SUCCESS.getMessage());
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
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

	@Override
	public BasicRes clubRandom() {

		// 宣告變數，紀錄社團可增加名額，一開始抓取所有社團的最大上限人數，用 HashMap 儲存 V
		// 先抓出所有學生的資料，將 club 志願序存到陣列中 V
		// 宣告一個陣列，儲存已經有社團id的學生
		// HashMap 第一輪(第一志願序)：社團id作為 key，學生id 作為 value，
		// 以陣列的方式儲存成 3:[23, 25, ] 2:[36]
		// if 陣列長度沒超過，就不用抽籤，用陣列長度減去可容納長度，存回紀錄社團名額的變數中
		// 社團選擇人數超過社團上限人數，隨機抽取目前社團剩餘的席次
		// 社團人數滿人，社團剩餘人數歸零，在第二輪塞選中就會接跳過該社團(抽籤，並將社團可容納人數歸零)
		// 第二輪，如果學生id在陣列裡面，就跳過。依第二志願序，存到 HashMap
		// 判斷每個社團可否再容納人
		// 沒有，就跳過該社團的抽籤
		// 有，抽籤，取值時用Entry

		// 社團上限人數
		List<Club> clubs = clubDao.findAll(); // 先取出Club這個entity中的多筆資料的所有值
		Map<Integer, Integer> clubMax = new HashMap<>();
		for (int i = 0; i < clubs.size(); i++) { // 先用for迴圈把所有資料列出來
			Club club = clubs.get(i); // 用一個變數來接for迴圈整理出來的資料
			clubMax.put(club.getClubId(), club.getMax()); // 利用 .put功能把想要塞如的值加進map裡面
		}
		System.out.println(clubMax);

		// 學生志願序
		List<Student> studentChoice = studentDao.findAll();
		// 下面中的Integer[]，原本是String，但因為前端傳進資料庫中的型態為陣列，但因為要把她轉成int會較容易連接到相對應的社團Id
		// 因為陣列的資料型態為String，將他強制轉型需要在[]前棉加上Integer
		Map<Integer, Integer[]> studentChoiceArr = new HashMap<>();
		for (int i = 0; i < studentChoice.size(); i++) {
			Student student = studentChoice.get(i);
			Integer[] choiceArr; // 宣告一個陣列來接值
			try {
				choiceArr = mapper.readValue(student.getChoiceList(), Integer[].class);
				studentChoiceArr.put(student.getStudentId(), choiceArr);
			} catch (JsonProcessingException e) {
				return new BasicRes(ResMessage.ACCOUNT_NOT_FOUND.getCode(), ResMessage.ACCOUNT_NOT_FOUND.getMessage());
//				e.printStackTrace();
			}

		}

		// 已經有Id的學生
		List<Student> clubStudentValue = studentDao.findAll();
		Map<Integer, Integer> clubStudentArr = new HashMap<>();
		for (int i = 0; i < clubStudentValue.size(); i++) {
			Student clubStudent = clubStudentValue.get(i);
			clubStudentArr.put(clubStudent.getStudentId(), clubStudent.getClubId());
		}
		System.out.println(clubStudentArr);

		
		
		// 實作抽籤
		// 第一次抽籤: key:社團id, value:學生id
		
		
		ArrayList<Integer> studentId = new ArrayList<>();
		studentId.add(studentChoiceArr.get(studentChoiceArr)[0]);
		
		Map<ArrayList<Integer>, Integer> firstRandom = new HashMap<>();
		
		
		
		
		
		


		return null;
	}

}
