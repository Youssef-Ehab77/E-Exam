package eExam.model;

import java.util.ArrayList;

public class Subject {

    private int ID;
    private String subjectName;
    private int levelID;
    private int departmentID;
    private final ArrayList<Exam> exams = new ArrayList<>();

    public Subject() {

    }

    public Subject(int ID, String subjectName) {
        this.ID = ID;
        this.subjectName = subjectName;
    }

    public Subject(int ID, String subjectName, int levelID, int departmentID) {
        this.ID = ID;
        this.subjectName = subjectName;
        this.levelID = levelID;
        this.departmentID = departmentID;
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

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

}
