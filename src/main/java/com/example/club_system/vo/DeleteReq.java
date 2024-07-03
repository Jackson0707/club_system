package com.example.club_system.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteReq {

	@JsonProperty("id_list")
	private List<Integer> idList;

	public DeleteReq() {
		super();
	}

	public DeleteReq(List<Integer> idList) {
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
