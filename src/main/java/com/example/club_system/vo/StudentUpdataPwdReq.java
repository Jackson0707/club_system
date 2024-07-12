package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

public class StudentUpdataPwdReq {

	private int studentId;

	@NotBlank(message = "Param old password error!!")
	@JsonAlias("old_password")
	private String oldPwd;

	@NotBlank(message = "Param new password error!!")
	@JsonAlias("new_password")
	private String newPwd;

	public StudentUpdataPwdReq() {
		super();
	}

	public StudentUpdataPwdReq(int studentId, @NotBlank(message = "Param old password error!!") String oldPwd,
			@NotBlank(message = "Param new password error!!") String newPwd) {
		super();
		this.studentId = studentId;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
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
