package com.example.CourseSelectSystem.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CourseSelectSystem.entity.StudentInfo;

@Qualifier("studentInfoDao")
@Repository
public interface StudentInfoDao extends JpaRepository<StudentInfo, Integer> {
	
	List<StudentInfo> findStudentInfoByName(String name);
	
	StudentInfo findStudentInfoByStudentId(String studentId);

}
