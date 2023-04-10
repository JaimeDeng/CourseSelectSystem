package com.example.CourseSelectSystem.services;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.CourseSelectSystem.vo.*;

public interface CourseInfoService {
	
	public CourseInfoResp setCourseInfo(CourseInfoReq courseInfoReq);
	public List getAllCourseInfo();
	public CourseInfoResp getCourseInfoByCourseId(CourseInfoReq courseInfoReq);
	public CourseInfoResp getCourseInfoByCourseName(CourseInfoReq courseInfoReq);
	public CourseInfoResp editCourse(CourseInfoReq courseInfoReq);
	public CourseInfoResp deleteCourse(CourseInfoReq courseInfoReq);
	public CourseInfoResp selectCourse(CourseInfoReq courseInfoReq);
	public CourseInfoResp dropCourse(CourseInfoReq courseInfoReq);
	
}
