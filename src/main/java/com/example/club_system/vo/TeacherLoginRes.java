package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Student;

public class TeacherLoginRes extends BasicRes{

	private int teacherId;
	
	private String teacherName;
	
	private int clubId;
	
	private String clubName;
	
	private List<Student> studentList;

	public TeacherLoginRes() {
		super();
	}

	public TeacherLoginRes(int statusCode, String message) {
		super(statusCode, message);
	}


	public TeacherLoginRes(int statusCode, String message, int teacherId) {
		super(statusCode, message);
		this.teacherId = teacherId;
	}

	public TeacherLoginRes(int statusCode, String message, String teacherName, int clubId, String clubName, List<Student> studentList) {
		super(statusCode, message);
		this.teacherName = teacherName;
		this.clubId = clubId;
		this.clubName = clubName;
		this.studentList = studentList;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	
	
	
	
}
