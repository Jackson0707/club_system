package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherLoginReq {
	
	@JsonProperty("teacher_id")
	private Integer teacherId;
	
	@JsonProperty("pwd")
	private String pwd;

	public TeacherLoginReq() {
		super();
	}

	public TeacherLoginReq(Integer teacherId, String pwd) {
		super();
		this.teacherId = teacherId;
		this.pwd = pwd;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
