package com.example.club_system.vo;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherGetStudentReq {

	@NotNull(message = "Param teacher id error!!")
	@JsonProperty("teacher_id")
	private int teacherId;

	public TeacherGetStudentReq() {
		super();
	}

	public TeacherGetStudentReq(@NotBlank(message = "Param teacher id error!!") int teacherId) {
		super();
		this.teacherId = teacherId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	
	
	
}
