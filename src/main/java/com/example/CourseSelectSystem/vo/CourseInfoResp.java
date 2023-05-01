package com.example.CourseSelectSystem.vo;

import java.util.*;

import com.example.CourseSelectSystem.entity.CourseInfo;
import com.example.CourseSelectSystem.entity.StudentInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseInfoResp extends CourseInfoReq {
	
	public String message;
	
	public String nameConflictMessage;
	
	public String scheduleConflictMessage;
	
	public boolean success = false;

}
