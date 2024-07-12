package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class studentsearchReq {

	@NotBlank(message = "Param status error!!")
	private String status;

	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private int studentId;

	@JsonProperty("club_id")
	@NotNull(message = "Param club id error!!")
	private int clubId;

	@JsonProperty("academic_year")
	@NotBlank(message = "Param status error!!")
	private String academicYear;

	@NotBlank(message = "Param grade error!!")
	private String grade;

	public String getStatus() {
		return status;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getClubId() {
		return clubId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public String getGrade() {
		return grade;
	}

}
