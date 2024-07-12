package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class studentDeleteList {

	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private int studentId;

	@JsonProperty("academic_year")
	@NotBlank(message = "Param status error!!")
	private String academicYear;

	public studentDeleteList() {
		super();
	}

	public studentDeleteList(@NotNull(message = "Param student id error!!") int studentId,
			@NotBlank(message = "Param status error!!") String academicYear) {
		super();
		this.studentId = studentId;
		this.academicYear = academicYear;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

}
