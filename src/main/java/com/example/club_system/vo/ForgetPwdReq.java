package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ForgetPwdReq {

	@JsonAlias("student_id")
	private Integer studentId;

	public ForgetPwdReq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgetPwdReq(Integer studentId) {
		super();
		this.studentId = studentId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

}
