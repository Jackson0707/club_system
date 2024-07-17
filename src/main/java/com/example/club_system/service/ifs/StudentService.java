package com.example.club_system.service.ifs;

import com.example.club_system.vo.BasicRes;
import com.example.club_system.vo.StudentLoginReq;
import com.example.club_system.vo.StudentLoginRes;
import com.example.club_system.vo.StudentSearchRes;
import com.example.club_system.vo.StudentUpdataPwdReq;
import com.example.club_system.vo.StudentcreateOrUpdateReq;
import com.example.club_system.vo.StudentdeleteReq;
import com.example.club_system.vo.StudentsearchReq;
import com.example.club_system.vo.TeacherGetStudentReq;
import com.example.club_system.vo.TeacherLoginRes;

public interface StudentService {

	public BasicRes updataPwd(StudentUpdataPwdReq req);

 	public BasicRes createOrUpdate(StudentcreateOrUpdateReq req);

	public StudentSearchRes search(StudentsearchReq req);

	public BasicRes delete(StudentdeleteReq req);
	
	public StudentLoginRes login(StudentLoginReq req);
	
//	public TeacherLoginRes teacherGetClubStudent(TeacherGetStudentReq req);
}
