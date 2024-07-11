package com.example.club_system.vo;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ClubCreateOrUpdateReq {

	@JsonAlias("club_id")
	private int clubId;
	
	@NotNull(message = "Param teacher id error!!")
	@JsonAlias("teacher_id")
	private int teacherId;
	
	@NotBlank(message = "Param semester error!!")
	@JsonAlias("semester")
	private String semester;
	
	@NotBlank(message = "Param club name error!!")
	@JsonAlias("name")
	private String name;
	
	@NotBlank(message = "Param club intro error!!")
	@JsonAlias("semester")
	private String intro;
	
	@NotNull(message = "Param pay error!!")
	@JsonAlias("pay")
	private int pay;
	
	@NotBlank(message = "Param classroom error!!")
	@JsonAlias("classroom")
	private String classroom;
	
	@NotNull(message = "Param max error!!")
	@JsonAlias("max")
	private int max;
	
//	@NotNull(message = "Param choice time error!!")
	@JsonAlias("choice_start_time")
	private LocalDate choiceStartTime;
	
//	@NotNull(message = "Param choice time error!!")
	@JsonAlias("choice_end_time")
	private LocalDate choiceEndTime; 
	
//	@NotNull(message = "Param club time error!!")
	@JsonAlias("club_start_time")
	private LocalDate clubStartTime;
	
//	@NotNull(message = "Param club time error!!")
	@JsonAlias("club_end_time")
	private LocalDate clubEndTime;
	
//	@NotNull(message = "Param draw time error!!")
	@JsonAlias("draw_time")
	private LocalDate drawTime;

	public ClubCreateOrUpdateReq() {
		super();
	}
//
//	public ClubCreateOrUpdateReq(int clubId, //
//			@NotNull(message = "Param teacher id error!!") int teacherId,
//			@NotBlank(message = "Param semester error!!") String semester,
//			@NotBlank(message = "Param club name error!!") String name,
//			@NotBlank(message = "Param club intro error!!") String intro,
//			@NotNull(message = "Param pay error!!") int pay,
//			@NotBlank(message = "Param classroom error!!") String classroom,
//			@NotNull(message = "Param max error!!") int max) {
//		super();
//		this.clubId = clubId;
//		this.teacherId = teacherId;
//		this.semester = semester;
//		this.name = name;
//		this.intro = intro;
//		this.pay = pay;
//		this.classroom = classroom;
//		this.max = max;
//	}
//
//	public ClubCreateOrUpdateReq(@NotNull(message = "Param teacher id error!!") int teacherId,
//			@NotBlank(message = "Param semester error!!") String semester,
//			@NotBlank(message = "Param club name error!!") String name,
//			@NotBlank(message = "Param club intro error!!") String intro,
//			@NotNull(message = "Param pay error!!") int pay,
//			@NotBlank(message = "Param classroom error!!") String classroom,
//			@NotNull(message = "Param max error!!") int max) {
//		super();
//		this.teacherId = teacherId;
//		this.semester = semester;
//		this.name = name;
//		this.intro = intro;
//		this.pay = pay;
//		this.classroom = classroom;
//		this.max = max;
//	}

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