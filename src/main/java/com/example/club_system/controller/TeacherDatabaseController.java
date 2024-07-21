package com.example.club_system.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.club_system.constants.ResMessage;
import com.example.club_system.service.ifs.TeacherDatabaseService;
import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ForgetPwdReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherForgotPwdReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginReq;
import com.example.club_system.vo.TeacherLoginRes;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherUpdatePwdReq;

//@RestController 包含了 @Controller 和 @ResponseBody
//@Controller 是指將此類別交由 spring boot 託管成 Controller 物件
//@ResponseBody:可以將自定義的物件(Response)轉換成 JSON 格式給外部
//加了 @RestController 後，就不需要在 AddInfoRes(或其他的 xxxRes) 前面加上 @ResponseBody
@CrossOrigin
@RestController
public class TeacherDatabaseController {

	@Autowired
	private TeacherDatabaseService teacherDatabaseService;

	@PostMapping(value = "teacherDatabase/updatePassword")
	public BasicRes updataPwd(@Valid @RequestBody TeacherUpdatePwdReq req) {
		return teacherDatabaseService.updatePwd(req.getTeacherId(), req.getOldPwd(), req.getNewPwd());
	}

	@PostMapping(value = "teacherDatabase/login")
	public BasicRes login(@Valid @RequestBody TeacherLoginReq req, HttpSession session) {
		if(session.getAttribute("Account") != null) {
			return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
		}
		TeacherLoginRes res = teacherDatabaseService.login(req);
		 if(res.getStatusCode() == 200) {
			 session.setAttribute("Account", req.getTeacherId());
		 }
		return res;
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
		return teacherDatabaseService.loginAdmin(req.getTeacherId(), req.getPwd());
	}

	// 老師端登入後，取得社團學生資訊方法
	@PostMapping(value = "teacherDatabase/clubStudentData")
	public TeacherLoginRes clubStudentData(@Valid @RequestBody TeacherGetStudentReq req) {
		return teacherDatabaseService.teacherGetClubStudent(req);
	}
	// 忘記密碼驗證
	@PostMapping(value = "teacherDatabase/pwdValidation")
	public TeacherLoginRes forgotPwd(@Valid @RequestBody TeacherForgotPwdReq req) {
		return teacherDatabaseService.forgotPwd(req);
		}
	
	// 老師登出
	 @GetMapping(value = "teacherDatabase/logout")
	    public BasicRes logout(HttpSession session) {
	        session.invalidate(); // 清除所有属性，包括用户資訊
	        return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	    }
	
	 @PostMapping(value = "teacherDatabase/createOrUpdateAll")
		public BasicRes createOrUpdateAll(@Valid @RequestBody TeacherDatabaseCreateOrUpdateReq req) {
			return teacherDatabaseService.createOrUpdate(req);
		}
	
	
	
	
}
