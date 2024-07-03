package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.CreateOrUpdateReq;
import com.example.club_system.vo.DeleteReq;
import com.example.club_system.vo.SearchReq;
import com.example.club_system.vo.SearchRes;

public interface ClubService {

	public BasicRes createOrUpdate(CreateOrUpdateReq req);
	
	public BasicRes delete(DeleteReq req);
	
	public SearchRes search(SearchReq req);
}
