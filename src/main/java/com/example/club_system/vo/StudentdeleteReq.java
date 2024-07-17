package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentdeleteReq {

	@JsonProperty("student_id_list")
	private List<Integer> studentIdList;

	public StudentdeleteReq() {
		super();
	}

	public StudentdeleteReq(List<Integer> studentIdList) {
		super();
		this.studentIdList = studentIdList;
	}

	public List<Integer> getStudentIdList() {
		return studentIdList;
	}

	public void setStudentIdList(List<Integer> studentIdList) {
		this.studentIdList = studentIdList;
	}

	

}
