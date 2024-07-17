package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;

//@RestController �]�t�F @Controller �M @ResponseBody
//@Controller �O���N�����O��� spring boot �U�ަ� Controller ����
//@ResponseBody:�i�H�N�۩w�q������(Response)�ഫ�� JSON �榡���~��
//�[�F @RestController ��A�N���ݭn�b AddInfoRes(�Ψ�L�� xxxRes) �e���[�W @ResponseBody
@CrossOrigin
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = "student/updata_password")
	public BasicRes updataPwd(@Valid @RequestBody StudentUpdataPwdReq req) {
		return studentService.updataPwd(req);
	}
	
	@PostMapping(value = "student/createOrUpdate")
	public BasicRes createOrUpdate(@Valid @RequestBody StudentcreateOrUpdateReq req) {
		return studentService.createOrUpdate(req);
	}
	
	@PostMapping(value = "student/search")
	public BasicRes search(@Valid @RequestBody StudentsearchReq req) {
		return studentService.search(req);
	}
	
	@PostMapping(value = "student/delete")
	public BasicRes delete(@Valid @RequestBody StudentdeleteReq req) {
		return studentService.delete(req);
	}
	
	@PostMapping(value = "student/login")
	public BasicRes login(@Valid @RequestBody StudentLoginReq req) {
		return studentService.login(req);
	}
}
