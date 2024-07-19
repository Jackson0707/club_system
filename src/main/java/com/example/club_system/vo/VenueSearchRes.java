package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Venue;

public class VenueSearchRes extends BasicRes {

	private List<Venue> venueList;

	public VenueSearchRes() {
		super();
	}

	public VenueSearchRes(int statusCode, String massage, List<Venue> venueList) {
		super(statusCode, massage);
		this.venueList = venueList;
	}

	public List<Venue> getVenueList() {
		return venueList;
	}

	public void setVenueList(List<Venue> venueList) {
		this.venueList = venueList;
	}

}
