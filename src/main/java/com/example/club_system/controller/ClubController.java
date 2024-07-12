package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.ClubService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.ClubDeleteReq;
import com.example.club_system.vo.ClubSearchReq;

@CrossOrigin
@RestController
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@PostMapping(value = "Club/createOrUpdate")
	public BasicRes createOrUpdate( @Valid @RequestBody ClubCreateOrUpdateReq req) {
		return clubService.createOrUpdate(req);
	}
	
	@DeleteMapping(value ="Club/delete")
	public BasicRes delete(@RequestBody ClubDeleteReq req) {
		return clubService.delete(req);
	}
	
	@PostMapping(value = "Club/search")
	public BasicRes search(@Valid @RequestBody ClubSearchReq req) {
		return clubService.search(req);
	}
	
	
//	@GetMapping(value ="Club/search/{name}")
//	public ClubSearchRes searchByName(@PathVariable String name) {
//		return clubService.searchByName(name);
//	}
	
//	@GetMapping(value ="Club/search/{teacher_id}")
//	public ClubSearchRes searchByTeacherId(@PathVariable String teacher_id) {
//		return clubService.searchByTeacherId(teacher_id);
//	}
//	
//	
//	@GetMapping(value ="Club/search/{semester}")
//	public ClubSearchRes searchBySemester(@PathVariable String semester) {
//		return clubService.searchBySemester(semester);
//	}
//	
	
	
}
