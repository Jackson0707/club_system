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
		List<Club> clubs = clubDao.findAll(); // �����XClub�o��entity�����h����ƪ��Ҧ���
		Map<Integer, Integer> clubMax = new HashMap<>();
		for(int i = 0; i<clubs.size(); i++) { // ����for�j���Ҧ���ƦC�X��
			 Club club = clubs.get(i); // �Τ@���ܼƨӱ�for�j���z�X�Ӫ����
			 clubMax.put(club.getClubId(), club.getMax()); // �Q�� .put�\���Q�n��p���ȥ[�imap�̭�
		}
		System.out.println(clubMax);
	}
}
