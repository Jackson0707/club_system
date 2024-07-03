package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.CreateOrUpdateReq;
import com.example.club_system.vo.DeleteReq;
import com.example.club_system.vo.SearchReq;
import com.example.club_system.vo.SearchRes;

@CrossOrigin
@RestController
public class ClubController {

//	@Autowired
	private ClubService clubService;
	
	@PostMapping(value = "Club/create_update")
	public BasicRes createOrUpdate( @Valid @RequestBody CreateOrUpdateReq req) {
		return clubService.createOrUpdate(req);
	}
	
	@PostMapping(value ="Club/delete")
	public BasicRes delete(@RequestBody DeleteReq req) {
		return clubService.delete(req);
	}
	
	@PostMapping(value ="Club/search")
	public SearchRes search(@RequestBody SearchReq req) {
		return clubService.search(req);
	}
}
