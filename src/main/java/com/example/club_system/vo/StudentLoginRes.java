package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentLoginRes extends BasicRes{

	@JsonProperty("student_id")
	private Integer studentId;
	
	@JsonProperty("club_id")
	private Integer clubId;
	
	private String studentName;
	
	private String clubName;
	
	private String clssroom;
	
	private int pay;
	
	private String teacherName;

	public StudentLoginRes() {
		super();
	}

	public StudentLoginRes(int statusCode, String message) {
		super(statusCode, message);
	}

	public StudentLoginRes(int statusCode, String message, Integer studentId) {
		super(statusCode, message);
		this.studentId = studentId;
	}

	public StudentLoginRes(int statusCode, String message, Integer clubId, String studentName, String clubName, String clssroom, int pay,
			String teacherName) {
		super(statusCode, message);
		this.clubId = clubId;
		this.studentName = studentName;
		this.clubName = clubName;
		this.clssroom = clssroom;
		this.pay = pay;
		this.teacherName = teacherName;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClssroom() {
		return clssroom;
	}

	public void setClssroom(String clssroom) {
		this.clssroom = clssroom;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	
	
	
	
	
}
