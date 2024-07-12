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

	// 因為 PK 是 AI(Auto Incremental) ，所以要加上此 Annotation
	// strategy:指的是 AI 的生成策略
	// GenerationType.IDENTITY:代表 PK 數字由資料庫來自動增加
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "teacher_id")
	private int teacherId;

	@Column(name = "club_id")
	private int clubId;

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

	public TeacherDatabase(String status, int teacherId, int clubId, String pwd, String name, String email,
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

	public TeacherDatabase(String status, int teacherId, String pwd, String name, String email, String type) {
		super();
		this.status = status;
		this.teacherId = teacherId;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public TeacherDatabase(String status, int teacherId, String pwd, String name, String email) {
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

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
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

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public void setCluId(int cluId) {
		this.clubId = cluId;
	}

}
