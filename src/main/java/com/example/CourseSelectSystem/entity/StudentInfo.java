package com.example.CourseSelectSystem.entity;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name = "studentinfo")
public class StudentInfo {
	
	@Id
	@Column(name = "student_id")
	private String studentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "acquired_credit")
	private Integer acquiredCredit;
	
	@Column(name = "selected_course")
	private String selectedCourse = "";
	
	@Column(name = "administrator")
	private Boolean administrator;
	
	//Getter & Setter
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAcquiredCredit() {
		return acquiredCredit;
	}

	public void setAcquiredCredit(Integer acquiredCredit) {
		this.acquiredCredit = acquiredCredit;
	}

	public String getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(String selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	
	public Boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentId = studentInfo.getStudentId();
		this.name = studentInfo.getName();
		this.password = studentInfo.getPassword();
		this.acquiredCredit = studentInfo.getAcquiredCredit();
		this.selectedCourse =studentInfo.getSelectedCourse();
		this.administrator = studentInfo.isAdministrator();
	}
	
	//Constructor
	
	public StudentInfo() {
	}
	
	public StudentInfo(String studentId, String name, String password, Integer acquiredCredit, String selectedCourse) {
		this.studentId = studentId;
		this.name = name;
		this.password = password;
		this.acquiredCredit = acquiredCredit;
		this.selectedCourse = selectedCourse;
	}

}
