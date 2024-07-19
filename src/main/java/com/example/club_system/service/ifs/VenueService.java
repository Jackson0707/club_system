package com.example.club_system.service.ifs;


import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.VenueCreateOrUpdateReq;
import com.example.club_system.vo.VenueSearchReq;
import com.example.club_system.vo.VenueSearchRes;

public interface VenueService {
	
	public BasicRes createOrUpdate(VenueCreateOrUpdateReq req);
	
	public VenueSearchRes search(VenueSearchReq req);

}
