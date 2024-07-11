package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentsearchReq {
	
	private String name;

	private String status;

	@JsonProperty("student_id")
	private int  studentId;

	@JsonProperty("club_id")
	private int  clubId;

	@JsonProperty("semester")
	private String semester;

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

	public String getSemester() {
		return semester;
	}

	public String getGrade() {
		return grade;
	}

	public String getName() {
		return name;
	}

	
}
