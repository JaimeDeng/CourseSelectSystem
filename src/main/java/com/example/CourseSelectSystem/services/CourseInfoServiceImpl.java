package com.example.CourseSelectSystem.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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

@Qualifier("courseInfoService")
@Service
@Transactional
public class CourseInfoServiceImpl implements CourseInfoService {
	
	//-----------------------Constructor Injection---------------------------
	private final CourseInfoDao courseInfoDao;
	
	private final StudentInfoDao studentInfoDao;
	
	@Autowired
	public CourseInfoServiceImpl(@Qualifier("courseInfoDao") CourseInfoDao courseInfoDao ,
			@Qualifier("studentInfoDao") StudentInfoDao studentInfoDao) {
	    this.courseInfoDao = courseInfoDao;
	    this.studentInfoDao = studentInfoDao;
	}
	//-----------------------Constructor Injection---------------------------
	
	//ResponseBody
	CourseInfoResp courseInfoResp = new CourseInfoResp();
	String courseIdResp = courseInfoResp.getCourseId();
	String courseNameResp = courseInfoResp.getCourseName();
	Integer creditResp = courseInfoResp.getCredit();
	String lessonDayResp = courseInfoResp.getLessonDay();
	String startTimeResp = courseInfoResp.getStartTime();
	String endTimeResp = courseInfoResp.getEndTime();
	String selectedPersonResp = courseInfoResp.getSelectedPerson();
	
	//-----------------------------------------設定課程資訊----------------------------------------
	@Override
	public CourseInfoResp setCourseInfo(CourseInfoReq courseInfoReq) {
		
		List<CourseInfo> courseInfoListReq = courseInfoReq.getCourseInfoList();
		
		//大量set區塊
		if(courseInfoListReq.size() != 0) {
			//遍歷List內容檢查格式有無錯誤
			for(CourseInfo element : courseInfoListReq) {
				String courseIdReqList = element.getCourseId();
				String courseNameReqList = element.getCourseName();
				Integer creditReqList = element.getCredit();
				String lessonDayReqList = element.getLessonDay();
				String startTimeReqList = element.getStartTime();
				String endTimeReqList = element.getEndTime();
				
				if(!StringUtils.hasText(courseIdReqList) || courseIdReqList.length() < 3) {
					courseInfoResp.message = "課程代碼" + courseIdReqList + "不得為空且須大於3個字元";
					return courseInfoResp;
				}
				if(!StringUtils.hasText(courseNameReqList)) {
					courseInfoResp.message = "課程名稱"+ courseNameReqList +"格式錯誤";
					return courseInfoResp;
				}
				if(creditReqList < 1) {
					courseInfoResp.message = "課程代碼" + courseIdReqList + "的學分不可設定低於1";
					return courseInfoResp;
				}
				if(!StringUtils.hasText(lessonDayReqList)) {
					courseInfoResp.message = "課程代碼" + courseIdReqList + "的上課星期不可為空";
					return courseInfoResp;
				}
				String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
				if(!startTimeReqList.matches(timePattern)) {
					courseInfoResp.message = "課程代碼" + courseIdReqList + "的上課時間格式錯誤 , "
							+ "請輸入24小時制的時間格式";
					return courseInfoResp;
				}
				if(!endTimeReqList.matches(timePattern)) {
					courseInfoResp.message = "課程代碼" + courseIdReqList + "的下課時間格式錯誤 , "
							+ "請輸入24小時制的時間格式";
					return courseInfoResp;
				}
				//選課人員名單不可設定 , 預設為空白
			}
			courseInfoDao.saveAll(courseInfoListReq);
			courseInfoResp.message = "課程資訊儲存成功";
			return courseInfoResp;
		}else {
			//單個set區塊
			String courseIdReq = courseInfoReq.getCourseId();
			String courseNameReq = courseInfoReq.getCourseName();
			Integer creditReq = courseInfoReq.getCredit();
			String lessonDayReq = courseInfoReq.getLessonDay();
			String startTimeReq = courseInfoReq.getStartTime();
			String endTimeReq = courseInfoReq.getEndTime();
			String selectedPersonReq = courseInfoReq.getSelectedPerson();
			if(!StringUtils.hasText(courseIdReq)) {
				courseInfoResp.message = "課程代碼不得為空且須大於3個字元";
				return courseInfoResp;
			}
			if(!StringUtils.hasText(courseNameReq)) {
				courseInfoResp.message = "課程名稱格式錯誤";
				return courseInfoResp;
			}
			if(creditReq < 1) {
				courseInfoResp.message = "學分不可設定低於1";
				return courseInfoResp;
			}
			if(!StringUtils.hasText(lessonDayReq)) {
				courseInfoResp.message = "上課星期不可為空";
				return courseInfoResp;
			}
			String timePattern = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
			if(!startTimeReq.matches(timePattern)) {
				courseInfoResp.message = "課程代碼" + courseIdReq + "的上課時間格式錯誤 , "
						+ "請輸入24小時制的時間格式";
				return courseInfoResp;
			}
			if(!endTimeReq.matches(timePattern)) {
				courseInfoResp.message = "課程代碼" + courseIdReq + "的下課時間格式錯誤 , "
						+ "請輸入24小時制的時間格式";
				return courseInfoResp;
			}
			//選課人員名單不可設定 , 預設為空白

			CourseInfo courseInfo = new CourseInfo(courseIdReq , courseNameReq , lessonDayReq , startTimeReq , 
					endTimeReq , creditReq);
			courseInfoDao.save(courseInfo);
			courseInfoResp.message = "課程資訊儲存成功";
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------獲取全部課程資訊----------------------------------------
	@Override
	public List getAllCourseInfo() {
		return courseInfoDao.findAll();
	}
	
	//-----------------------------------------以ID獲取課程資訊----------------------------------------
	@Override
	public CourseInfoResp getCourseInfoByCourseId(CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		CourseInfo courseInfo = new CourseInfo();
		courseInfo = courseInfoDao.findCourseInfoByCourseId(courseInfoReq.getCourseId());
		if(courseInfo == null) {
			courseInfoResp.message = "查無此ID" ;
			return courseInfoResp;
		}else {
			courseInfoResp.setCourseInfo(courseInfo);
			courseInfoResp.message = "成功查詢到該ID的課程" ;
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------以名稱獲取課程資訊----------------------------------------
	@Override
	public CourseInfoResp getCourseInfoByCourseName(CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		List<CourseInfo> courseInfoList = courseInfoDao.findCourseInfoByCourseName(courseInfoReq.getCourseName());
		if(courseInfoList.size() < 1) {
			courseInfoResp.message = "沒有搜尋到該課程名稱的資訊";
		}else {
			courseInfoResp.setCourseInfoList(courseInfoList);
			courseInfoResp.message = "成功搜尋到該名稱的課程列表";
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------修改課程資訊----------------------------------------
	@Override
	public CourseInfoResp editCourse(CourseInfoReq courseInfoReq) {
		CourseInfo courseInfo = courseInfoDao.findCourseInfoByCourseId(courseInfoReq.getCourseId());
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		if(!courseInfoDao.existsByCourseId(courseInfoReq.getCourseId())) {
			courseInfoResp.message = "無此課程代碼";
		}else {	
			//檢查修改的內容格式有無錯誤
			String courseIdReq = courseInfoReq.getCourseId();
			String courseNameReq = courseInfoReq.getCourseName();
			Integer creditReq = courseInfoReq.getCredit();
			String lessonDayReq = courseInfoReq.getLessonDay();
			String startTimeReq = courseInfoReq.getStartTime();
			String endTimeReq = courseInfoReq.getEndTime();
			
			if(!StringUtils.hasText(courseNameReq)) {
				courseInfoResp.message = "課程名稱格式錯誤";
				return courseInfoResp;
			}
			if(creditReq < 1) {
				courseInfoResp.message = "學分不可設定低於1";
				return courseInfoResp;
			}
			if(!StringUtils.hasText(lessonDayReq)) {
				courseInfoResp.message = "上課星期不可為空";
				return courseInfoResp;
			}
			if(!StringUtils.hasText(startTimeReq)) {
				courseInfoResp.message = "上課時間不可為空";
				return courseInfoResp;
			}
			if(!StringUtils.hasText(endTimeReq)) {
				courseInfoResp.message = "下課時間不可為空";
				return courseInfoResp;
			}
			//如果該課程有人選修 , 保留選修人員資訊
			if(!courseInfo.getSelectedPerson().isBlank()) {
				CourseInfo newCourseInfo = new CourseInfo(courseIdReq , courseNameReq , lessonDayReq , startTimeReq , 
						endTimeReq , creditReq , courseInfo.getSelectedPerson());
				courseInfoDao.save(newCourseInfo);
				courseInfoResp.message = "課程資訊修改成功";
				return courseInfoResp;
			}
			
			CourseInfo newCourseInfo = new CourseInfo(courseIdReq , courseNameReq , lessonDayReq , startTimeReq , 
					endTimeReq , creditReq);
			courseInfoDao.save(newCourseInfo);
			courseInfoResp.message = "課程資訊修改成功";
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------刪除課程----------------------------------------
	@Override
	public CourseInfoResp deleteCourse(CourseInfoReq courseInfoReq) {
		CourseInfoResp courseInfoResp = new CourseInfoResp();
		String courseIdReq = courseInfoReq.getCourseId();
		String courseNameReq = courseInfoReq.getCourseName();
		CourseInfo courseInfo = courseInfoDao.findCourseInfoByCourseId(courseIdReq);
		
		if(courseInfo == null) {
			courseInfoResp.message = "查無此課程代碼存在";
			return courseInfoResp;
		}
		if(StringUtils.hasText(courseInfo.getSelectedPerson())) {
			courseInfoResp.message = "課程尚有學生選修 , 無法刪除";
			return courseInfoResp;
		}else {
			courseInfoDao.delete(courseInfo);
			courseInfoResp.message = "課程: " + courseInfo.getCourseId() + " " + courseInfo.getCourseName() + " 已成功刪除";
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------加選課程----------------------------------------
	@Override
	public CourseInfoResp selectCourse(CourseInfoReq courseInfoReq) {
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(courseInfoReq.getStudentId());
		CourseInfo courseInfo = courseInfoDao.findCourseInfoByCourseId(courseInfoReq.getCourseId());
		String selectedCourse = studentInfo.getSelectedCourse();
		
		if(courseInfo == null) {
			courseInfoResp.message = "該課程代碼不存在";
			return courseInfoResp;
		}
		
		//欲選課程選修人數上限判斷
		String courseSeletedPerson = courseInfo.getSelectedPerson();
		if(!courseSeletedPerson.isBlank()) {
			String[] courseSeletedPersonArray = courseSeletedPerson.split(",");
			List<String> courseSeletedPersonList = new ArrayList<>(Arrays.asList(courseSeletedPersonArray));
			if(courseSeletedPersonList.size() >= 3) {
				courseInfoResp.message = "該課程的選修人數已達上限 , 無法再加選";
				return courseInfoResp;
			}
		}
		
		//學分上限判斷
		if(studentInfo.getAcquiredCredit() >= 10) {
			courseInfoResp.message = "你的學分已經滿10分了 , 無法再加選";
			return courseInfoResp;
		}
		
		//學生已選課程判斷
		if(!selectedCourse.isBlank()) {
			//學生有已選課程
			String[] selectedCourseArray = selectedCourse.split(",");
			List<String> selectedCourseList = new ArrayList<>(Arrays.asList(selectedCourseArray));
			
			//重複加選判斷
			if(selectedCourseList.contains(courseInfoReq.getCourseId())) {
				courseInfoResp.message = "你已經加選該課程了 , 無法再加選";
				return courseInfoResp;
			}
			
			//學生選修上限判斷
			if(selectedCourseList.size() >= 3) {
				courseInfoResp.message = "你的選修課程數已達上限 , 無法再加選";
				return courseInfoResp;
			}
			
			//課程同名判斷
			for(String element : selectedCourseList) {
				//學生已選課程的Info (new新的Req使用,否則會蓋掉原本輸入的ReqBody)
				CourseInfoReq selectedCourseInfoReq = new CourseInfoReq();
				selectedCourseInfoReq.setCourseId(element);
				CourseInfo selectedCourseInfo = getCourseInfoByCourseId(selectedCourseInfoReq);
				if(courseInfo.getCourseName().equals(selectedCourseInfo.getCourseName())) {
					courseInfoResp.message = "你的已選課程中已有 " + courseInfo.getCourseName() + " , 不可再"
							+ "加選相同名稱的課程" ;
					return courseInfoResp;
				}
				
				//衝堂判斷
				//已選課程的上下課時間轉為分鐘數
				String selectedCourseStartTimeStr = selectedCourseInfo.getStartTime();
				LocalTime selectedCourseStartTime = LocalTime.parse(selectedCourseStartTimeStr);
				int selectedCourseStartTimeMinutes = selectedCourseStartTime.getHour() * 60 + 
						selectedCourseStartTime.getMinute();
				
				String selectedCourseEndTimeStr = selectedCourseInfo.getEndTime();
				LocalTime selectedCourseEndTime = LocalTime.parse(selectedCourseEndTimeStr);
				int selectedCourseEndTimeMinutes = selectedCourseEndTime.getHour() * 60 + 
						selectedCourseEndTime.getMinute();
				
				//欲選課程的上下課時間轉為分鐘數
				String selectCourseStartTimeStr = courseInfo.getStartTime();
				LocalTime selectCourseStartTime = LocalTime.parse(selectCourseStartTimeStr);
				int selectCourseStartTimeMinutes = selectCourseStartTime.getHour() * 60 + 
						selectCourseStartTime.getMinute();
				
				String selectCourseEndTimeStr = courseInfo.getStartTime();
				LocalTime selectCourseEndTime = LocalTime.parse(selectCourseEndTimeStr);
				int selectCourseEndTimeMinutes = selectCourseEndTime.getHour() * 60 + 
						selectCourseEndTime.getMinute();
				
				//同一天才判斷
				if(courseInfo.getLessonDay().equals(selectedCourseInfo.getLessonDay())){
					
					if(selectCourseStartTimeMinutes >= selectedCourseStartTimeMinutes
						&& selectCourseStartTimeMinutes <= selectedCourseEndTimeMinutes
						||	selectCourseEndTimeMinutes >= selectedCourseStartTimeMinutes
						&& selectCourseEndTimeMinutes <= selectedCourseEndTimeMinutes) {
						courseInfoResp.message = "你的已選課程 " + selectedCourseInfo.getCourseName() + " 與欲選課程 " +
								courseInfo.getCourseName() + " 時間相衝 , 不可加選";
						return courseInfoResp;
					}
					
				}
			}
			
			//加入學生已選課程
			selectedCourseList.add(courseInfo.getCourseId());
			String joinedSelectedCourseList = String.join(",", selectedCourseList);
			studentInfo.setSelectedCourse(joinedSelectedCourseList);
			//學生學分增加
			studentInfo.setAcquiredCredit(studentInfo.getAcquiredCredit() + courseInfo.getCredit());
			//課程的選課人數增加
			if(courseInfo.getSelectedPerson().isBlank()) {
				courseInfo.setSelectedPerson(studentInfo.getStudentId());
				courseInfoDao.save(courseInfo);
			}else {
				String selectedPersonStr = courseInfo.getSelectedPerson();
				String newselectedPersonStr = courseInfo.getSelectedPerson() + "," + studentInfo.getStudentId();
				courseInfo.setSelectedPerson(newselectedPersonStr);
				courseInfoDao.save(courseInfo);
			}
			studentInfoDao.save(studentInfo);
			courseInfoResp.message = "課程加選成功";
			return courseInfoResp;
			
		}else{
			//學生沒有已選課程
			studentInfo.setSelectedCourse(courseInfo.getCourseId());
			studentInfo.setAcquiredCredit(studentInfo.getAcquiredCredit() + courseInfo.getCredit());	
			//加入學生已選課程
			studentInfo.setSelectedCourse(courseInfo.getCourseId());
			//學生學分增加
			studentInfo.setAcquiredCredit(courseInfo.getCredit());
			//課程的選課人數增加
			if(courseInfo.getSelectedPerson().isBlank()) {
				courseInfo.setSelectedPerson(studentInfo.getStudentId());
				courseInfoDao.save(courseInfo);
			}else {
				String selectedPersonStr = courseInfo.getSelectedPerson();
				String newselectedPersonStr = courseInfo.getSelectedPerson() + "," + studentInfo.getStudentId();
				courseInfo.setSelectedPerson(newselectedPersonStr);
				courseInfoDao.save(courseInfo);
			}
			studentInfoDao.save(studentInfo);
			courseInfoResp.message = "課程加選成功";
		}
		return courseInfoResp;
	}
	
	//-----------------------------------------退選課程----------------------------------------
	@Override
	public CourseInfoResp dropCourse(CourseInfoReq courseInfoReq) {
		StudentInfo studentInfo = studentInfoDao.findStudentInfoByStudentId(courseInfoReq.getStudentId());
		CourseInfo courseInfo = courseInfoDao.findCourseInfoByCourseId(courseInfoReq.getCourseId());
		String selectedCourseStr =  studentInfo.getSelectedCourse();
		String selectedPersonStr =  courseInfo.getSelectedPerson();
		if(selectedCourseStr.isBlank()) {
			courseInfoResp.message = "該學號之學生並未加選任何課程";
			return courseInfoResp;
		}else {
			String[] selectedCourseArray = selectedCourseStr.split(",");
			List<String> selectedCourseList = new ArrayList<>(Arrays.asList(selectedCourseArray));
			
			if(!selectedCourseList.contains(courseInfo.getCourseId())){
				courseInfoResp.message = "該學號之學生並未加選此課程";
				return courseInfoResp;
			}
			
			//移除已選課程
			int courseCount = 0;
			for(String courseId : selectedCourseList) {
				if(courseId.equals(courseInfo.getCourseId())) {
					break;
				}
				courseCount++;
			}
			
			selectedCourseList.remove(courseCount);
			String newSelectedCourseList = String.join(",", selectedCourseList);
			studentInfo.setSelectedCourse(newSelectedCourseList);
			
			//移除加選人員
			if(selectedPersonStr.isBlank()) {
				courseInfoResp.message = "該課程資訊內沒有此學生加選 , 請檢查資料庫是否異常";
				return courseInfoResp;
			}
			
			String[] selectedPersonArray = selectedPersonStr.split(",");
			List<String> selectedPersonList = new ArrayList<>(Arrays.asList(selectedPersonArray));
			
			int personCount = 0;
			for(String studentId : selectedPersonList) {
				if(studentId.equals(studentInfo.getStudentId())) {
					break;
				}
				personCount++;
			}
			
			selectedPersonList.remove(personCount);
			String newSelectedPersonList = String.join(",", selectedPersonList);
			courseInfo.setSelectedPerson(newSelectedPersonList);		
			//扣除學生學分
			studentInfo.setAcquiredCredit(studentInfo.getAcquiredCredit() - courseInfo.getCredit());
			
			studentInfoDao.save(studentInfo);
			courseInfoDao.save(courseInfo);
			courseInfoResp.message = "退選成功";
		}
		return courseInfoResp;
	}

}
