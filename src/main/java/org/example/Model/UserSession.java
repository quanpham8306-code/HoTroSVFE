package org.example.Model;

public class UserSession
{
    private static UserSession instance;

    private String  studentId;
    private String username;

    private UserSession(String studentId, String username) {
        this.studentId = studentId;
        this.username = username;
    }

    public static void initSession(String studentId, String username) {
        instance = new UserSession(studentId, username);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }

    public static void cleanSession() {
        instance = null;
    }
}
