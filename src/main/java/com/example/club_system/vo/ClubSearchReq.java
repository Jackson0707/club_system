package com.example.club_system.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubSearchReq {
	
	@JsonProperty("club_id")
	private int clubId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("semester")
	private String semester;
	
	@JsonProperty("teacher_id")
	private int teacherId;

	public ClubSearchReq() {
		super();
	}

	public ClubSearchReq(int clubId, String name, String semester, int teacherId) {
		super();
		this.clubId = clubId;
		this.name = name;
		this.semester = semester;
		this.teacherId = teacherId;
	}

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
