package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubSearchReq {
	
	@JsonProperty("club_id")
	private int clubId;
	
	@JsonProperty("teacher_id")
	private int teacherId;
	
	private String name;
	
	private String semester;
	


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
