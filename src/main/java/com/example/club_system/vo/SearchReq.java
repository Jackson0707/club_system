package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Club;

public class SearchReq {

	private List<Club> clubList;

	public SearchReq() {
		super();
	}

	public SearchReq(List<Club> clubList) {
		super();
		this.clubList = clubList;
	}

	public List<Club> getClubList() {
		return clubList;
	}

	public void setClubList(List<Club> clubList) {
		this.clubList = clubList;
	}
	
	
}
