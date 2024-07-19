package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDatabaseCreateOrUpdateReq {
	

	@JsonProperty("teacher_id")
//	@NotNull(message = "Param teacher id error!!")
	private Integer teacherId;
	
	@JsonProperty("club_id")
	private Integer clubId;

	@JsonProperty("type")
	@NotNull(message = "Param teacher type error!!")
	private String type;
	
	@NotBlank(message = "Param teacher password error!!")
	private String pwd;

	@NotBlank(message = "Param teacher name error!!")
	private String name;

	@NotBlank(message = "Param teacher email error!!")
	private String email;
	
	@NotBlank(message = "Param status error!!")
	private String status;

	public TeacherDatabaseCreateOrUpdateReq() {
		super();
	}

	
	
	
	
	public TeacherDatabaseCreateOrUpdateReq( Integer teacherId,
			@NotNull(message = "Param club id error!!") Integer clubId,
			@NotNull(message = "Param teacher id error!!") String type,
			@NotBlank(message = "Param password error!!") String pwd,
			@NotBlank(message = "Param teacher name error!!") String name,
			@NotBlank(message = "Param email error!!") String email,
			@NotBlank(message = "Param status error!!") String status) {
		super();
		this.teacherId = teacherId;
		this.clubId = clubId;
		this.type = type;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.status = status;
	}





	public TeacherDatabaseCreateOrUpdateReq(@NotNull(message = "Param teacher type error!!") String type,
			@NotBlank(message = "Param teacher password error!!") String pwd,
			@NotBlank(message = "Param teacher name error!!") String name,
			@NotBlank(message = "Param teacher email error!!") String email,
			@NotBlank(message = "Param status error!!") String status) {
		super();
		this.type = type;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.status = status;
	}





	public TeacherDatabaseCreateOrUpdateReq(Integer teacherId,
			@NotBlank(message = "Param teacher id error!!") String type,
			@NotBlank(message = "Param password error!!") String pwd,
			@NotBlank(message = "Param teacher name error!!") String name,
			@NotBlank(message = "Param email error!!") String email,
			@NotBlank(message = "Param status error!!") String status) {
		super();
		this.teacherId = teacherId;
		this.type = type;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.status = status;
	}



	public TeacherDatabaseCreateOrUpdateReq(
			@NotBlank(message = "Param status error!!") String status,//
			Integer teacherId,
			@NotBlank(message = "Param password error!!") String pwd,
			@NotBlank(message = "Param teacher name error!!") String name,
			@NotBlank(message = "Param email error!!") String email) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public TeacherDatabaseCreateOrUpdateReq(
			@NotBlank(message = "Param status error!!") String status,//
			@NotBlank(message = "Param password error!!") String pwd,
			@NotBlank(message = "Param teacher name error!!") String name,
			@NotBlank(message = "Param email error!!") String email) {
		super();
		this.status = status;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Integer getClubId() {
		return clubId;
	}



	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	

}
