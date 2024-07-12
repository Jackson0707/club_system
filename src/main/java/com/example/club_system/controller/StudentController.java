package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentUpdataPwdReq;

//@RestController 包含了 @Controller 和 @ResponseBody
//@Controller 是指將此類別交由 spring boot 託管成 Controller 物件
//@ResponseBody:可以將自定義的物件(Response)轉換成 JSON 格式給外部
//加了 @RestController 後，就不需要在 AddInfoRes(或其他的 xxxRes) 前面加上 @ResponseBody
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = "student/updata_password")
	public BasicRes updataPwd(@Valid @RequestBody StudentUpdataPwdReq req) {
		return studentService.updataPwd(req.getStudentId(), req.getOldPwd(), req.getNewPwd());
	}
}
