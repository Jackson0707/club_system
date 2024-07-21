package com.example.club_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherDatabase {

	@Column(name = "status")
	private String status;

	// �]�� PK �O AI(Auto Incremental) �A�ҥH�n�[�W�� Annotation
	// strategy:�����O AI ���ͦ�����
	// GenerationType.IDENTITY:�N�� PK �Ʀr�Ѹ�Ʈw�Ӧ۰ʼW�[
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "teacher_id")
	private Integer teacherId;

	@Column(name = "club_id")
	private Integer clubId;

	@Column(name = "pwd")
	private String pwd;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "type")
	private String type;

	public TeacherDatabase() {
		super();
	}

	
	
	public TeacherDatabase(String status, Integer teacherId) {
		super();
		this.status = status;
		this.teacherId = teacherId;
	}



	public TeacherDatabase(String status, Integer teacherId, Integer clubId, String pwd, String name, String email,
			String type) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.clubId = clubId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public TeacherDatabase(String status, Integer teacherId, String pwd, String name, String email, String type) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	
	
	
	public TeacherDatabase(String status, Integer teacherId, String name, String email) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.name = name;
		this.email = email;
	}



	public TeacherDatabase(String status, Integer teacherId, String pwd, String name, String email) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public void setCluId(Integer cluId) {
		this.clubId = cluId;
	}

}
