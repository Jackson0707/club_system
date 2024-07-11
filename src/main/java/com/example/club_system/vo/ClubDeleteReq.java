package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubDeleteReq {

	@JsonProperty("club_id")
	private List<Integer> idList;

	public ClubDeleteReq() {
		super();
	}

	public ClubDeleteReq(List<Integer> idList) {
		super();
		this.idList = idList;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	

	

}
