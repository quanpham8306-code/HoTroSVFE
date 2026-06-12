package org.example.Util;

public class ApiEndpoint {

    public static final String BASE_URL = "http://26.108.243.246:8080";

    // Auth
    public static final String LOGIN = "/api/auth/login";
    public static final String CHANGE_PASSWORD = "/api/common/change-password";

    // Student
    public static final String STUDENT_ME = "/api/student/me";
    public static final String STUDENT_SCORE_ME = "/api/student/score/me";
    public static final String STUDENT_SCORE_SUMMARY = "/api/student/score/summary";
    public static final String STUDENT_SCHEDULE_ME = "/api/student/schedule/me";
    public static final String STUDENT_SCHEDULE_EXPORT = "/api/student/schedule/export";
    public static final String STUDENT_VIRTUAL_SCHEDULE_CHECK = "/api/student/lich-ao/check-them-lop";

    // Admin
    public static final String ADMIN_STUDENT = "/api/admin/student";
    public static final String ADMIN_STUDENT_BY_ID = "/api/admin/student/{id}";
    public static final String ADMIN_SUBJECT = "/api/admin/subject";
    public static final String ADMIN_SUBJECT_BY_ID = "/api/admin/subject/{id}";
    public static final String ADMIN_CLASS = "/api/admin/class";
    public static final String ADMIN_CLASS_BY_ID = "/api/admin/class/{id}";
    public static final String ADMIN_SCORE = "/api/admin/score";
    public static final String ADMIN_SCORE_BY_STUDENT = "/api/admin/score/student/{idSv}";
    public static final String ADMIN_SCORE_BY_STUDENT_CLASS = "/api/admin/score/{idSv}/{idLhp}";
    public static final String ADMIN_SCHEDULE = "/api/admin/schedule";
    public static final String ADMIN_SCHEDULE_BY_STUDENT = "/api/admin/schedule/{idSv}";
    public static final String ADMIN_SCHEDULE_BY_ID = "/api/admin/schedule/{idTkb}";
}