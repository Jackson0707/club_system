package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VenueSearchReq {

	@NotBlank
	@JsonProperty("id")
	private String id;

	public VenueSearchReq() {
		super();
	}

	public VenueSearchReq(@NotBlank String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
