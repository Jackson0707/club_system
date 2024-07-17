package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubDeleteReq {

	@JsonProperty("club_id_list")
	private List<Integer> clubIdList;

	public ClubDeleteReq() {
		super();
	}

	public ClubDeleteReq(List<Integer> clubIdList) {
		super();
		this.clubIdList = clubIdList;
	}

	public List<Integer> getClubIdList() {
		return clubIdList;
	}

	public void setClubIdList(List<Integer> clubIdList) {
		this.clubIdList = clubIdList;
	}

	

	

	

}
