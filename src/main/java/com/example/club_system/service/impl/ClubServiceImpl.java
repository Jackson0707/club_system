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
		
//		int teacherId = req.getTeacherId();

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

}
