package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class StudentLoginReq {

	@JsonAlias("student_id")
	private Integer studentId;

	@JsonAlias("pwd")
	private String pwd;

	public StudentLoginReq() {
		super();
	}

	public StudentLoginReq(Integer studentId, String pwd) {
		super();
		this.studentId = studentId;
		this.pwd = pwd;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
