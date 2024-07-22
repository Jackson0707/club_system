//package com.example.club_system.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.club_system.service.ifs.VenueService;
//import com.example.club_system.vo.BasicRes;
//import com.example.club_system.vo.VenueCreateOrUpdateReq;
//import com.example.club_system.vo.VenueSearchReq;
//import com.example.club_system.vo.VenueSearchRes;
//
//@RestController
//public class VenueController {
//
//	@Autowired
//	private VenueService venueService;
//
//	@PostMapping(value = "Venue/createOrUpdate")
//	public BasicRes createOrUpdate(@RequestBody VenueCreateOrUpdateReq req) {
//		return venueService.createOrUpdate(req);
//	}
//
//	@PostMapping(value = "Venue/search")
//	public VenueSearchRes search(@RequestBody VenueSearchReq req) {
//		return venueService.search(req);
//	}
//}
