package com.example.club_system.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClubCreateOrUpdateReq {

	@JsonProperty("club_id")
	private Integer clubId;

	@JsonProperty("teacher_id")
	private Integer teacherId;

	@NotBlank(message = "Param semester error!!")
	private String semester;

	@NotBlank(message = "Param club name error!!")
	private String name;

	@NotBlank(message = "Param club intro error!!")
	private String intro;

	@NotNull(message = "Param pay error!!")
	private int pay;

	@NotBlank(message = "Param classroom error!!")
	private String classroom;

	@NotNull(message = "Param max error!!")
	private int max;


//	@NotNull(message = "Param choice time error!!")
//	@JsonAlias("choice_start_time")
//	private LocalDate choiceStartTime;

//	@NotNull(message = "Param choice time error!!")
//	@JsonAlias("choice_end_time")
//	private LocalDate choiceEndTime; 

//	@NotNull(message = "Param club time error!!")
//	@JsonAlias("club_start_time")
//	private LocalDate clubStartTime;

//	@NotNull(message = "Param club time error!!")
//	@JsonAlias("club_end_time")
//	private LocalDate clubEndTime;

//	@NotNull(message = "Param draw time error!!")
//	@JsonAlias("draw_time")
//	private LocalDate drawTime;

	public ClubCreateOrUpdateReq() {
		super();
	}


	public ClubCreateOrUpdateReq(Integer clubId, Integer teacherId,
			@NotBlank(message = "Param semester error!!") String semester,
			@NotBlank(message = "Param club name error!!") String name,
			@NotBlank(message = "Param club intro error!!") String intro,
			@NotNull(message = "Param pay error!!") int pay,
			@NotBlank(message = "Param classroom error!!") String classroom,
			@NotNull(message = "Param max error!!") int max) {
		super();
		this.clubId = clubId;
		this.teacherId = teacherId;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.pay = pay;
		this.classroom = classroom;
		this.max = max;
	}

	public ClubCreateOrUpdateReq( Integer teacherId,
			@NotBlank(message = "Param semester error!!") String semester,
			@NotBlank(message = "Param club name error!!") String name,
			@NotBlank(message = "Param club intro error!!") String intro,
			@NotNull(message = "Param pay error!!") int pay,
			@NotBlank(message = "Param classroom error!!") String classroom,
			@NotNull(message = "Param max error!!") int max) {
		super();
		this.teacherId = teacherId;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.pay = pay;
		this.classroom = classroom;
		this.max = max;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
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

	
	

//	public LocalDate getChoiceStartTime() {
//		return choiceStartTime;
//	}
//
//	public void setChoiceStartTime(LocalDate choiceStartTime) {
//		this.choiceStartTime = choiceStartTime;
//	}
//
//	public LocalDate getChoiceEndTime() {
//		return choiceEndTime;
//	}
//
//	public void setChoiceEndTime(LocalDate choiceEndTime) {
//		this.choiceEndTime = choiceEndTime;
//	}

//	public LocalDate getClubStartTime() {
//		return clubStartTime;
//	}
//
//	public void setClubStartTime(LocalDate clubStartTime) {
//		this.clubStartTime = clubStartTime;
//	}
//
//	public LocalDate getClubEndTime() {
//		return clubEndTime;
//	}
//
//	public void setClubEndTime(LocalDate clubEndTime) {
//		this.clubEndTime = clubEndTime;
//	}
//
//	public LocalDate getDrawTime() {
//		return drawTime;
//	}
//
//	public void setDrawTime(LocalDate drawTime) {
//		this.drawTime = drawTime;
//	}

}