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

	// �ЫؤΧ�s���θ��
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

	// �R�����θ��
	@Override
	public BasicRes delete(ClubDeleteReq req) {
		
		if (!CollectionUtils.isEmpty(req.getIdList())) {
			// �R���ݨ�
			try {
				for(Integer id : req.getIdList()) {
					System.out.println(id);
					clubDao.deleteById(id);;
				}
				
			} catch (Exception e) {
				// �� deleteAllById ��k���Aid ���Ȥ��s�b�AJPA �|����
				// �]���b�R�����e�A JPA�|���j�a�J�� id �ȡA�Y�S���G�N�|����
				// �ѩ��ڤW�]�S�R�������ơA�]���N���ݭn��o��Exception ���B�z
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	// �j�M����
	@Override
	public ClubSearchRes search(ClubSearchReq req) {
		
		int clubId = req.getClubId();
		
		String name = req.getName();

		String semester = req.getSemester();
		
		int teacherId = req.getTeacherId();

		// ���] name �O null �άO���ťզr��A�i�H�����S����J����ȡA�N��ܭn���o����
		// JPA �� containing ��k�A����ȬO�Ŧr��ɡA�|�j�M����
		// �ҥH�n�� name ���ȬO null �άO���ťզr��ɡA�ഫ���Ŧr��
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

		// ���M���C�Ӿǥͪ����@��
		// �d�ݨC�Ӫ��ΤH�ƤW��
		// if���ΤH�ư��A���ΤH�ƴ�1�Ahashmap�skey�ǥͬO���� value����
		// else�H�Ƥ����A�ݲĤG���@���ΤH�ƤW��

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
			int randomChooseStudent = (int) (Math.random() * studentDrawList.size());
			int randomStudentId = studentDrawList.get(randomChooseStudent); // �H���⪺�ǥ�Id
			Integer[] randomStudentChoices = studentChoiceMap.get(randomStudentId); // ���oMap���۹������ǥ�Id�����@��

			System.out.println(randomChooseStudent);
			// ���ޭȱq0�}�l�M���ǥͪ����@
			for (int i = 0; i < randomStudentChoices.length; i++) {
				int clubId = randomStudentChoices[i];
				System.out.printf("�� %d �ӧ��@��: %d \n", i, clubId);
				// �ˬd�o�Ӫ��άO�_�٦��Ŧ�
				System.out.println("�����@�ǬO�_�i�i�J?" + availableClubs.contains(clubId));
				if (availableClubs.contains(clubId)) {
					// ���t�ǥͨ�o�Ӫ���
					drawResult.put(randomStudentId, clubId);

					// ��s���γѾl�W�B
					int remainingSpots = clubMaxMap.get(clubId) -1;
					clubMaxMap.put(clubId, remainingSpots);

					// �p�G���κ��F�A�q�i�Ϊ��ΦC������
					if (remainingSpots == 0) {
						System.out.println("�ڶi�ӤF");
						availableClubs.remove(availableClubs.indexOf(clubId));
					}
					System.out.println("�����γѾl�i�e�ǤH��: " + remainingSpots);
					System.out.println("�����Υi�_�A�e�ǤH�H" + availableClubs.contains(clubId));
					break;
				}
			}

			// �q�ݤ��t�C�������Ӿǥ�
			studentDrawList.remove(randomChooseStudent);
			
			System.out.println(drawResult);
		}

		 // ��s�ƾڮw
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
