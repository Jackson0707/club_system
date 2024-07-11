package com.example.club_system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class ClubServiceTests {

	@Autowired
	ClubDao clubDao;

	@Autowired
	StudentDao studentDao;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void clubRandom() {

		// �ŧi�ܼơA�������Υi�W�[�W�B�A�@�}�l����Ҧ����Ϊ��̤j�W���H�ơA�� HashMap �x�s
		// ����X�Ҧ��ǥͪ���ơA�N club ���@�Ǧs��}�C��
		// �ŧi�@�Ӱ}�C�A�x�s�w�g������id���ǥ�
		// HashMap �Ĥ@��(�Ĥ@���@��)�G����id�@�� key�A�ǥ�id �@�� value�A
		// �H�}�C���覡�x�s�� 3:[23, 25, ] 2:[36]
		// if �}�C���רS�W�L�A�N���Ω��ҡA�ΰ}�C���״�h�i�e�Ǫ��סA�s�^�������ΦW�B���ܼƤ�
		// ���ο�ܤH�ƶW�L���ΤW���H�ơA�H������ثe���γѾl���u��
		// ���ΤH�ƺ��H�A���γѾl�H���k�s�A�b�ĤG����襤�N�|�����L�Ӫ���(���ҡA�ñN���Υi�e�ǤH���k�s)
		// �ĤG���A�p�G�ǥ�id�b�}�C�̭��A�N���L�C�̲ĤG���@�ǡA�s�� HashMap
		// �P�_�C�Ӫ��Υi�_�A�e�ǤH
		// �S���A�N���L�Ӫ��Ϊ�����
		// ���A���ҡA���Ȯɥ�Entry

		// ���ΤW���H��
		List<Club> clubs = clubDao.findAll(); // �����XClub�o��entity�����h����ƪ��Ҧ���
		Map<Integer, Integer> clubMax = new HashMap<>();
		for (int i = 0; i < clubs.size(); i++) { // ����for�j���Ҧ���ƦC�X��
			Club club = clubs.get(i); // �Τ@���ܼƨӱ�for�j���z�X�Ӫ����
			clubMax.put(club.getClubId(), club.getMax()); // �Q�� .put�\���Q�n��p���ȥ[�imap�̭�
		}
		System.out.println(clubMax);

		// �ǥͧ��@��
		List<Student> studentChoice = studentDao.findAll();
		// �U������Integer[]�A�쥻�OString�A���]���e�ݶǶi��Ʈw�������A���}�C�A���]���n��o�নint�|���e���s����۹���������Id
		// �]���}�C����ƫ��A��String�A�N�L�j���૬�ݭn�b[]�e�֥[�WInteger
		Map<Integer, Integer[]> studentChoiceArr = new HashMap<>();
		for (int i = 0; i < studentChoice.size(); i++) {
			Student student = studentChoice.get(i);
			Integer[] choiceArr; // �ŧi�@�Ӱ}�C�ӱ���
			try {
				choiceArr = mapper.readValue(student.getChoiceList(), Integer[].class);
				studentChoiceArr.put(student.getStudentId(), choiceArr);
			} catch (JsonProcessingException e) {
				System.out.println("JsonProcessingException");
			}

		}
		try {
			System.out.println(mapper.writeValueAsString(studentChoiceArr));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �w�g��Id���ǥ�
		List<Student> clubStudentValue = new ArrayList<>();
		HashMap<Integer, Integer[]> clubStudentArr = new HashMap<>();
		Set<Entry<Integer, Integer[]>> studentChoiceArrSet = studentChoiceArr.entrySet();
		
		for (Entry<Integer, Integer[]> item : studentChoiceArrSet) {
			item.getKey();  // �ǥ�ID
			item.getValue();// ���Χ��@��
			Integer[] clubStudentAllId = new Integer[studentChoice.size()];
			if(!clubStudentArr.containsKey(item.getValue()[0])) {
				clubStudentAllId[0] = item.getKey();
				clubStudentArr.put(item.getValue()[0],clubStudentAllId);
			}
			else { 
				clubStudentAllId = clubStudentArr.get(item.getValue()[0]);
			}
		}
		System.out.println(clubStudentArr);
		
		
		
		
		// ��@����
		// HashMap �Ĥ@��(�Ĥ@���@��)�G����id�@�� key�A�ǥ�id �@�� value�A
		// �H�}�C���覡�x�s�� 3:[23, 25, ] 2:[36]
		// if �}�C���רS�W�L�A�N���Ω��ҡA�ΰ}�C���״�h�i�e�Ǫ��סA�s�^�������ΦW�B���ܼƤ�
		// ���ο�ܤH�ƶW�L���ΤW���H�ơA�H������ثe���γѾl���u��
		// ���ΤH�ƺ��H�A���γѾl�H���k�s�A�b�ĤG����襤�N�|�����L�Ӫ���(���ҡA�ñN���Υi�e�ǤH���k�s)
		// �ĤG���A�p�G�ǥ�id�b�}�C�̭��A�N���L�C�̲ĤG���@�ǡA�s�� HashMap
		// �P�_�C�Ӫ��Υi�_�A�e�ǤH
		// �S���A�N���L�Ӫ��Ϊ�����
		// ���A���ҡA���Ȯɥ�Entry
		
		
		Map<Integer, ArrayList<Integer>> firstRandom = new HashMap<>();

		// �C�Ӫ��Ϊ��ǥͦC��
		for (Integer clubId : clubMax.keySet()) {
		    firstRandom.put(clubId, new ArrayList<>());
		}

		// �M���Ҧ��ǥ͡A�N�L�̪��Ĥ@���@�[����������ΦC��
		for (Entry<Integer, Integer[]> entry : studentChoiceArr.entrySet()) {
		    Integer studentId = entry.getKey();
		    Integer[] choices = entry.getValue();
		    
		    if (choices.length > 0) {
		        Integer firstChoice = choices[0];
		        firstRandom.get(firstChoice).add(studentId);
		    }
		}
		System.out.println(firstRandom +"����");

		return;
	}

}
