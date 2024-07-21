package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class TeacherForgotPwdReq {

	@JsonAlias("teacher_id")
	private Integer teacherId;

	public TeacherForgotPwdReq() {
		super();
	}

	public TeacherForgotPwdReq(Integer teacherId) {
		super();
		this.teacherId = teacherId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	
}
