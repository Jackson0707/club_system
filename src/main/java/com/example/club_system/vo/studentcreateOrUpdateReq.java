package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class studentcreateOrUpdateReq {

	@NotBlank(message = "Param status error!!")
	private String status;

	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private int studentId;

	@JsonProperty("academic_year")
	@NotBlank(message = "Param status error!!")
	private String academicYear;

	@NotBlank(message = "Param PASSWORD error!!")
	private String pwd;

	@NotBlank(message = "Param grade error!!")
	private String grade;

	@JsonProperty("club_id")
	@NotNull(message = "Param club id error!!")
	private int clubId;

	@JsonProperty("payment_status")
	@NotBlank(message = "Param paymentStatus error!!")
	private String paymentStatus;

	@JsonProperty("choice_list")
	@NotBlank(message = "Param choiceList error!!")
	private String choiceList;

	@NotBlank(message = "Param studentcol error!!")
	private String studentcol;

	public studentcreateOrUpdateReq() {
		super();
	}

	public studentcreateOrUpdateReq(@NotBlank(message = "Param status error!!") String status,
			@NotNull(message = "Param student id error!!") int studentId,
			@NotBlank(message = "Param status error!!") String academicYear,
			@NotBlank(message = "Param PASSWORD error!!") String pwd,
			@NotBlank(message = "Param grade error!!") String grade,
			@NotNull(message = "Param club id error!!") int clubId,
			@NotBlank(message = "Param paymentStatus error!!") String paymentStatus,
			@NotBlank(message = "Param choiceList error!!") String choiceList,
			@NotBlank(message = "Param studentcol error!!") String studentcol) {
		super();
		this.status = status;
		this.studentId = studentId;
		this.academicYear = academicYear;
		this.pwd = pwd;
		this.grade = grade;
		this.clubId = clubId;
		this.paymentStatus = paymentStatus;
		this.choiceList = choiceList;
		this.studentcol = studentcol;
	}

	public studentcreateOrUpdateReq(@NotBlank(message = "Param status error!!") String status,
			@NotBlank(message = "Param status error!!") String academicYear,
			@NotBlank(message = "Param PASSWORD error!!") String pwd,
			@NotBlank(message = "Param grade error!!") String grade,
			@NotNull(message = "Param club id error!!") int clubId,
			@NotBlank(message = "Param paymentStatus error!!") String paymentStatus,
			@NotBlank(message = "Param choiceList error!!") String choiceList,
			@NotBlank(message = "Param studentcol error!!") String studentcol) {
		super();
		this.status = status;
		this.academicYear = academicYear;
		this.pwd = pwd;
		this.grade = grade;
		this.clubId = clubId;
		this.paymentStatus = paymentStatus;
		this.choiceList = choiceList;
		this.studentcol = studentcol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(String choiceList) {
		this.choiceList = choiceList;
	}

	public String getStudentcol() {
		return studentcol;
	}

	public void setStudentcol(String studentcol) {
		this.studentcol = studentcol;
	}

}
