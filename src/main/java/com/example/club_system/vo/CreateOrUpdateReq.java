package com.example.club_system.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class CreateOrUpdateReq {

	private int clubId;
	
	@NotBlank(message = "Param teacher id error!!")
	private int teacherId;
	
	@NotBlank(message = "Param academic year error!!")
	private int academicYear;
	
	@NotBlank(message = "Param semester error!!")
	private int semester;
	
	@NotBlank(message = "Param club name error!!")
	private String name;
	
	@NotBlank(message = "Param club intro error!!")
	private String intro;
	
	@NotBlank(message = "Param pay error!!")
	private int pay;
	
	@NotBlank(message = "Param classroom error!!")
	private String classroom;
	
	@NotBlank(message = "Param max error!!")
	private int max;
	
	@NotBlank(message = "Param attendees error!!")
	private int attendees;
	
	private LocalDate choiceStartTime;
	
	private LocalDate choiceEndTime;
	
	private LocalDate clubStartTime;
	
	private LocalDate clubEndTime;
	
	private LocalDate drawTime;

	public CreateOrUpdateReq() {
		super();
	}

	public CreateOrUpdateReq(int clubId, @NotBlank(message = "Param teacher id error!!") int teacherId,
			@NotBlank(message = "Param academic year error!!") int academicYear,
			@NotBlank(message = "Param semester error!!") int semester,
			@NotBlank(message = "Param club name error!!") String name,
			@NotBlank(message = "Param club intro error!!") String intro,
			@NotBlank(message = "Param pay error!!") int pay,
			@NotBlank(message = "Param classroom error!!") String classroom,
			@NotBlank(message = "Param max error!!") int max,
			@NotBlank(message = "Param attendees error!!") int attendees, LocalDate choiceStartTime,
			LocalDate choiceEndTime, LocalDate clubStartTime, LocalDate clubEndTime, LocalDate drawTime) {
		super();
		this.clubId = clubId;
		this.teacherId = teacherId;
		this.academicYear = academicYear;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.pay = pay;
		this.classroom = classroom;
		this.max = max;
		this.attendees = attendees;
		this.choiceStartTime = choiceStartTime;
		this.choiceEndTime = choiceEndTime;
		this.clubStartTime = clubStartTime;
		this.clubEndTime = clubEndTime;
		this.drawTime = drawTime;
	}

	public CreateOrUpdateReq(int clubId, @NotBlank(message = "Param teacher id error!!") int teacherId,
			@NotBlank(message = "Param academic year error!!") int academicYear,
			@NotBlank(message = "Param semester error!!") int semester,
			@NotBlank(message = "Param club name error!!") String name,
			@NotBlank(message = "Param club intro error!!") String intro,
			@NotBlank(message = "Param pay error!!") int pay,
			@NotBlank(message = "Param classroom error!!") String classroom,
			@NotBlank(message = "Param max error!!") int max) {
		super();
		this.clubId = clubId;
		this.teacherId = teacherId;
		this.academicYear = academicYear;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.pay = pay;
		this.classroom = classroom;
		this.max = max;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}

	public LocalDate getChoiceStartTime() {
		return choiceStartTime;
	}

	public void setChoiceStartTime(LocalDate choiceStartTime) {
		this.choiceStartTime = choiceStartTime;
	}

	public LocalDate getChoiceEndTime() {
		return choiceEndTime;
	}

	public void setChoiceEndTime(LocalDate choiceEndTime) {
		this.choiceEndTime = choiceEndTime;
	}

	public LocalDate getClubStartTime() {
		return clubStartTime;
	}

	public void setClubStartTime(LocalDate clubStartTime) {
		this.clubStartTime = clubStartTime;
	}

	public LocalDate getClubEndTime() {
		return clubEndTime;
	}

	public void setClubEndTime(LocalDate clubEndTime) {
		this.clubEndTime = clubEndTime;
	}

	public LocalDate getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(LocalDate drawTime) {
		this.drawTime = drawTime;
	}
	
	





}