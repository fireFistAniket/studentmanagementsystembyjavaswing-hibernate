package com.databasetable.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = { "SEMAIL", "SID" }, name = "student") })
public class Student {
	@Id
	@Column(name = "SID")
	private int id;
	@Column(name = "SNAME")
	private String name;
	@Column(name = "SMARKS")
	private double marks;
	@Column(name = "SPHNO")
	private long ph_num;
	@Column(name = "SEMAIL")
	private String email;
	@Column(name = "SGENDER")
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public long getPh_num() {
		return ph_num;
	}

	public void setPh_num(long ph_num) {
		this.ph_num = ph_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}

}
