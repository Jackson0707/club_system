package com.example.club_system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ClubServiceTests {

	@Autowired
	ClubDao clubDao;

	@Autowired
	StudentDao studentDao;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	// 用社團去選學生(未完成)
	public void clubRandom() {

		// 宣告變數，紀錄社團可增加名額，一開始抓取所有社團的最大上限人數，用 HashMap 儲存
		// 先抓出所有學生的資料，將 club 志願序存到陣列中
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
		List<Club> clubs = clubDao.findAll(); // 先查詢Club這個entity中的多筆資料的所有值
		Map<Integer, Integer> clubMax = new HashMap<>();
		for (int i = 0; i < clubs.size(); i++) { // 先用for迴圈把所有資料列出來
			Club club = clubs.get(i); // 用一個變數來接for迴圈整理出來的值
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
				System.out.println("JsonProcessingException");
				System.out.println(studentChoiceArr);
			}

		}
		try {
			System.out.println(mapper.writeValueAsString(studentChoiceArr));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 已經有Id的學生
		List<Student> clubStudentValue = new ArrayList<>();
		HashMap<Integer, Integer[]> clubStudentArr = new HashMap<>();
		Set<Entry<Integer, Integer[]>> studentChoiceArrSet = studentChoiceArr.entrySet();

		for (Entry<Integer, Integer[]> item : studentChoiceArrSet) {
			item.getKey(); // 學生ID
			item.getValue();// 社團志願序

			Integer[] clubStudentAllId = new Integer[studentChoice.size()];
			if (!clubStudentArr.containsKey(item.getValue()[0])) {
				clubStudentAllId[0] = item.getKey();
				clubStudentArr.put(item.getValue()[0], clubStudentAllId);
			} else {
				clubStudentAllId = clubStudentArr.get(item.getValue()[0]);
			}
		}
		System.out.println(clubStudentArr);

		// 實作抽籤
		// HashMap 第一輪(第一志願序)：社團id作為 key，學生id 作為 value，
		// 以陣列的方式儲存成 3:[23, 25, ] 2:[36]
		// if 陣列長度沒超過，就不用抽籤，用陣列長度減去可容納長度，存回紀錄社團名額的變數中
		// 社團選擇人數超過社團上限人數，隨機抽取目前社團剩餘的席次
		// 社團人數滿人，社團剩餘人數歸零，在第二輪塞選中就會接跳過該社團(抽籤，並將社團可容納人數歸零)
		// 第二輪，如果學生id在陣列裡面，就跳過。依第二志願序，存到 HashMap
		// 判斷每個社團可否再容納人
		// 沒有，就跳過該社團的抽籤
		// 有，抽籤，取值時用Entry

		Map<Integer, ArrayList<Integer>> firstRandom = new HashMap<>();

		// 每個社團的學生列表
		for (Integer clubId : clubMax.keySet()) {
			firstRandom.put(clubId, new ArrayList<>());
		}

		// 遍歷所有學生，將他們的第一志願加到對應的社團列表中
		for (Entry<Integer, Integer[]> entry : studentChoiceArr.entrySet()) {
			Integer studentId = entry.getKey();
			Integer[] choices = entry.getValue();

			if (choices.length > 0) {
				Integer firstChoice = choices[0];
				firstRandom.get(firstChoice).add(studentId);
			}
		}
		System.out.println(firstRandom + "結束");

		return;
	}


	// 隨機抽選生，從志願序選社團
	@Test
	public void random() {

		// 學生志願序 key:學號, value:志願序
		HashMap<Integer, Integer[]> studentChoiceMap = new HashMap<>();
		List<Student> studentList = studentDao.findAll();
		for (Student studentData : studentList) {
			try {
				Integer[] choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
//				System.out.println("資料有錯: " + studentData.getStudentId());
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

		// 隨機分配學生到社團
		while (!studentDrawList.isEmpty()) {
			// 隨機選擇一個學生
			int randomIndex = (int) (Math.random() * studentDrawList.size());
			int studentId = studentDrawList.get(randomIndex);

			Integer[] choices = studentChoiceMap.get(studentId);
			boolean assigned = false;

			for (int clubId : choices) {
				int clubMax = clubMaxMap.get(clubId);
				if (clubMax > 0) {
					// 分配學生到這個社團
					drawResult.put(studentId, clubId);
					clubMaxMap.put(clubId, clubMax - 1);
//					assigned = true;
					break;
				}
			}

			if (assigned) {
				studentDrawList.remove(randomIndex);
			} else {
				// 如果學生無法被分配到任何志願社團，可以在這裡處理
//				System.out.println("學生 " + studentId + " 無法被分配到任何志願社團");
				studentDrawList.remove(randomIndex);			   

			}
		}
		// drawResult 放了抽籤結果，key: 學號, value: 社團Id
		// 更新數據庫
		for (Student student : studentList) {
	        Integer assignedClubId = drawResult.get(student.getStudentId());
	        if (assignedClubId != null) {
	            student.setClubId(assignedClubId);
	        } else {
	            // 如果學生沒有被分配到社團，設置一個特殊值（例如 0）
	            student.setClubId(0);
	        }
	    }
	    
		 studentDao.saveAll(studentList);
		// 保存更新後的學生信息到數據庫
			System.out.println("抽籤結果:" + drawResult);
	}

}
