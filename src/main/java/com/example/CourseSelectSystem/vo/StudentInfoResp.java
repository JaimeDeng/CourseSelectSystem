package com.example.CourseSelectSystem.vo;

import java.util.*;

import com.example.CourseSelectSystem.entity.CourseInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentInfoResp extends StudentInfoReq {
	
	public String message;
	
	public boolean success = false;
	
	private List<CourseInfo> courseInfoList = new ArrayList<CourseInfo>();
	
	//Getter & Setter
	public List<CourseInfo> getCourseInfoList() {
		return courseInfoList;
	}

	public void setCourseInfoList(List<CourseInfo> courseInfoList) {
		this.courseInfoList = courseInfoList;
	}

}
