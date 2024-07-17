package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubSearchReq {
	
	@JsonProperty("club_id")
	private Integer clubId;
	
	@JsonProperty("teacher_id")
	private Integer teacherId;
	
	private String name;
	
	private String semester;

	public Integer getClubId() {
		return clubId;
	}

	public String getName() {
		return name;
	}

	public String getSemester() {
		return semester;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	
	
	
}
