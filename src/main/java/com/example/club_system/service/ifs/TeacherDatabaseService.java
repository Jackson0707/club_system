package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginReq;
import com.example.club_system.vo.TeacherLoginRes;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherSearchRes;

public interface TeacherDatabaseService {
	
	public BasicRes updatePwd(Integer teacherId,String oldpwd,String newpwd);

 	public BasicRes createOrUpdate(TeacherDatabaseCreateOrUpdateReq req);

	public TeacherSearchRes search(TeacherSearchReq req);

	public BasicRes delete(TeacherDeleteReq req);
	
	public TeacherLoginRes login(TeacherLoginReq req);

	public BasicRes loginAdmin(Integer teacherId, String pwd);

	public TeacherLoginRes teacherGetClubStudent(TeacherGetStudentReq req);
}
