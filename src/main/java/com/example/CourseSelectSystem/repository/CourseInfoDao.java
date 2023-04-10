package com.example.CourseSelectSystem.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CourseSelectSystem.entity.CourseInfo;

@Qualifier("courseInfoDao")
@Repository
public interface CourseInfoDao extends JpaRepository<CourseInfo, Integer> {
	
	List<CourseInfo> findCourseInfoByCourseName(String courseName);

	CourseInfo findCourseInfoByCourseId(String courseId);

	boolean existsByCourseId(String courseId);
	
	void deleteCourseByCourseId(String courseId);
	
}
