package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.studentcreateOrUpdateReq;
import com.example.club_system.vo.studentdeleteReq;
import com.example.club_system.vo.studentsearchReq;

public interface StudentService {

	public BasicRes updataPwd(int studentId,String oldpwd,String newpwd);

 	public BasicRes createOrUpdate(studentcreateOrUpdateReq req);

	public StudentSearchRes search(studentsearchReq req);

	public BasicRes delete(studentdeleteReq req);
	
	public BasicRes login(int clubId,String pwd);
}
