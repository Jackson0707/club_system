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
	
//	 社團搜尋功能(還在測試一個小bug)
	@PostMapping(value = "Club/search")
	public BasicRes search(@RequestBody ClubSearchReq req) {
		return clubService.search(req);
	}
	
//	 @GetMapping(value="Club/search")
//	    public ClubSearchRes searchClubs(ClubSearchReq req) {
//	        return clubService.searchClubs(req);
//	    }
//	
	
	
	// 抽籤功能
	@PostMapping(value = "Club/random")
	public BasicRes clubRandom() {
		return clubService.clubRandom();
	}
	

	
//	@GetMapping(value ="Club/search/{name}")
//	public ClubSearchRes searchByName(@PathVariable String name) {
//		return clubService.searchByName(name);
//	}
	
//	@GetMapping(value = "Club/search1")
//    public ResponseEntity<List<Club>> search(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) int teacherId,
//            @RequestParam(required = false) String semester) {
//        return (ResponseEntity<List<Club>>) clubService.search1(name, teacherId, semester);
//    }

	
	
}
