package com.example.club_system.vo;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentGetClubDataReq {

	@NotNull(message = "Param teacher id error!!")
	@JsonProperty("student_id")
	private Integer studentId;

	public StudentGetClubDataReq() {
		super();
	}

	public StudentGetClubDataReq(@NotNull(message = "Param teacher id error!!") Integer studentId) {
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
