package com.example.club_system.vo;

public class JoinTable {

	private String id;
	
	private int studentId ;
	
	private int teacherId;
	
	private int choice;
	
	private String semester;

	public JoinTable() {
		super();
	}

	public JoinTable(String id, int studentId, int teacherId, int choice, String semester) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.teacherId = teacherId;
		this.choice = choice;
		this.semester = semester;
	}
	
	
	
	
	
}
