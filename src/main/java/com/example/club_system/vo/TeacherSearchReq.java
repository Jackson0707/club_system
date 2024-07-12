package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherSearchReq {

	private String name;

	private String status;
	
	@JsonProperty("club_id")
	private int clubId;
	
	@JsonProperty("teacher_id")
	private int teacherId;

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public int getClubId() {
		return clubId;
	}


}
