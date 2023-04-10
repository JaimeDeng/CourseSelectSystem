package com.example.CourseSelectSystem.entity;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "courseinfo")
public class CourseInfo {
	
	@Id
	@Column(name = "course_id")
	private String courseId;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "lesson_day")
	private String lessonDay;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "credit")
	private Integer credit;

	@Column(name = "selected_person")
	private String selectedPerson = "";
	
	
	//Getter & Setter
	
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getLessonDay() {
		return lessonDay;
	}

	public void setLessonDay(String lessonDay) {
		this.lessonDay = lessonDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(String selectedPerson) {
		this.selectedPerson = selectedPerson;
	}
	
	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseId = courseInfo.getCourseId();
		this.courseName = courseInfo.getCourseName();
		this.lessonDay = courseInfo.getLessonDay();
		this.startTime = courseInfo.getStartTime();
		this.endTime = courseInfo.getEndTime();
		this.credit = courseInfo.getCredit();
		this.selectedPerson = courseInfo.getSelectedPerson();
	}
	
	//Constructor
	public CourseInfo() {
	}
	
	public CourseInfo(String courseId, String courseName, String lessonDay, String startTime, 
			String endTime, Integer credit) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.lessonDay = lessonDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.credit = credit;
	}
	
	public CourseInfo(String courseId, String courseName, String lessonDay, String startTime, 
			String endTime, Integer credit , String selectedPerson) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.lessonDay = lessonDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.credit = credit;
		this.selectedPerson = selectedPerson;
	}
	
}
