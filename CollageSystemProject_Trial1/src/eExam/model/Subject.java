package eExam.model;

import java.util.ArrayList;

public class Subject {

    private int ID;
    private String subjectName;
    private final ArrayList<Exam> exams = new ArrayList<>();

    public Subject() {

    }

    public Subject(int ID, String subjectName) {
        this.ID = ID;
        this.subjectName = subjectName;
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
