package com.example.CourseSelectSystem.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.CourseSelectSystem.entity.CourseInfo;
import com.example.CourseSelectSystem.entity.StudentInfo;
import com.example.CourseSelectSystem.repository.CourseInfoDao;
import com.example.CourseSelectSystem.repository.StudentInfoDao;
import com.example.CourseSelectSystem.vo.CourseInfoReq;
import com.example.CourseSelectSystem.vo.CourseInfoResp;
import com.example.CourseSelectSystem.vo.StudentInfoReq;
import com.example.CourseSelectSystem.vo.StudentInfoResp;

@Qualifier("studentInfoService")
@Service
@Transactional
public class StudentInfoServiceImpl implements StudentInfoService {
	
	//-----------------------Constructor Injection---------------------------
	private final StudentInfoDao studentInfoDao;
	
	private final CourseInfoDao courseInfoDao;
	
	@Autowired
	public StudentInfoServiceImpl(@Qualifier("studentInfoDao") StudentInfoDao studentInfoDao , 
			@Qualifier("courseInfoDao") CourseInfoDao courseInfoDao) {
	    this.studentInfoDao = studentInfoDao;
	    this.courseInfoDao = courseInfoDao;
	}
	//-----------------------Constructor Injection---------------------------
	
	@Autowired
	CourseInfoService courseInfoService;
	
	//ResponseBody
	StudentInfoResp studentInfoResp = new StudentInfoResp();

	//-----------------------------------------設定學生資訊----------------------------------------
	@Override
	public StudentInfoResp setStudentInfo(StudentInfoReq studentInfoReq) {
		
		List<StudentInfo> studentInfoListReq = studentInfoReq.getStudentInfoList();
		//大量set區塊
				if(studentInfoListReq.size() != 0) {
					//遍歷List內容檢查格式有無錯誤
					for(StudentInfo element : studentInfoListReq) {
						String studentIdReqList = element.getStudentId();
						String nameReqList = element.getName();
						Integer acquiredCreditReqList = element.getAcquiredCredit();
						String passwordReqList = element.getPassword();
						String selectedCourseReqList = element.getSelectedCourse();
						
						if(studentInfoDao.existsByStudentId(studentIdReqList)) {
							studentInfoResp.message = "該學號 " + studentIdReqList +  " 已存在,不可新增";
							return studentInfoResp;
						}
						String idPattern = "[a-zA-Z0-9]{4,10}";
						if(!studentIdReqList.matches(idPattern)) {
							studentInfoResp.message = "學號" + studentIdReqList + "必須為英數字4~10個字元";
							return studentInfoResp;
						}
						if(!StringUtils.hasText(nameReqList)) {
							studentInfoResp.message = "姓名"+ nameReqList +"格式錯誤";
							return studentInfoResp;
						}
						if(acquiredCreditReqList < 0) {
							studentInfoResp.message = "學號" + studentIdReqList + "的學生學分不可設定為負數";
							return studentInfoResp;
						}
						String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
						if(!passwordReqList.matches(patternSecurity)) {
							studentInfoResp.message = "學號" + studentIdReqList + "的密碼格式錯誤 , "
									+ "密碼必須長度8~20且至少含有一個特殊字符";
							return studentInfoResp;
						}
						if(StringUtils.hasText(selectedCourseReqList)) {
							studentInfoResp.message = "課程代碼" + studentIdReqList + "的已選課程預設為空白 , "
									+ "請透過課程加選系統加選";
							return studentInfoResp;
						}
					}
					studentInfoDao.saveAll(studentInfoListReq);
					studentInfoResp.message = "學生資訊儲存成功";
					return studentInfoResp;
				}else {
					//單個set區塊
					String studentIdReq = studentInfoReq.getStudentId();
					String nameReq = studentInfoReq.getName();
					Integer acquiredCreditReq = studentInfoReq.getAcquiredCredit();
					String passwordReq = studentInfoReq.getPassword();
					String selectedCourseReq = studentInfoReq.getSelectedCourse();
					
					if(studentInfoDao.existsByStudentId(studentIdReq)) {
						studentInfoResp.message = "該學號 " + studentIdReq +  " 已存在,不可新增";
						return studentInfoResp;
					}
					String idPattern = "[a-zA-Z0-9]{4,10}";
					if(!studentIdReq.matches(idPattern)) {
						studentInfoResp.message = "學號" + studentIdReq + "必須為英數字4~10個字元";
						return studentInfoResp;
					}
					if(!StringUtils.hasText(nameReq)) {
						studentInfoResp.message = "姓名"+ nameReq +"格式錯誤";
						return studentInfoResp;
					}
					if(acquiredCreditReq < 0) {
						studentInfoResp.message = "學號" + studentIdReq + "的學生學分不可設定為負數";
						return studentInfoResp;
					}
					String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
					if(!passwordReq.matches(patternSecurity)) {
						studentInfoResp.message = "學號" + studentIdReq + "的密碼格式錯誤 , "
								+ "密碼必須長度8~20且至少含有一個特殊字符";
						return studentInfoResp;
					}
					if(StringUtils.hasText(selectedCourseReq)) {
						studentInfoResp.message = "課程代碼" + studentIdReq + "的已選課程預設為空白 , "
								+ "請透過課程加選系統加選";
						return studentInfoResp;
					}
					StudentInfo studentInfo = new StudentInfo();
					studentInfo.setStudentInfo(studentInfoReq);
					studentInfoDao.save(studentInfo);
					studentInfoResp.message = "學生資訊儲存成功";
				}
				return studentInfoResp;
	}

	//-----------------------------------------獲取全部學生資訊----------------------------------------
	@Override
	public List getAllStudentInfo() {
		return studentInfoDao.findAll();
	}
	
	//-----------------------------------------以ID獲取學生資訊----------------------------------------
	@Override
	public StudentInfoResp getStudentInfoByStudentId(StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		StudentInfo studentInfo = new StudentInfo();
		studentInfo = studentInfoDao.findStudentInfoByStudentId(studentInfoReq.getStudentId());
		if(studentInfo == null) {
			studentInfoResp.message = "查無此學號";
		}else {
			studentInfoResp.setStudentInfo(studentInfo);
			studentInfoResp.message = "成功查詢到此學號學生資訊";
		}
		return studentInfoResp;
	}
	
	//-----------------------------------------刪除學生資訊----------------------------------------
	@Override
	public StudentInfoResp deleteStudentInfo(StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		String studentIdReq = studentInfoReq.getStudentId();
		String courseNameReq = studentInfoReq.getName();
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(studentInfoReq.getStudentId());
		
		if(studentInfo == null) {
			studentInfoResp.message = "查無此學號存在";
			return studentInfoResp;
		}
		if(StringUtils.hasText(studentInfo.getSelectedCourse())) {
			studentInfoResp.message = "該學生尚有選課 , 無法刪除 , 請先退選所有課程";
			return studentInfoResp;
		}else {
			studentInfoDao.delete(studentInfo);
			studentInfoResp.message = "學生資訊: " + studentInfo.getStudentId() + " " + studentInfo.getName() + " 已成功刪除";
		}
		return studentInfoResp;
	}

	//-----------------------------------------獲取已加選課程資訊----------------------------------------
	@Override
	public StudentInfoResp getSelectedCourseInfo(StudentInfoReq studentInfoReq) {
		StudentInfoResp studentInfoResp = new StudentInfoResp();
		CourseInfoReq courseInfoReq = new CourseInfoReq();
		CourseInfo courseInfo = new CourseInfo();
		String studentIdReq = studentInfoReq.getStudentId();
		String NameReq = studentInfoReq.getName();
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(studentInfoReq.getStudentId());
		
		if(studentInfo == null) {
			studentInfoResp.message = "查無此學號存在";
			return studentInfoResp;
		}
		if(!StringUtils.hasText(studentInfo.getSelectedCourse())) {
			studentInfoResp.message = "該學生尚無選課";
			return studentInfoResp;
		}else {
			String selectedCourse = studentInfo.getSelectedCourse();
			String[] selectedCourseArray = selectedCourse.split(",");
			for(String element : selectedCourseArray) {
				courseInfoReq.setCourseId(element);
				courseInfo = courseInfoService.getCourseInfoByCourseId(courseInfoReq);
				studentInfoResp.getCourseInfoList().add(courseInfo);
			}
			studentInfoResp.setStudentId(studentIdReq);
			studentInfoResp.setName(NameReq);
			studentInfoResp.message = "學生: " + studentInfo.getStudentId() + " " + studentInfo.getName() + " 的選課資訊查詢成功";
		}
		return studentInfoResp;
	}


}
