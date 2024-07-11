package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.ClubCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDatabaseCreateOrUpdateReq;
import com.example.club_system.vo.TeacherDeleteReq;
import com.example.club_system.vo.TeacherSearchReq;
import com.example.club_system.vo.TeacherSearchRes;

public interface TeacherDatabaseService {
	
	public BasicRes updatePwd(int teacherId,String oldpwd,String newpwd);

 	public BasicRes createOrUpdate(TeacherDatabaseCreateOrUpdateReq req);

	public TeacherSearchRes search(TeacherSearchReq req);

	public BasicRes delete(TeacherDeleteReq req);
	
	public BasicRes login(int teacherId,String pwd);

	public BasicRes loginAdmin(int teacherId,String pwd);

	public BasicRes clubRandom();
}
