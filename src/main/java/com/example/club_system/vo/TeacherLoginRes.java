package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherLoginRes extends BasicRes{

	@JsonProperty("teacher_id")
	private Integer teacherId;
	
	private String teacherName;
	
	 @JsonProperty("club_id")
	private Integer clubId;
	
	private String clubName;
	
	private List<Student> studentList;

	public TeacherLoginRes() {
		super();
	}

	public TeacherLoginRes(int statusCode, String message) {
		super(statusCode, message);
	}


	public TeacherLoginRes(int statusCode, String message, Integer teacherId) {
		super(statusCode, message);
		this.teacherId = teacherId;
	}

	public TeacherLoginRes(int statusCode, String message, String teacherName, Integer clubId, String clubName, List<Student> studentList) {
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

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
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

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	
	
	
}
