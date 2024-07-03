package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDeleteReq {

	@JsonProperty("teacher_id_list")
	private List<Integer> teacherIdList;

	public TeacherDeleteReq() {
		super();
	}

	public TeacherDeleteReq(List<Integer> teacherIdList) {
		super();
		this.teacherIdList = teacherIdList;
	}

	public List<Integer> getTeacherIdList() {
		return teacherIdList;
	}

	public void setTeacherIdList(List<Integer> teacherIdList) {
		this.teacherIdList = teacherIdList;
	}

}
