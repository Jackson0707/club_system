package com.example.club_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.entity.Club;
import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;
import com.example.club_system.vo.ClubSearchRes;
import com.example.club_system.vo.StudentdeleteReq;

@CrossOrigin
@RestController
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	// 社團創建與更新
	@PostMapping(value = "Club/createOrUpdate")
	public BasicRes createOrUpdate( @Valid @RequestBody ClubCreateOrUpdateReq req) {
		return clubService.createOrUpdate(req);
	}
	
	// 社團刪除功能
	@DeleteMapping(value ="Club/delete")
	public BasicRes delete(@RequestBody ClubDeleteReq req) {
		return clubService.delete(req);
	}
	
//	 社團搜尋功能
	@PostMapping(value = "Club/search")
	public BasicRes search(@RequestBody ClubSearchReq req) {
		return clubService.search(req);
	}
	
	// 抽籤功能
	@PostMapping(value = "Club/random")
	public BasicRes clubRandom() {
		return clubService.clubRandom();
	}

	@PostMapping(value = "Club/resetClubId")
	public BasicRes reset(@RequestBody StudentdeleteReq req) {
		return clubService.resetClubId(req);
	}
	
}
