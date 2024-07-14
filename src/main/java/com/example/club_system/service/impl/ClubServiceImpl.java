package com.example.club_system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ClubDao clubDao;
	
	private ObjectMapper mapper = new ObjectMapper();

	// 創建及更新社團資料
	@Override
	public BasicRes createOrUpdate(ClubCreateOrUpdateReq req) {
		System.out.println(req.getClubId());
		if (req.getClubId() == 0) {
			Club club = new Club();
			club.setName(req.getName());
			club.setTeacherId(req.getTeacherId());
			club.setPay(req.getPay());
			club.setSemester(req.getSemester());
			club.setIntro(req.getIntro());
			club.setMax(req.getMax());
			club.setClassroom(req.getClassroom());
			club.setChoiceStartTime(req.getChoiceStartTime());
			club.setChoiceEndTime(req.getChoiceEndTime());
			club.setClubStartTime(req.getClubStartTime());
			club.setClubEndTime(req.getClubEndTime());
			club.setDrawTime(req.getDrawTime());

			clubDao.save(club);

		} else {
			Club club = new Club();
			club.setClubId(req.getClubId());
			club.setName(req.getName());
			club.setTeacherId(req.getTeacherId());
			club.setPay(req.getPay());
			club.setSemester(req.getSemester());
			club.setIntro(req.getIntro());
			club.setMax(req.getMax());
			club.setClassroom(req.getClassroom());
			club.setChoiceStartTime(req.getChoiceStartTime());
			club.setChoiceEndTime(req.getChoiceEndTime());
			club.setClubStartTime(req.getClubStartTime());
			club.setClubEndTime(req.getClubEndTime());
			club.setDrawTime(req.getDrawTime());

			clubDao.save(club);

		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 刪除社團資料
	@Override
	public BasicRes delete(ClubDeleteReq req) {
		
		if (!CollectionUtils.isEmpty(req.getIdList())) {
			// 刪除問卷
			try {
				for(Integer id : req.getIdList()) {
					System.out.println(id);
					clubDao.deleteById(id);;
				}
				
			} catch (Exception e) {
				// 當 deleteAllById 方法中，id 的值不存在，JPA 會報錯
				// 因為在刪除之前， JPA會先搜帶入的 id 值，若沒結果就會報錯
				// 由於實際上也沒刪除任何資料，因此就不需要對這個Exception 做處理
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 搜尋社團
	@Override
	public ClubSearchRes search(ClubSearchReq req) {
		
		int clubId = req.getClubId();
		
		String name = req.getName();

		String semester = req.getSemester();
		
		int teacherId = req.getTeacherId();

		// 假設 name 是 null 或是全空白字串，可以視為沒有輸入條件值，就表示要取得全部
		// JPA 的 containing 方法，條件值是空字串時，會搜尋全部
		// 所以要把 name 的值是 null 或是全空白字串時，轉換成空字串
		if (!StringUtils.hasText(name)) {
			name = "";
		}
		if (!StringUtils.hasText(semester)) {
			semester = "";
		}
		if (clubId == 0 && teacherId == 0) {
			return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					clubDao.findByNameContainingAndSemester(name, semester));
		}
		if (clubId == 0 && teacherId != 0) {
			return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					clubDao.findByNameContainingAndSemesterAndTeacherId(name, semester, teacherId));
		}
		if (clubId != 0 && teacherId == 0) {
			return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					clubDao.findByNameContainingAndClubIdAndSemester(clubId, name, semester));
		}
		if (clubId != 0 && teacherId != 0) {
			return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					clubDao.findByClubIdAndTeacherId(clubId, teacherId));
		}
		if(clubId != 0 ) {
			return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
					clubDao.findByClubId(clubId));
		}
		return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),//
				clubDao.findByNameContainingAndClubIdAndSemesterAndTeacherId(clubId, name, semester, teacherId));
	}

	
	@Override
	public BasicRes clubRandom() {

		// 先遍歷每個學生的志願序
		// 查看每個社團人數上限
		// if社團人數夠，社團人數減1，hashmap存key學生是哪個 value社團
		// else人數不夠，看第二志願社團人數上限

		// 學生志願序 key:學號, value:志願序
		HashMap<Integer, Integer[]> studentChoiceMap = new HashMap<>();
		List<Student> studentList = studentDao.findAll();
		for (Student studentData : studentList) {
			try {
				Integer[] choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
				System.out.println("資料有錯: " + studentData.getStudentId());
			}
		}

		// 檢查各社團的上限人數
		HashMap<Integer, Integer> clubMaxMap = new HashMap<>();
		List<Club> clubList = clubDao.findAll();
		for (Club clubData : clubList) {
			clubMaxMap.put(clubData.getClubId(), clubData.getMax());
		}

		// 存儲抽籤結果
		HashMap<Integer, Integer> drawResult = new HashMap<>(); // key: 學生Id, value: 社團Id

		// 創建學生Id的列表，用來隨機選擇，拿到學生Id
		List<Integer> studentDrawList = new ArrayList<>(studentChoiceMap.keySet());

		// 創建一個可以繼續分配的社團列表
		List<Integer> availableClubs = new ArrayList<>(clubMaxMap.keySet());

		while (!studentDrawList.isEmpty() && !availableClubs.isEmpty()) {
			// 隨機選擇一個學生
			int randomChooseStudent = (int) (Math.random() * studentDrawList.size());
			int randomStudentId = studentDrawList.get(randomChooseStudent); // 隨機抽的學生Id
			Integer[] randomStudentChoices = studentChoiceMap.get(randomStudentId); // 取得Map中相對應的學生Id的志願序

			System.out.println(randomChooseStudent);
			// 索引值從0開始遍歷學生的志願
			for (int i = 0; i < randomStudentChoices.length; i++) {
				int clubId = randomStudentChoices[i];
				System.out.printf("第 %d 個志願序: %d \n", i, clubId);
				// 檢查這個社團是否還有空位
				System.out.println("此志願序是否可進入?" + availableClubs.contains(clubId));
				if (availableClubs.contains(clubId)) {
					// 分配學生到這個社團
					drawResult.put(randomStudentId, clubId);

					// 更新社團剩餘名額
					int remainingSpots = clubMaxMap.get(clubId) -1;
					clubMaxMap.put(clubId, remainingSpots);

					// 如果社團滿了，從可用社團列表中移除
					if (remainingSpots == 0) {
						System.out.println("我進來了");
						availableClubs.remove(availableClubs.indexOf(clubId));
					}
					System.out.println("此社團剩餘可容納人數: " + remainingSpots);
					System.out.println("此社團可否再容納人？" + availableClubs.contains(clubId));
					break;
				}
			}

			// 從待分配列表中移除該學生
			studentDrawList.remove(randomChooseStudent);
			
			System.out.println(drawResult);
		}

		 // 更新數據庫
		List<Student> updatedStudents = new ArrayList<>();
		for (Student student : studentList) {
		    Integer assignedClubId = drawResult.get(student.getStudentId());
		    if (assignedClubId != null) {
		        student.setClubId(assignedClubId);
		        updatedStudents.add(student);
		    }
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}
}
