package com.example.club_system.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentcreateOrUpdateReq {

	@JsonProperty("student_id")
	private Integer studentId;

	@JsonProperty("semester")
//	@NotBlank(message = "Param semester error!!")
	private String semester;

	private String pwd;

//	@NotBlank(message = "Param grade error!!")
	private String grade;

//	@NotBlank(message = "Param name error!!")
	private String name;

//	@NotBlank(message = "Param email error!!")
	private String email;

	@JsonProperty("club_id")
	private Integer clubId;

	@JsonProperty("choice_list")
	private String choiceList;

//	@NotBlank(message = "Param status error!!")
	private String status;

	private LocalDateTime updateTime;

	public StudentcreateOrUpdateReq() {
		super();
	}

	public StudentcreateOrUpdateReq(Integer studentId, //
			String semester, //
			String name, //
			String email, //
			String status) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public StudentcreateOrUpdateReq(Integer studentId, String semester, String grade, String name, String email,
			Integer clubId, String status) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.grade = grade;
		this.name = name;
		this.email = email;
		this.clubId = clubId;
		this.status = status;
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
