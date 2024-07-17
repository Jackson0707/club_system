package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentdeleteReq {

	private List<StudentDeleteList> studentIdList;

	public StudentdeleteReq() {
		super();
	}

	public StudentdeleteReq(List<StudentDeleteList> studentIdList) {
		super();
		this.studentIdList = studentIdList;
	}

	public List<StudentDeleteList> getStudentIdList() {
		return studentIdList;
	}

	public void setStudentIdList(List<StudentDeleteList> studentIdList) {
		this.studentIdList = studentIdList;
	}

}
