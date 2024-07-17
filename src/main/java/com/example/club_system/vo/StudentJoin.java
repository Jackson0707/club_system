package com.example.club_system.vo;

public class StudentJoin {

	private Integer studentId;
	
	private Integer clubId;
	
	private Integer choiceList;
	
	private String semester;

	public StudentJoin() {
		super();
	}

	public StudentJoin(Integer studentId, Integer clubId, Integer choiceList, String semester) {
		super();
		this.studentId = studentId;
		this.clubId = clubId;
		this.choiceList = choiceList;
		this.semester = semester;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Integer getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(Integer choiceList) {
		this.choiceList = choiceList;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	
}
