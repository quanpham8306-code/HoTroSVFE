package org.example.Util;

public class ApiEndpoint {

    public static final String BASE_URL = "http://47.129.182.56:8080";

    // Auth
    public static final String LOGIN = "/api/auth/login";
    public static final String CHANGE_PASSWORD = "/api/common/change-password";

    // Student
    public static final String STUDENT_ME = "/api/student/me";
    public static final String STUDENT_SCORE_ME = "/api/student/score/me";
    public static final String STUDENT_SCORE_ME_BY_KY = "/api/student/score/";
    public static final String STUDENT_SCORE_SUMMARY = "/api/student/score/summary";
    public static final String STUDENT_SCORE_SUMMARY_BY_KY = "/api/student/score/summary/";
    public static final String STUDENT_SCHEDULE_ME = "/api/student/schedule/me";
    public static final String STUDENT_SCHEDULE_BY_WEEK = "/api/student/schedule/date/";
    public static final String STUDENT_SCHEDULE_EXPORT = "/api/student/schedule/export";
    public static final String STUDENT_PICKED_CLASS = "/api/student/lich-ao/picked-class/";
    public static final String STUDENT_PICKED_SUBJECT = "/api/student/lich-ao/getMon/";
    public static final String STUDENT_SCHEDULE_NEAR = "/api/student/schedule/baBuoiGanNhat";
    public static final String STUDENT_GET_VIRTUAL_SCHEDULE = "/api/student/lich-ao/me";
    public static final String STUDENT_UPSERT_VIRTUAL_SCHEDULE = "/api/student/lich-ao/save";
    public static final String STUDENT_DELETE_VIRTUAL_SCHEDULE = "/api/student/lich-ao/delete";
    public static final String STUDENT_VIRTUAL_SCHEDULE_IMPORT = "/api/student/lich-ao/importExcel";
    public static final String STUDENT_VIRTUAL_SCHEDULE_EXPORT = "/api/student/lich-ao/exportExcel";
    public static final String STUDENT_NOTE_ME = "/api/student/note/me";
    public static final String STUDENT_NOTE_TITLE = "/api/student/note/";
    public static final String STUDENT_NOTE_POST = "/api/student/note/addNote";
    public static final String STUDENT_NOTE_DELETE = "/api/student/note/delete";
    public static final String STUDENT_NOTE_UPDATE = "/api/student/note/update";

    public static final String ADMIN_STUDENT = "/api/admin/student";
    public static final String ADMIN_STUDENT_IMPORT = "/api/admin/student/importExcel";
    public static final String ADMIN_STUDENT_BY_MASV = "/api/admin/student/";
    public static final String ADMIN_STUDENT_DELETE_BY_MASV = "/api/admin/student/delete/";
    public static final String ADMIN_SUBJECT = "/api/admin/subject";
    public static final String ADMIN_SUBJECT_BY_MAMON = "/api/admin/subject/";
    public static final String ADMIN_CLASS = "/api/admin/class";
    public static final String ADMIN_CLASS_IMPORT = "/api/admin/class/importExcel";
    public static final String ADMIN_CLASS_BY_MALOP = "/api/admin/class/";
    public static final String ADMIN_CLASS_DELETE_BY_MALOP = "/api/admin/class/delete/";
    public static final String ADMIN_SCORE = "/api/admin/score";
    public static final String ADMIN_SCORE_BY_STUDENT_CLASS = "/api/admin/score/"; //{maSv}/{maLophp}
    public static final String ADMIN_RESET_PASSWORD  = "/api/admin/account/reset-password/";

    public static final String ADMIN_REGISTERED_CLASS_BY_STUDENT = "/api/admin/class/registered_class/";// xem sinh viên đã đăng kí những lớp nào
    public static final String ADMIN_ADD_STUDENT_TO_CLASS ="/api/admin/class/add_student";// thêm sv vào lớp HP
    public static final String ADMIN_STUDENT_TO_CLASS_IMPORT = "/api/admin/class/importExcelSinhVienVaoLopHP/";
    public static final String ADMIN_STUDENT_IN_CLASS = "/api/admin/class/student/";
    public static final String ADMIN_REMOVE_STUDENT_FROM_CLASS ="/api/admin/class/delete/";// xóa sv khỏi lớp HP
    public static final String ADMIN_SCORE_IMPORT ="/api/admin/score/importExcel";
    //Common
    public static final String COMMON_CHANGE_PWS = "/api/common/change-password";
}