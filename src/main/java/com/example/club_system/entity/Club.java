package com.example.club_system.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "club")
public class Club {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="club_id")
	private int clubId;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="name")
	private String name;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="teacher_id")
	private int teacherId;
	
	@Column(name="pay")
	private int pay;
	
	@Column(name="classroom")
	private String classroom;
	
	@Column(name="max")
	private int max;
	
	@Column(name="attendees")
	private int attendees;
	
	@Column(name="choice_start_time")
	private LocalDate choiceStartTime;
	
	@Column(name="choice_end_time")
	private LocalDate choiceEndTime;
	
	@Column(name="club_start_time")
	private LocalDate clubStartTime;
	
	@Column(name="club_end_time")
	private LocalDate clubEndTime;
	
	@Column(name="draw_time")
	private LocalDate drawTime;
	
	private String picture;

	public Club() {
		super();
	}

	public Club(int clubId, String semester, String name, String intro, int teacherId, int pay, String classroom,
			int max, int attendees, LocalDate choiceStartTime, LocalDate choiceEndTime, LocalDate clubStartTime,
			LocalDate clubEndTime, LocalDate drawTime) {
		super();
		this.clubId = clubId;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.teacherId = teacherId;
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

	public Club(int clubId, String semester, String name, String intro, int teacherId, int pay, String classroom,
			int max) {
		super();
		this.clubId = clubId;
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.teacherId = teacherId;
		this.pay = pay;
		this.classroom = classroom;
		this.max = max;
	}

	public Club(String semester, String name, String intro, int teacherId, int pay, String classroom, int max) {
		super();
		this.semester = semester;
		this.name = name;
		this.intro = intro;
		this.teacherId = teacherId;
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

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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
