package com.example.club_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.repository.ClubDao;
import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.CreateOrUpdateReq;
import com.example.club_system.vo.DeleteReq;
import com.example.club_system.vo.SearchReq;
import com.example.club_system.vo.SearchRes;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubDao clubDao;

	@Override
	public BasicRes createOrUpdate(CreateOrUpdateReq req) {
		// 檢查社團id是否存在
		if(clubDao.existsById(req.getClubId())){
			System.out.println(ResMessage.PARAM_CLUB_ID_EXIST.getMessage());
			return new BasicRes(ResMessage.PARAM_CLUB_ID_EXIST.getCode(),//
					ResMessage.PARAM_CLUB_ID_EXIST.getMessage());
		}
		return new BasicRes (ResMessage.SUCCESS.getCode(),//
				ResMessage.SUCCESS.getMessage());
	}

	// 刪除社團資料
	@Override
	public BasicRes delete(DeleteReq req) {
		
		if(!CollectionUtils.isEmpty(req.getIdList())) {
			clubDao.deleteAllById(req.getIdList());
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(),//
				ResMessage.SUCCESS.getMessage());
	}

	// 搜尋社團
	@Override
	public SearchRes search(SearchReq req) {
//		String name = 
		return null;
	}
	
	




}
