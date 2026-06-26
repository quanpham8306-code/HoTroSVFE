package org.example.Service.Admin;

public class AdminHomeService {

    private final AdminStudentService studentService =
            new AdminStudentService();

    private final AdminSubjectService subjectService =
            new AdminSubjectService();

    private final AdminClassService classService =
            new AdminClassService();

    private final AdminScoreService scoreService =
            new AdminScoreService();

    public int getTotalStudent() {
        return studentService.getAll().size();
    }

    public int getTotalSubject() {
        return subjectService.getAll().size();
    }

    public int getTotalClass() {
        return classService.getAll().size();
    }

    public int getTotalScore() {
        return scoreService.getAll().size();
    }
}
