package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubSearchReq {
	
	@JsonProperty("club_id")
	private int clubId;
	
	private String name;
	
	private String semester;
	
	@JsonProperty("teacher_id")
	private int teacherId;

	public int getClubId() {
		return clubId;
	}

	public String getName() {
		return name;
	}

	public String getSemester() {
		return semester;
	}

	public int getTeacherId() {
		return teacherId;
	}

	
	
	
}
