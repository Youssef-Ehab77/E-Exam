package eExam.model;

import java.util.ArrayList;

public class Subject {
    private static final Subject s = new Subject();
    private String subjectName;
    private int ID;
    private ArrayList<Exam> exams = new ArrayList<>();

    private Subject() {

    }

    public static Subject getInstance() {
        return s;
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
