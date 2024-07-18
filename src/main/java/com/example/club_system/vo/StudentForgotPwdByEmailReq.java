package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentForgotPwdByEmailReq {

	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private Integer studentId;

	@NotBlank(message = "Param email error!!")
	@JsonAlias("email")
	private String email;

	@NotBlank(message = "Param new password error!!")
	@JsonAlias("new_password")
	private String newPwd;

	public StudentForgotPwdByEmailReq() {
		super();
	}

	public StudentForgotPwdByEmailReq( //
			@NotNull(message = "Param student id error!!") Integer studentId,
			@NotBlank(message = "Param email error!!") String email,
			@NotBlank(message = "Param new password error!!") String newPwd) {
		super();
		this.studentId = studentId;
		this.email = email;
		this.newPwd = newPwd;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
