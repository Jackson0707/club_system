package com.example.club_system.service.ifs;

import java.util.List;

import com.example.club_system.entity.Club;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;

public interface ClubService {

	public BasicRes createOrUpdate(ClubCreateOrUpdateReq req);
	
	public BasicRes delete(ClubDeleteReq req);
	
	public ClubSearchRes search(ClubSearchReq req);
	
	public BasicRes clubRandom();

//	public ClubSearchRes searchClubs(ClubSearchReq req);

//	List<Club> searchSql(String name, int teacherId, String semester);
	
}
