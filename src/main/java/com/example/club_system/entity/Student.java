package com.example.club_system.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "student")
//複合主鍵用:指定的 CLASS 名稱是把所有主鍵集中管理的 CLASS
@IdClass(value = StudentId.class)
public class Student {

	@Id
	@Column(name = "student_id")
	private int studentId;

	@Id
	@Column(name = "semester")
	private String semester;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "grade")
	private String grade;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "club_id")
	private int clubId;

	@Column(name = "choice_list")
	private String choiceList;

	@Column(name = "status")
	private String status;

	@Column(name = "update_time")
	private LocalDateTime updateTime;

	public Student() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		
		return this.getStudentId()==other.getStudentId();
	}
	
	public Student(int studentId, String semester, String pwd, String grade, String name, String email, int clubId,
			String choiceList, String status, LocalDateTime updateTime) {
		super();
		this.studentId = studentId;
		this.semester = semester;
		this.pwd = pwd;
		this.grade = grade;
		this.name = name;
		this.email = email;
		this.clubId = clubId;
		this.choiceList = choiceList;
		this.status = status;
		this.updateTime = updateTime;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(String choiceList) {
		this.choiceList = choiceList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
