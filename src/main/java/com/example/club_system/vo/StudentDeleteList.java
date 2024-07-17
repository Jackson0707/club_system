//package com.example.club_system.vo;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//import com.fasterxml.jackson.annotation.JsonAlias;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class StudentDeleteList {
//
//	@JsonAlias("student_id")
//	@NotNull(message = "Param student id error!!")
//	private Integer studentId;
//
//	@JsonAlias("semester")
//	@NotBlank(message = "Param semester error!!")
//	private String semester;
//
//	public StudentDeleteList() {
//		super();
//	}
//
//	
//	
//	public StudentDeleteList(@NotNull(message = "Param student id error!!") Integer studentId) {
//		super();
//		this.studentId = studentId;
//	}
//
//
//
//	public StudentDeleteList(@NotNull(message = "Param student id error!!") Integer studentId,
//			@NotBlank(message = "Param status error!!") String semester) {
//		super();
//		this.studentId = studentId;
//		this.semester = semester;
//	}
//
//	public Integer getStudentId() {
//		return studentId;
//	}
//
//	public void setStudentId(Integer studentId) {
//		this.studentId = studentId;
//	}
//
//	public String getSemester() {
//		return semester;
//	}
//
//	public void setSemester(String semester) {
//		this.semester = semester;
//	}
//
//}
