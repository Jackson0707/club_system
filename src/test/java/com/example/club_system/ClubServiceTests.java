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
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.entity.Student;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.repository.StudentDao;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;
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
		List<Club> clubs = clubDao.findAll(); // ���d��Club�o��entity�����h����ƪ��Ҧ���
		Map<Integer, Integer> clubMax = new HashMap<>();
		for (int i = 0; i < clubs.size(); i++) { // ����for�j���Ҧ���ƦC�X��
			Club club = clubs.get(i); // �Τ@���ܼƨӱ�for�j���z�X�Ӫ���
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
				System.out.println(studentChoiceArr);
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
			item.getKey(); // �ǥ�ID
			item.getValue();// ���Χ��@��

			Integer[] clubStudentAllId = new Integer[studentChoice.size()];
			if (!clubStudentArr.containsKey(item.getValue()[0])) {
				clubStudentAllId[0] = item.getKey();
				clubStudentArr.put(item.getValue()[0], clubStudentAllId);
			} else {
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
		System.out.println(firstRandom + "����");

		return;
	}

	@Test
	public void clubRandom1() {

		// �ǥͧ��@�� key:�Ǹ�, value:���@��
		HashMap<Integer, Integer[]> studentChoiceMap = new HashMap<>();
		List<Student> studentList = studentDao.findAll();
		for (int i = 0; i < studentList.size(); i++) {
			Student studentData = studentList.get(i);
			Integer[] choiceArr;
			try {
				choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
				System.out.println("��Ʀ���");
//		            return new BasicRes(ResMessage.FAIL.getCode(), "�ǥ͸�ƳB�z���~");
			}
		}

		// �ˬd�U���Ϊ��W���H��
		HashMap<Integer, Integer> clubMaxMap = new HashMap<>();
		List<Club> clubList = clubDao.findAll();
		for (int i = 0; i < clubList.size(); i++) {
			Club clubData = clubList.get(i);
			clubMaxMap.put(clubData.getClubId(), clubData.getMax());
		}

		// �s�x���t���G
		HashMap<Integer, Integer> drawResult = new HashMap<>(); // key: �ǥ�Id, value: ����Id

		// �Ыؾǥ�Id���C��A�Ω��H�����
		List<Integer> studentDrawList = new ArrayList<>(studentChoiceMap.keySet());

		// �H�����t�ǥͨ����

		while (!studentDrawList.isEmpty()) {
			// �H����ܤ@�Ӿǥ�
			int randomIndex = (int) (Math.random() * studentDrawList.size());
			int studentId = studentDrawList.get(randomIndex);
			Integer[] choices = studentChoiceMap.get(studentId);

			boolean assigned = false;
			for (int clubId : choices) {
				int drawClubMax = clubMaxMap.get(clubId);
				if (drawClubMax > 0) {
					// ���t�ǥͨ�o�Ӫ���
					drawResult.put(studentId, clubId);
					clubMaxMap.put(clubId, drawClubMax - 1);
					assigned = true;
					break;
				}
			}

			if (assigned) {
				studentDrawList.remove(randomIndex);
			} else {
				// �p�G�ǥ͵L�k�Q���t�������@���ΡA�i�H�b�o�̳B�z
				System.out.println("�ǥ� " + studentId + " �L�k�Q���t�������@����");
				studentDrawList.remove(randomIndex);
			}
		}
		System.out.println(drawResult);

//		    // ��s�ƾڮw
//		    for (Map.Entry<Integer, Integer> entry : assignedStudents.entrySet()) {
//		        int studentId = entry.getKey();
//		        int clubId = entry.getValue();
//		        studentDao.save(studentId, clubId);
//		    }

//		    return new BasicRes(ResMessage.SUCCESS.getCode(), "���Τ��t����");
	}

	@Test
	public void random() {

		// �ǥͧ��@�� key:�Ǹ�, value:���@��
		HashMap<Integer, Integer[]> studentChoiceMap = new HashMap<>();
		List<Student> studentList = studentDao.findAll();
		for (Student studentData : studentList) {
			try {
				Integer[] choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
				System.out.println("��Ʀ���: " + studentData.getStudentId());
			}
		}

		// �ˬd�U���Ϊ��W���H��
		HashMap<Integer, Integer> clubMaxMap = new HashMap<>();
		List<Club> clubList = clubDao.findAll();
		for (Club clubData : clubList) {
			clubMaxMap.put(clubData.getClubId(), clubData.getMax());
		}

		// �s�x���ҵ��G
		HashMap<Integer, Integer> drawResult = new HashMap<>(); // key: �ǥ�Id, value: ����Id

		// �Ыؾǥ�Id���C��A�Ψ��H����ܡA����ǥ�Id
		List<Integer> studentDrawList = new ArrayList<>(studentChoiceMap.keySet());

		// �H�����t�ǥͨ����
		while (!studentDrawList.isEmpty()) {
			// �H����ܤ@�Ӿǥ�
			int randomIndex = (int) (Math.random() * studentDrawList.size());
			int studentId = studentDrawList.get(randomIndex);

			Integer[] choices = studentChoiceMap.get(studentId);
			boolean assigned = false;

			// �p�G�ǥ�
//			if(studentChoiceMap.entrySet()) {
//				
//			}
			for (int clubId : choices) {
				int clubMax = clubMaxMap.get(clubId);
				if (clubMax > 0) {
					// ���t�ǥͨ�o�Ӫ���
					drawResult.put(studentId, clubId);
					clubMaxMap.put(clubId, clubMax - 1);
//					assigned = true;
					break;
				}
			}

			if (assigned) {
				studentDrawList.remove(randomIndex);
			} else {
				// �p�G�ǥ͵L�k�Q���t�������@���ΡA�i�H�b�o�̳B�z
				System.out.println("�ǥ� " + studentId + " �L�k�Q���t�������@����");
				studentDrawList.remove(randomIndex);
			}
		}

		System.out.println(drawResult);
	}

	@Test
	public void random1() {
		// �ǥͧ��@�� key:�Ǹ�, value:���@��
		HashMap<Integer, Integer[]> studentChoiceMap = new HashMap<>();
		List<Student> studentList = studentDao.findAll();
		for (Student studentData : studentList) {
			try {
				Integer[] choiceArr = mapper.readValue(studentData.getChoiceList(), Integer[].class);
				studentChoiceMap.put(studentData.getStudentId(), choiceArr);
			} catch (Exception e) {
				System.out.println("��Ʀ���: " + studentData.getStudentId());
			}
		}

		// �ˬd�U���Ϊ��W���H��
		HashMap<Integer, Integer> clubMaxMap = new HashMap<>();
		List<Club> clubList = clubDao.findAll();
		for (Club clubData : clubList) {
			clubMaxMap.put(clubData.getClubId(), clubData.getMax());
		}

		// �s�x���ҵ��G
		HashMap<Integer, Integer> drawResult = new HashMap<>(); // key: �ǥ�Id, value: ����Id

		// �Ыؾǥ�Id���C��A�Ψ��H����ܡA����ǥ�Id
		List<Integer> studentDrawList = new ArrayList<>(studentChoiceMap.keySet());
		
		// �Ыؤ@�ӥi�H�~����t�����ΦC��
		List<Integer> availableClubs = new ArrayList<>(clubMaxMap.keySet());

		while (!studentDrawList.isEmpty() && !availableClubs.isEmpty()) {
		    // �H����ܤ@�Ӿǥ�
		    int randomStudentIndex = (int) (Math.random() * studentDrawList.size());
		    int studentId = studentDrawList.get(randomStudentIndex); // �H���⪺�ǥ�Id
		    Integer[] studentChoices = studentChoiceMap.get(studentId); // Map���۹������ǥ�Id

//		    boolean studentAssigned = false;
		    
		    

		    System.out.println(randomStudentIndex);
		    // ���ޭȱq0�}�l�M���ǥͪ����@
		    for (int i = 0; i < studentChoices.length; i++) {
		        int clubId = studentChoices[i];
		        System.out.printf("�� %d �ӧ��@��: %d \n", i, clubId);
		        // �ˬd�o�Ӫ��άO�_�٦��Ŧ�
		        System.out.println("�����@�ǬO�_�i�i�J?"+availableClubs.contains(clubId));
		        if (availableClubs.contains(clubId)) {
		            // ���t�ǥͨ�o�Ӫ���
		            drawResult.put(studentId, clubId);
		            
		            // ��s���γѾl�W�B
		            int remainingSpots = clubMaxMap.get(clubId) - 1;
		            clubMaxMap.put(clubId, remainingSpots);
		            
		            // �p�G���κ��F�A�q�i�Ϊ��ΦC������
		            if (remainingSpots == 0) {
		            	System.out.println("�ڶi�ӤF");
		                availableClubs.remove(availableClubs.indexOf(clubId));
		            }
		            System.out.println("�����γѾl�i�e�ǤH��: "+remainingSpots);
		            System.out.println("�����Υi�_�A�e�ǤH�H"+availableClubs.contains(clubId));
		            break;
		        }
		    }

		    // �q�ݤ��t�C�������Ӿǥ�
		    studentDrawList.remove(randomStudentIndex);

		    // �p�G�ǥͥ��Q���t�A��X�T��
//    if (!studentAssigned) {
//       System.out.println("�ǥ� " + studentId + " �S������");
//		    }
		    System.out.println(drawResult);
//		}
	
		
		  // ��s�ƾڮw
//	    for (Map.Entry<Integer, Integer> entry : drawResult.entrySet()) {
//	        int studentId = entry.getKey();
//	        int clubId = entry.getValue();
//	        List<Student> finalResult = new ArrayList<>();
//	        finalResult.addAll(studentList);
//	        
//	        studentDao.saveAll(null);
	    }
		
	}
}
