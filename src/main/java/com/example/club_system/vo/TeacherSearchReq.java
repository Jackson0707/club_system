package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherSearchReq {

	private String name;

	private String status;
	
	@JsonProperty("club_id")
	private Integer clubId;
	
	@JsonProperty("teacher_id")
	private Integer teacherId;

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public Integer getClubId() {
		return clubId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}




}
