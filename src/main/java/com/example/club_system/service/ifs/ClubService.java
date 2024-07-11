package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;

public interface ClubService {

	public BasicRes createOrUpdate(ClubCreateOrUpdateReq req);
	
	public BasicRes delete(ClubDeleteReq req);
	
//	public ClubSearchRes search(ClubSearchReq req);
	
	public ClubSearchRes searchByName(String name);
	
//	public ClubSearchRes searchByTeacherId(String teacher_id);
	
//	public ClubSearchRes searchBySemester(String semester);
}
