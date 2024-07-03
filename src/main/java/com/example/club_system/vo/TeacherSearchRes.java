package com.example.club_system.vo;

import java.util.List;

import com.example.club_system.entity.TeacherDatabase;

public class TeacherSearchRes extends BasicRes {

	private List<TeacherDatabase> quizList;

	public TeacherSearchRes() {
		super();
	}

	public TeacherSearchRes(int statusCode, String massage, List<TeacherDatabase> quizList) {
		super(statusCode, massage);
		this.quizList = quizList;
	}

	public List<TeacherDatabase> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<TeacherDatabase> quizList) {
		this.quizList = quizList;
	}

}
