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
import com.example.club_system.vo.StudentdeleteReq;
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
		if(req.getClubId() > 0) {
			boolean clubIdExist = clubDao.existsById(req.getClubId());
			if(!clubIdExist ) {
				return new BasicRes(ResMessage.CLUB_ID_NOT_FOUND.getCode(), 
						ResMessage.CLUB_ID_NOT_FOUND.getMessage());
			}
			clubDao.save(new Club(req.getClubId(), req.getSemester(),req.getName(),req.getIntro(),req.getTeacherId(),
					req.getPay(),req.getClassroom(), req.getMax()));
		}
		if(req.getClubId() < 0) {
			req.setClubId(0);
		}
		clubDao.save(new Club(req.getClubId(), req.getSemester(),req.getName(),req.getIntro(),req.getTeacherId(),
				req.getPay(),req.getClassroom(), req.getMax()));
		return new BasicRes(ResMessage.SUCCESS.getCode(),ResMessage.SUCCESS.getMessage());
	}

	// 刪除社團資料
	@Override
	public BasicRes delete(ClubDeleteReq req) {

		if (!CollectionUtils.isEmpty(req.getClubIdList())) {
			// 刪除問卷
			try {
				for (Integer id : req.getClubIdList()) {
					clubDao.deleteById(id);;
					;
				}

			} catch (Exception e) {
				// 當 deleteAllById 方法中，id 的值不存在，JPA 會報錯
				// 因為在刪除之前， JPA會先搜帶入的 id 值，若沒結果就會報錯
				// 由於實際上也沒刪除任何資料，因此就不需要對這個Exception 做處理
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

//	 搜尋社團
	@Override
	public ClubSearchRes search(ClubSearchReq req) {

		String name = !StringUtils.hasText(req.getName()) ?"":req.getName();

		String semester = !StringUtils.hasText(req.getSemester()) ?"":req.getSemester();
		
		Integer teacherId = req.getTeacherId();
		
		Integer clubId = req.getClubId();
		
		return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
				clubDao.findByNameAndSemesterAndTeacherIdAndClubId(name, semester, teacherId, clubId));
		
	}

	// 社團抽籤功能
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
				if(studentData.getChoiceList() == null) {
					continue;
				}
				Integer[] choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
				//資料有錯，可以把錯誤的地方顯示出來，看哪筆資料錯誤
				System.out.println(studentData.getStudentId());
				return new BasicRes(ResMessage.JSON_ERROR.getCode(), ResMessage.JSON_ERROR.getMessage());
				
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
					int remainingSpots = clubMaxMap.get(clubId) - 1;
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
		}
		//這邊要把抽完籤的結果存起來，所以記得要放在while迴圈之外，不然每筆資料都會進資料庫撈資料!!!
		// 把抽完籤的學生Id遍歷，並找到相對應的社團Id，沒有抽到的學生社團Id設為0
		for (Student student : studentList) {
			Integer getResultClubId = drawResult.get(student.getStudentId());
			if (getResultClubId != null) {
				student.setClubId(getResultClubId);
			} else {
				// 如果學生沒有被分配到社團，設置一個特殊值（例如 0）
				student.setClubId(0);
			}
		}

		// 保存更新後的學生信息到數據庫
		studentDao.saveAll(studentList);
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// 社團Id歸零功能
		@Override
		public BasicRes resetClubId(StudentdeleteReq req) {
			// 拿到學生所有的資料
		    List<Student> studentDataList = studentDao.findAll();
		    for (Student studentData : studentDataList) {
		        if (studentData.getClubId() != null && studentData.getClubId() != 0) {
		            studentData.setClubId(0);
		            studentDao.save(studentData);
		        }
		    }
		    return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
	
	
}
