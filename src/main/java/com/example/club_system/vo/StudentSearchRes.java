package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.Student;

public class StudentSearchRes extends BasicRes {

	private List<Student> studentList;

	public StudentSearchRes() {
		super();
	}

	public StudentSearchRes(int statusCode, String message) {
		super(statusCode, message);
	}

	public StudentSearchRes(List<Student> studentList) {
		super();
		this.studentList = studentList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
