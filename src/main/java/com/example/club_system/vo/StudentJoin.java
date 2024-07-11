package com.example.club_system.vo;

public class StudentJoin {

	private int studentId;
	
	private int clubId;
	
	private int choiceList;
	
	private String semester;

	public StudentJoin() {
		super();
	}

	public StudentJoin(int studentId, int clubId, int choiceList, String semester) {
		super();
		this.studentId = studentId;
		this.clubId = clubId;
		this.choiceList = choiceList;
		this.semester = semester;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(int choiceList) {
		this.choiceList = choiceList;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	
}
