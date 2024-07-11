package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

public class TeacherUpdatePwdReq {

	private int teacherId;

	@NotBlank(message = "Param old password error!!")
	@JsonAlias("old_password")
	private String oldPwd;

	@NotBlank(message = "Param new password error!!")
	@JsonAlias("new_password")
	private String newPwd;

	public TeacherUpdatePwdReq() {
		super();
	}

	public TeacherUpdatePwdReq(int teacherId, String oldPwd, String newPwd) {
		super();
		this.teacherId = teacherId;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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