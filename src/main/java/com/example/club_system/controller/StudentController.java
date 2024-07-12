package com.example.club_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentUpdataPwdReq;

//@RestController �]�t�F @Controller �M @ResponseBody
//@Controller �O���N�����O��� spring boot �U�ަ� Controller ����
//@ResponseBody:�i�H�N�۩w�q������(Response)�ഫ�� JSON �榡���~��
//�[�F @RestController ��A�N���ݭn�b AddInfoRes(�Ψ�L�� xxxRes) �e���[�W @ResponseBody
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = "student/updata_password")
	public BasicRes updataPwd(@Valid @RequestBody StudentUpdataPwdReq req) {
		return studentService.updataPwd(req.getStudentId(), req.getOldPwd(), req.getNewPwd());
	}
}
