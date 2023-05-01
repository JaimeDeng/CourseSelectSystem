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
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						String idPattern = "[a-zA-Z0-9]{4,10}";
						if(!studentIdReqList.matches(idPattern)) {
							studentInfoResp.message = "學號" + studentIdReqList + "必須為英數字4~10個字元";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						if(!StringUtils.hasText(nameReqList)) {
							studentInfoResp.message = "姓名"+ nameReqList +"格式錯誤";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						if(acquiredCreditReqList < 0) {
							studentInfoResp.message = "學號" + studentIdReqList + "的學生學分不可設定為負數";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
						if(!passwordReqList.matches(patternSecurity)) {
							studentInfoResp.message = "學號" + studentIdReqList + "的密碼格式錯誤 , "
									+ "密碼必須長度8~20且至少含有一個特殊字符";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						if(StringUtils.hasText(selectedCourseReqList)) {
							studentInfoResp.message = "課程代碼" + studentIdReqList + "的已選課程預設為空白 , "
									+ "請透過課程加選系統加選";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
						if(StringUtils.hasText(selectedCourseReqList)) {
							studentInfoResp.message = "課程代碼" + studentIdReqList + "的已選課程預設為空白 , "
									+ "請透過課程加選系統加選";
							studentInfoResp.success = false;
							return studentInfoResp;
						}
					}
					studentInfoDao.saveAll(studentInfoListReq);
					studentInfoResp.message = "學生資訊儲存成功";
					studentInfoResp.success = true;
					return studentInfoResp;
				}else {
					//單個set區塊
					String studentIdReq = studentInfoReq.getStudentId();
					String nameReq = studentInfoReq.getName();
					Integer acquiredCreditReq = studentInfoReq.getAcquiredCredit();
					String passwordReq = studentInfoReq.getPassword();
					String selectedCourseReq = studentInfoReq.getSelectedCourse();
					Boolean administratorRqe = studentInfoReq.isAdministrator();
					
					if(studentInfoDao.existsByStudentId(studentIdReq)) {
						studentInfoResp.message = "該學號 " + studentIdReq +  " 已存在,不可新增";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					String idPattern = "[a-zA-Z0-9]{4,10}";
					if(!studentIdReq.matches(idPattern)) {
						studentInfoResp.message = "學號" + studentIdReq + "必須為英數字4~10個字元";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					if(!StringUtils.hasText(nameReq)) {
						studentInfoResp.message = "姓名"+ nameReq +"格式錯誤";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					if(acquiredCreditReq == null || acquiredCreditReq < 0) {
						studentInfoResp.message = "請設定學號" + studentIdReq + "的學生學分取不可設定為負數";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
					if(!passwordReq.matches(patternSecurity)) {
						studentInfoResp.message = "學號" + studentIdReq + "的密碼格式錯誤 , "
								+ "密碼必須長度8~20且至少含有一個特殊字符";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					if(StringUtils.hasText(selectedCourseReq)) {
						studentInfoResp.message = "課程代碼" + studentIdReq + "的已選課程預設為空白 , "
								+ "請透過課程加選系統加選";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					if(administratorRqe == null) {
						studentInfoResp.message = "請選擇是否為職員";
						studentInfoResp.success = false;
						return studentInfoResp;
					}
					StudentInfo studentInfo = new StudentInfo();
					studentInfo.setStudentInfo(studentInfoReq);
					studentInfoDao.save(studentInfo);
					studentInfoResp.message = "學生資訊儲存成功";
					studentInfoResp.success = true;
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
			studentInfoResp.success = false;
		}else {
			studentInfoResp.setStudentInfo(studentInfo);
			studentInfoResp.message = "成功查詢到此學號學生資訊";
			studentInfoResp.success = true;
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
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		if(StringUtils.hasText(studentInfo.getSelectedCourse())) {
			studentInfoResp.message = "該學生尚有選課 , 無法刪除 , 請先退選所有課程";
			studentInfoResp.success = false;
			return studentInfoResp;
		}else {
			studentInfoDao.delete(studentInfo);
			studentInfoResp.message = "學生資訊: " + studentInfo.getStudentId() + " " + studentInfo.getName() + " 已成功刪除";
			studentInfoResp.success = true;
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
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		if(!StringUtils.hasText(studentInfo.getSelectedCourse())) {
			studentInfoResp.message = "該學生尚無選課";
			studentInfoResp.success = false;
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
			studentInfoResp.success = true;
		}
		return studentInfoResp;
	}

	//-----------------------------------------修改密碼----------------------------------------
	@Override
	public StudentInfoResp editPassword(StudentInfoReq studentInfoReq) {
		if(!StringUtils.hasText(studentInfoReq.getStudentId())) {
			studentInfoResp.message = "學號不可為null";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		if(!StringUtils.hasText(studentInfoReq.getPassword())) {
			studentInfoResp.message = "新密碼不可為null";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		if(!studentInfoDao.existsByStudentId(studentInfoReq.getStudentId())) {
			studentInfoResp.message = "此學號不存在";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(studentInfoReq.getStudentId());
		String oldPwd = studentInfo.getPassword();
		String newPwd = studentInfoReq.getPassword();
		if(oldPwd.equals(newPwd)) {
			studentInfoResp.message = "新密碼不可與舊密碼相同";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
		if(!newPwd.matches(patternSecurity)) {
			studentInfoResp.message = "密碼必須長度8~20且至少含有一個特殊字符";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		studentInfo.setPassword(newPwd);
		studentInfoDao.save(studentInfo);
		studentInfoResp.message = "密碼修改成功";
		studentInfoResp.success = true;
		return studentInfoResp;
	}

	//-----------------------------------------修改學生資訊----------------------------------------
	@Override
	public StudentInfoResp editStudentInfo(StudentInfoReq studentInfoReq) {

		String studentIdReq = studentInfoReq.getStudentId();
		String nameReq = studentInfoReq.getName();
		Integer acquiredCreditReq = studentInfoReq.getAcquiredCredit();
		String passwordReq = studentInfoReq.getPassword();
		String selectedCourseReq = studentInfoReq.getSelectedCourse();
		String newStudentId = studentInfoReq.getNewStudentId();
		Boolean addministratorReq = studentInfoReq.isAdministrator();
		
		if(!studentInfoDao.existsByStudentId(studentIdReq)) {
			studentInfoResp.message = "查無此學號資訊";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(studentIdReq);
		StudentInfo newStudentInfo = new StudentInfo();
		
		String idPattern = "[a-zA-Z0-9]{4,10}";
		if(!newStudentId.matches(idPattern)) {
			studentInfoResp.message = "學號必須為英數字4~10個字元";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		newStudentInfo.setStudentId(newStudentId);
		if(!StringUtils.hasText(nameReq)) {
			studentInfoResp.message = "姓名"+ nameReq +"格式錯誤";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		newStudentInfo.setName(nameReq);
		if(acquiredCreditReq == null || acquiredCreditReq < 0) {
			studentInfoResp.message = "請設定學分且不可設定為負數";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		newStudentInfo.setAcquiredCredit(acquiredCreditReq);
		String patternSecurity = "^(?=.*[!*@#$%^&+=])(?=\\S{8,20}$).*";		//密碼必須長度8~20且至少含有一個特殊字符
		if(!passwordReq.matches(patternSecurity)) {
			studentInfoResp.message = "密碼必須長度8~20且至少含有一個特殊字符";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		newStudentInfo.setPassword(passwordReq);
		if(StringUtils.hasText(selectedCourseReq)) {
			studentInfoResp.message = "課程代碼" + studentIdReq + "的已選課程預設為空白 , "
					+ "請透過課程加選系統加選";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		if(addministratorReq == null) {
			studentInfoResp.message = "職員權限不得為null";
			studentInfoResp.success = false;
			return studentInfoResp;
		}
		newStudentInfo.setAdministrator(addministratorReq);
		if(newStudentId.equals(studentIdReq)) {
			studentInfoDao.save(newStudentInfo);
			studentInfoResp.message = "學生資訊儲存成功";
			studentInfoResp.success = true;
			return studentInfoResp;
		}else {
			if(studentInfoDao.existsByStudentId(newStudentId)) {
				studentInfoResp.message = "您設定的學號已有學生使用,不可修改為該學號";
				studentInfoResp.success = false;
				return studentInfoResp;
			}
			studentInfoDao.save(newStudentInfo);
			studentInfoDao.delete(studentInfo);
			studentInfoResp.message = "學生資訊儲存成功";
			studentInfoResp.success = true;
		}
		return studentInfoResp;
	}


}
