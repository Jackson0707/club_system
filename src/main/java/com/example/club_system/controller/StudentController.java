package com.example.club_system.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.service.ifs.StudentService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ForgetPwdReq;
import com.example.club_system.vo.StudentGetClubDataReq;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginRes;

//@RestController 包含了 @Controller 和 @ResponseBody
//@Controller 是指將此類別交由 spring boot 託管成 Controller 物件
//@ResponseBody:可以將自定義的物件(Response)轉換成 JSON 格式給外部
//加了 @RestController 後，就不需要在 AddInfoRes(或其他的 xxxRes) 前面加上 @ResponseBody
@CrossOrigin
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = "student/updatePassword")
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
	
	// 學生刪除方法，切記 Postman 方法是 DeleteMapping !!!
	@DeleteMapping(value = "student/delete")
	public BasicRes delete(@Valid @RequestBody StudentdeleteReq req) {
		return studentService.delete(req);
	}
	
	@PostMapping(value = "student/login")
	public BasicRes login(@Valid @RequestBody StudentLoginReq req, HttpSession session) {
		if(session.getAttribute("Account") != null) {
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
		 StudentLoginRes res = studentService.login(req);
		 if(res.getStatusCode() == 200) {
			 session.setAttribute("Account", req.getStudentId());
		 }
		return res;
	}
	
	 @GetMapping(value = "student/logout")
	    public BasicRes logout(HttpSession session) {
	        session.invalidate(); // 清除所有属性，包括用户資訊
	        return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	    }
	
	//學生端登入後，取得學生社團資訊方法
	@PostMapping(value = "student/studentClubData")
	public StudentLoginRes clubStudentData(@Valid @RequestBody StudentGetClubDataReq req) {
		return studentService.studentGetClubData(req);
	}
	
	// 忘記密碼驗證
	@PostMapping(value = "student/pwdValidation")
	public StudentLoginRes forgotPwd(@Valid @RequestBody ForgetPwdReq req) {
		return studentService.forgotPwd(req);
	}
}
