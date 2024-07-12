package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class studentdeleteReq {

	@JsonProperty("student_id_list")
	private List<studentDeleteList> studentIdList;

	public studentdeleteReq() {
		super();
	}

	public studentdeleteReq(List<studentDeleteList> studentIdList) {
		super();
		this.studentIdList = studentIdList;
	}

	public List<studentDeleteList> getStudentIdList() {
		return studentIdList;
	}

	public void setStudentIdList(List<studentDeleteList> studentIdList) {
		this.studentIdList = studentIdList;
	}

}
