package com.example.club_system.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentId implements Serializable {

	private int studentId;

	private String academicYear;

	public StudentId() {
		super();
	}

	
	public StudentId(int studentId) {
		super();
		this.studentId = studentId;
	}


	public StudentId(int studentId, String academicYear) {
		super();
		this.studentId = studentId;
		this.academicYear = academicYear;
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

}
