package org.example.Util;

public class ApiEndpoint {

    public static final String BASE_URL = "http://26.84.60.246:8080";

    // Auth
    public static final String LOGIN = "/api/auth/login";
    public static final String CHANGE_PASSWORD = "/api/common/change-password";

    // Student
    public static final String STUDENT_ME = "/api/student/me";
    public static final String STUDENT_SCORE_ME = "/api/student/score/me";
    public static final String STUDENT_SCORE_ME_BY_KY = "/api/student/me/";
    public static final String STUDENT_SCORE_SUMMARY = "/api/student/score/summary";
    public static final String STUDENT_SCORE_SUMMARY_BY_KY = "/api/student/score/summary/";
    public static final String STUDENT_SCHEDULE_ME = "/api/student/schedule/me";
    public static final String STUDENT_SCHEDULE_BY_WEEK = "/api/student/schedule/date/";
    public static final String STUDENT_SCHEDULE_EXPORT = "/api/student/schedule/export";
    public static final String STUDENT_SCHEDULE_IMPORT = "/api/student/schedule/import";
    public static final String STUDENT_PICKED_CLASS = "/api/student/lich-ao/picked-class/";
    public static final String STUDENT_PICKED_SUBJECT = "/api/student/lich-ao/subject/";
    public static final String STUDENT_SCHEDULE_NEAR = "/api/student/schedule/baBuoiGanNhat";
    public static final String STUDENT_VIRTUAL_SCHEDULE_CHECK = "/api/student/lich-ao/check-them-lop";
    public static final String STUDENT_NOTE_ME = "/api/student/note/me";
    public static final String GET_ALL_MY_HOC_KY = "/api/student/get-all-my-hoc-ky";
    public static final String STUDENT_NOTE_TITLE = "/api/student/note/";
    public static final String STUDENT_NOTE_POST = "/api/student/note/addNote";
    public static final String STUDENT_NOTE_DELETE = "/api/student/note/delete";
    public static final String STUDENT_NOTE_UPDATE = "/api/student/note/update";

   // Admin
    public static final String ADMIN_STUDENT = "/api/admin/student";
    public static final String ADMIN_STUDENT_BY_MASV = "/api/admin/student/";
    public static final String ADMIN_STUDENT_DELETE_BY_MASV = "/api/admin/student/delete/";
    public static final String ADMIN_SUBJECT = "/api/admin/subject";
    public static final String ADMIN_SUBJECT_BY_MAMON = "/api/admin/subject/";
    public static final String ADMIN_CLASS = "/api/admin/class";
    public static final String ADMIN_CLASS_BY_MALOP = "/api/admin/class/";
    public static final String ADMIN_SCORE = "/api/admin/score";
    public static final String ADMIN_SCORE_BY_MaSv_STUDENT = "/api/admin/score/student/";
    public static final String ADMIN_SCORE_BY_STUDENT_CLASS = "/api/admin/score/"; //{maSv}/{maLophp}
    public static final String ADMIN_SCORE_SUMMARY_BY_MASV = "/api/admin/score/student/summary/"; //{idSv}
    public static final String ADMIN_SCHEDULE = "/api/admin/schedule";
    public static final String ADMIN_SCHEDULE_BY_STUDENT = "/api/admin/schedule/";   //{idSv}

    public static final String ADMIN_REGISTERED_CLASS_BY_STUDENT = "/api/admin/class/registered_class/";// xem sinh viên đã đăng kí những lớp nào
    public static final String ADMIN_ADD_STUDENT_TO_CLASS ="/api/admin/class/add_student/";// thêm sv vào lớp HP
    public static final String ADMIN_REMOVE_STUDENT_FROM_CLASS ="/api/admin/class/remove_student/";// xóa sv khỏi lớp HP
}

