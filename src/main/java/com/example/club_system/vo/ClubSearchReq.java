package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubSearchReq {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("semestre")
	private String semester;
	
	@JsonProperty("teacher_id")
	private int teacherId;

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
