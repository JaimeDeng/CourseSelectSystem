package com.example.CourseSelectSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import com.example.CourseSelectSystem.services.*;
import com.example.CourseSelectSystem.vo.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CourseSelectSystemController {
	
	//-----------------------Constructor Injection---------------------------
	private final CourseInfoService courseInfoService;
	private final StudentInfoService  studentInfoService;
	
	@Autowired
	public CourseSelectSystemController(@Qualifier("courseInfoService") CourseInfoService  courseInfoService , 
			@Qualifier("studentInfoService") StudentInfoService  studentInfoService) {
		this.courseInfoService = courseInfoService;
		this.studentInfoService = studentInfoService;
	}
	//-----------------------Constructor Injection---------------------------
	
	//---------------------------CourseInfoService---------------------------
	
	@PostMapping("/setCourseInfo")
	public CourseInfoResp setCourseInfo(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.setCourseInfo(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/getAllCourseInfo")
	public CourseInfoResp getAllCourseInfo() {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp.setCourseInfoList(courseInfoService.getAllCourseInfo());
		courseInfoResp.success = true;
		return courseInfoResp;
	}
	
	@PostMapping("/getCourseInfoById")
	public CourseInfoResp getCourseInfoByCourseId(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.getCourseInfoByCourseId(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/getCourseInfoByCourseName")
	public CourseInfoResp getCourseInfoByCourseName(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.getCourseInfoByCourseName(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/editCourse")
	public CourseInfoResp editCourse(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.editCourse(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/deleteCourse")
	public CourseInfoResp deleteCourse(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.deleteCourse(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/selectCourse")
	public CourseInfoResp selectCourse(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.selectCourse(courseInfoReq);
		return courseInfoResp;
	}
	
	@PostMapping("/dropCourse")
	public CourseInfoResp dropCourse(@RequestBody CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		courseInfoResp = courseInfoService.dropCourse(courseInfoReq);
		return courseInfoResp;
	}
	//---------------------------CourseInfoService---------------------------
	
	//---------------------------StudentInfoService--------------------------
	@PostMapping("/setStudentInfo")
	public StudentInfoResp setStudentInfo(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.setStudentInfo(studentInfoReq);
		return studentInfoResp;
	}
	
	@PostMapping("/getAllStudentInfo")
	public StudentInfoResp getAllStudentInfo() {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp.setStudentInfoList(studentInfoService.getAllStudentInfo());
		studentInfoResp.success = true;
		return studentInfoResp;
	}
	
	@PostMapping("/getStudentInfoByStudentId")
	public StudentInfoResp getStudentInfoByStudentId(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.getStudentInfoByStudentId(studentInfoReq);
		return studentInfoResp;
	}

	@PostMapping("/deleteStudentInfo")
	public StudentInfoResp deleteStudentInfo(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.deleteStudentInfo(studentInfoReq);
		return studentInfoResp;
	}
	
	@PostMapping("getSelectedCourseInfo")
	public StudentInfoResp getSelectedCourseInfo(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.getSelectedCourseInfo(studentInfoReq);
		return studentInfoResp;
	}
	
	@PostMapping("editPassword")
	public StudentInfoResp editPassword(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.editPassword(studentInfoReq);
		return studentInfoResp;
	}
	
	@PostMapping("editStudentInfo")
	public StudentInfoResp editStudentInfo(@RequestBody StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		studentInfoResp = studentInfoService.editStudentInfo(studentInfoReq);
		return studentInfoResp;
	}
	//---------------------------StudentInfoService--------------------------
	
}
