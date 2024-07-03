package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDatabaseCreateOrUpdateReq {

	@NotBlank(message = "Param status error!!")
	private String status;

	@JsonProperty("teacher_id")
	@NotNull(message = "Param teacher id error!!")
	private int teacherId;

	@NotBlank(message = "Param password error!!")
	private String pwd;

	@NotBlank(message = "Param teacher name error!!")
	private String name;

	@NotBlank(message = "Param email error!!")
	private String email;

	public TeacherDatabaseCreateOrUpdateReq() {
		super();
	}

	public TeacherDatabaseCreateOrUpdateReq(@NotBlank(message = "Param status error!!") String status,
			@NotNull(message = "Param teacher id error!!") int teacherId,
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

	public TeacherDatabaseCreateOrUpdateReq(@NotBlank(message = "Param status error!!") String status,
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

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
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


}
