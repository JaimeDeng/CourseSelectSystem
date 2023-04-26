package com.example.CourseSelectSystem.vo;

import java.util.*;

import com.example.CourseSelectSystem.entity.StudentInfo;

public class StudentInfoReq extends StudentInfo {
	
	private String newStudentId;
	
	private List<StudentInfo> studentInfoList = new ArrayList<StudentInfo>();

	//Getter & Setter
	public String getNewStudentId() {
		return newStudentId;
	}

	public void setNewStudentId(String newStudentId) {
		this.newStudentId = newStudentId;
	}
	
	public List<StudentInfo> getStudentInfoList() {
		return studentInfoList;
	}

	public void setStudentInfoList(List<StudentInfo> studentInfoList) {
		this.studentInfoList = studentInfoList;
	}

}
