# CourseSelectSystem
Personal Project - Course Select System
專案更新於develop分支

postman API測試說明:

路徑
RequestMapping("/api")

============================================= course service ==================================================

"/setCourseInfo":
JSON陣列格式填入 (設定多個)
陣列名稱courseInfoList
陣列內容為物件如下方單個的方法填入
JSON物件格式填入 (設定單個)
courseId(String) . courseName(String) . lessonDay(String) . startTime(String) . endTime(String) . credit(int) . selectLimit(int)

"/getAllCourseInfo":
無須send Req

"/getCourseInfoById":
JSON物件格式填入
courseId(String)

"/getCourseInfoByCourseName":
JSON物件格式填入
courseName(String)

"/editCourse":
JSON物件格式填入
既有的 courseId(String) . 修改的 courseName(String) . lessonDay(String) . startTime(String) . endTime(String) . credit(int) . selectLimit(int)

"/deleteCourse":
JSON物件格式填入
courseId(String)

"/selectCourse":
JSON物件格式填入
studentId(String)
courseId(String)
若欲選多堂課將courseId改為
selectCourseList(String array)
以陣列格式輸入課程代碼

"/dropCourse":
JSON物件格式填入
studentId(String)
courseId(String)

============================================= student service ==================================================

"/setStudentInfo":
JSON陣列格式填入 (設定多個)
陣列名稱studentInfoList
陣列內容為物件如下方單個的方法填入
JSON物件格式填入
studentId(String) . name(String) . selectedCourse(String) . acquiredCredit(int) . password(String) . administrator(boolean)

"/getAllStudentInfo":
無須send Req

"/getStudentInfoByStudentId":
JSON物件格式填入
studentId(String)

"/deleteStudentInfo":
JSON物件格式填入
studentId(String)

"/getSelectedCourseInfo":
JSON物件格式填入
studentId(String)

"/editStudentInfo":
JSON物件格式填入
studentId(String) . newStudentId(String) . name(String) . acquiredCredit(int) . password(String) . administrator(boolean)

"/editPassword":
JSON物件格式填入
studentId(String) . newPassword(String)
