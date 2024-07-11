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
					clubDao.delete(id);
				}
				
			} catch (Exception e) {
				// �� deleteAllById ��k���Aid ���Ȥ��s�b�AJPA �|����
				// �]���b�R�����e�A JPA�|���j�a�J�� id �ȡA�Y�S���G�N�|����
				// �ѩ��ڤW�]�S�R�������ơA�]���N���ݭn��o��Exception ���B�z
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

//	// �j�M����
//	@Override
//	public ClubSearchRes search(ClubSearchReq req) {
//		String name = req.getName();
//
//		String semester = req.getSemester();
//		
//		int teacherId = req.getTeacherId();
//
//		// ���] name �O null �άO���ťզr��A�i�H�����S����J����ȡA�N��ܭn���o����
//		// JPA �� containing ��k�A����ȬO�Ŧr��ɡA�|�j�M����
//		// �ҥH�n�� name ���ȬO null �άO���ťզr��ɡA�ഫ���Ŧr��
//		if (!StringUtils.hasText(name)) {
//			name = "";
//		}
//		if (!StringUtils.hasText(semester)) {
//			semester = "";
//		}
//		if(teacherId == 0) {
//			teacherId = req.getTeacherId();
//		}
//		return new ClubSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(),
//				clubDao.findByNameContainingNameAndTeacherIdAndSemester(name, semester, teacherId));
//	}

	@Override
	public ClubSearchRes searchByName(String name) {
		
		ClubSearchRes clubSearchRes = new ClubSearchRes();
		System.out.println(clubDao.selectByName(name));
		clubSearchRes.setClubList(clubDao.selectByName(name));
		
		return  clubSearchRes;
	}

//	@Override
//	public ClubSearchRes searchByTeacherId(String teacher_id) {
//		ClubSearchRes clubSearchRes = new ClubSearchRes();
//		System.out.println(clubDao.selectByTeacherId(teacher_id));
//		clubSearchRes.setClubList(clubDao.selectByTeacherId(teacher_id));
//		
//		return  clubSearchRes;
//	}
//
//	@Override
//	public ClubSearchRes searchBySemester(String semester) {
//		ClubSearchRes clubSearchRes = new ClubSearchRes();
//		System.out.println(clubDao.selectBySemester(semester));
//		clubSearchRes.setClubList(clubDao.selectBySemester(semester));
//		
//		return  clubSearchRes;
//	}

}
