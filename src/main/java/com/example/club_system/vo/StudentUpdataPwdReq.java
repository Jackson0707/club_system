package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentUpdataPwdReq {

	
	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private Integer studentId;

	@NotBlank(message = "Param old password error!!")
	@JsonAlias("old_password")
	private String oldPwd;

	@NotBlank(message = "Param new password error!!")
	@JsonAlias("new_password")
	private String newPwd;

	public StudentUpdataPwdReq() {
		super();
	}

	public StudentUpdataPwdReq(Integer studentId, @NotBlank(message = "Param old password error!!") String oldPwd,
			@NotBlank(message = "Param new password error!!") String newPwd) {
		super();
		this.studentId = studentId;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
