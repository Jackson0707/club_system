package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Club;

public class SearchRes extends BasicRes{

	private List<Club> clubList;

	public SearchRes() {
		super();
	}

	public SearchRes(int statusCode, String message,List<Club> clubList) {
		super(statusCode, message);
		this.clubList = clubList;
	}

	public List<Club> getClubList() {
		return clubList;
	}

	public void setClubList(List<Club> clubList) {
		this.clubList = clubList;
	}
	
	
}
