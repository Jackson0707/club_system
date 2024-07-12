package com.example.club_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "student")
//複合主鍵用:指定的 CLASS 名稱是把所有主鍵集中管理的 CLASS
@IdClass(value = StudentId.class)
public class Student {

	@Column(name = "status")
	private String status;

	// 因為 PK 是 AI(Auto Incremental) ，所以要加上此 Annotation
	// strategy:指的是 AI 的生成策略
	// GenerationType.IDENTITY:代表 PK 數字由資料庫來自動增加
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "student_id")
	private int studentId;

	@Id
	@Column(name = "academic_year")
	private String academicYear;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "grade")
	private String grade;

	@Column(name = "club_id")
	private int clubId;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "choice_list")
	private String choiceList;

	@Column(name = "studentcol")
	private String studentcol;

	public Student() {
		super();
	}

	public Student(String status, int studentId, String academicYear, String pwd, String grade, int clubId,
			String paymentStatus, String choiceList, String studentcol) {
		super();
		this.status = status;
		this.studentId = studentId;
		this.academicYear = academicYear;
		this.pwd = pwd;
		this.grade = grade;
		this.clubId = clubId;
		this.paymentStatus = paymentStatus;
		this.choiceList = choiceList;
		this.studentcol = studentcol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(String choiceList) {
		this.choiceList = choiceList;
	}

	public String getStudentcol() {
		return studentcol;
	}

	public void setStudentcol(String studentcol) {
		this.studentcol = studentcol;
	}

}
