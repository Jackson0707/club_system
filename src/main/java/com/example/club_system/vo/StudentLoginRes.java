package com.example.club_system.vo;

public class StudentLoginRes extends BasicRes{

	
	private int studentId;
	
	private String studentName;
	
	private int clubId;
	
	private String clubName;
	
	private String clssroom;
	
	private String pay;
	
	private String teacherName;

	public StudentLoginRes() {
		super();
	}

	public StudentLoginRes(int statusCode, String message) {
		super(statusCode, message);
	}

	public StudentLoginRes(int statusCode, String message, int studentId) {
		super(statusCode, message);
		this.studentId = studentId;
	}

	public StudentLoginRes(String studentName, int clubId, String clubName, String clssroom, String pay,
			String teacherName) {
		super();
		this.studentName = studentName;
		this.clubId = clubId;
		this.clubName = clubName;
		this.clssroom = clssroom;
		this.pay = pay;
		this.teacherName = teacherName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public String getClssroom() {
		return clssroom;
	}

	public void setClssroom(String clssroom) {
		this.clssroom = clssroom;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
	
	
}
