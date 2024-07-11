package com.example.club_system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.vo.BasicRes;
import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest
public class ClubServiceTests {

	@Autowired
	ClubDao clubDao;
	
	@Autowired
	StudentDao studentDao;
	
	@Test
	public void clubMax(){
		List<Club> clubs = clubDao.findAll(); // 先取出Club這個entity中的多筆資料的所有值
		Map<Integer, Integer> clubMax = new HashMap<>();
		for(int i = 0; i<clubs.size(); i++) { // 先用for迴圈把所有資料列出來
			 Club club = clubs.get(i); // 用一個變數來接for迴圈整理出來的資料
			 clubMax.put(club.getClubId(), club.getMax()); // 利用 .put功能把想要塞如的值加進map裡面
		}
		System.out.println(clubMax);
	}
}
