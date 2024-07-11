package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.TeacherDatabaseService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherLoginReq;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherUpdatePwdReq;

//@RestController 包含了 @Controller 和 @ResponseBody
//@Controller 是指將此類別交由 spring boot 託管成 Controller 物件
//@ResponseBody:可以將自定義的物件(Response)轉換成 JSON 格式給外部
//加了 @RestController 後，就不需要在 AddInfoRes(或其他的 xxxRes) 前面加上 @ResponseBody
@RestController
public class TeacherDatabaseController {

	@Autowired
	private TeacherDatabaseService teacherDatabaseService;

	@PostMapping(value = "teacherDatabase/updata_password")
	public BasicRes updataPwd(@Valid @RequestBody TeacherUpdatePwdReq req) {
		return teacherDatabaseService.updatePwd(req.getTeacherId(), req.getOldPwd(), req.getNewPwd());
	}

	@PostMapping(value = "teacherDatabase/login")
	public BasicRes login(@Valid @RequestBody TeacherLoginReq req) {
		return teacherDatabaseService.login(req.getTeacherId(), req.getPwd());
	}

	@PostMapping(value = "teacherDatabase/createOrUpdate")
	public BasicRes createOrUpdate(@Valid @RequestBody TeacherDatabaseCreateOrUpdateReq req) {
		return teacherDatabaseService.createOrUpdate(req);
	}

	@PostMapping(value = "teacherDatabase/search")
	public BasicRes search(@Valid @RequestBody TeacherSearchReq req) {
		return teacherDatabaseService.search(req);
	}

	@PostMapping(value = "teacherDatabase/delete")
	public BasicRes delete(@Valid @RequestBody TeacherDeleteReq req) {
		return teacherDatabaseService.delete(req);
	}
	
	@PostMapping(value = "teacherDatabase/loginAdmin")
	public BasicRes loginAdmin(@Valid @RequestBody TeacherLoginReq req) {
		return teacherDatabaseService.login(req.getTeacherId(), req.getPwd());
	}
}
