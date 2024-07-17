package com.example.club_system.vo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentcreateOrUpdateReq {

	@JsonProperty("student_id")
	@NotNull(message = "Param student id error!!")
	private Integer studentId;

	@JsonProperty("semester")
	@NotBlank(message = "Param semester error!!")
	private String semester;

	@NotBlank(message = "Param PASSWORD error!!")
	private String pwd;

	@NotBlank(message = "Param grade error!!")
	private String grade;

	@NotBlank(message = "Param name error!!")
	private String name;

	@NotBlank(message = "Param email error!!")
	private String email;

	@JsonProperty("club_id")
//	@NotNull(message = "Param club id error!!")
	private Integer clubId;

	@JsonProperty("choice_list")
//	@NotBlank(message = "Param choiceList error!!")
	private String choiceList;

	@NotBlank(message = "Param status error!!")
	private String status;

	private LocalDateTime updateTime;

	public StudentcreateOrUpdateReq() {
		super();
	}

	

	public StudentcreateOrUpdateReq(
			@NotNull(message = "Param student id error!!") Integer studentId,
			@NotBlank(message = "Param semester error!!") String semester,
			@NotBlank(message = "Param PASSWORD error!!") String pwd,
			@NotBlank(message = "Param grade error!!") String grade,
			@NotBlank(message = "Param name error!!") String name,
			@NotBlank(message = "Param email error!!") String email, 
			Integer clubId,//
			String choiceList,//
			@NotBlank(message = "Param status error!!") String status) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.pwd = pwd;
		this.grade = grade;
		this.name = name;
		this.email = email;
		this.clubId = clubId;
		this.choiceList = choiceList;
		this.status = status;
	}



	

	public StudentcreateOrUpdateReq(
			@NotNull(message = "Param student id error!!") Integer studentId,
			@NotBlank(message = "Param semester error!!") String semester,
			@NotBlank(message = "Param PASSWORD error!!") String pwd,
			@NotBlank(message = "Param grade error!!") String grade,
			@NotBlank(message = "Param name error!!") String name,
			@NotBlank(message = "Param email error!!") String email, 
			Integer clubId, //
			String choiceList,//
			@NotBlank(message = "Param status error!!") String status, LocalDateTime updateTime) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.pwd = pwd;
		this.grade = grade;
		this.name = name;
		this.email = email;
		this.clubId = clubId;
		this.choiceList = choiceList;
		this.status = status;
		this.updateTime = updateTime;
	}



	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemesterr(String semester) {
		this.semester = semester;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmali(String email) {
		this.email = email;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(String choiceList) {
		this.choiceList = choiceList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}


}
