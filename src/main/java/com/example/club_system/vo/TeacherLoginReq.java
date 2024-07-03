package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class TeacherLoginReq {

	private int teacherId;

	
	@JsonAlias("password")
	private String pwd;

	public TeacherLoginReq() {
		super();
	}

	public TeacherLoginReq(int teacherId, String pwd) {
		super();
		this.teacherId = teacherId;
		this.pwd = pwd;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
