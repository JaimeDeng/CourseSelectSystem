package com.example.CourseSelectSystem.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.CourseSelectSystem.vo.*;

public interface StudentInfoService {
	
	public StudentInfoResp setStudentInfo(StudentInfoReq studentInfoReq);
	public List getAllStudentInfo();
	public StudentInfoResp getStudentInfoByStudentId(StudentInfoReq studentInfoReq);
	public StudentInfoResp deleteStudentInfo(StudentInfoReq studentInfoReq);
	public StudentInfoResp getSelectedCourseInfo(StudentInfoReq studentInfoReq);
	
}
