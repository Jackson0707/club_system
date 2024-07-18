package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;
import com.example.club_system.vo.StudentdeleteReq;

public interface ClubService {

	public BasicRes createOrUpdate(ClubCreateOrUpdateReq req);
	
	public BasicRes delete(ClubDeleteReq req);
	
	public ClubSearchRes search(ClubSearchReq req);
	
	public BasicRes clubRandom();
	
	public BasicRes resetClubId(StudentdeleteReq req);
	
}
