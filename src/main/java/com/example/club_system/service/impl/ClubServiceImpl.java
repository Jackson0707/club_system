package com.example.club_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.entity.Club;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherSearchRes;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubDao clubDao;

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
		
//		int teacherId = req.getTeacherId();

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

}
