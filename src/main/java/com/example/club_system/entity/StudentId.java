package com.example.club_system.entity;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class StudentId implements Serializable {

	private int studentId;

	private String semester;

	public StudentId() {
		super();
	}

	
	public StudentId(int studentId) {
		super();
		this.studentId = studentId;
	}


	public StudentId(int studentId, String semester) {
		super();
		this.studentId = studentId;
		this.semester = semester;
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

	
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        StudentId studentId1 = (StudentId) o;
	        return studentId == studentId1.studentId && Objects.equals(semester, studentId1.semester);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(studentId, semester);
	    }
}
