package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class StudentLoginReq {

	@JsonAlias("student_id")
	private int studentId;

	@JsonAlias("pwd")
	private String pwd;

	public StudentLoginReq() {
		super();
	}

	public StudentLoginReq(int studentId, String pwd) {
		super();
		this.studentId = studentId;
		this.pwd = pwd;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
