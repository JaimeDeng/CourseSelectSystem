package com.example.CourseSelectSystem.vo;

import java.util.*;

import com.example.CourseSelectSystem.entity.CourseInfo;
import com.example.CourseSelectSystem.entity.StudentInfo;

public class CourseInfoReq extends CourseInfo {
	
	private List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
	
	private List<String> selectCourseList = new ArrayList<String>();
	
	private String studentId;

	//Getter & Setter
	
	public List<String> getSelectCourseList() {
		return selectCourseList;
	}

	public void setSelectCourseList(List<String> selectCourseList) {
		this.selectCourseList = selectCourseList;
	}
	
	public List<CourseInfo> getCourseInfoList() {
		return courseInfoList;
	}

	public void setCourseInfoList(List<CourseInfo> courseInfoList) {
		this.courseInfoList = courseInfoList;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	

}
