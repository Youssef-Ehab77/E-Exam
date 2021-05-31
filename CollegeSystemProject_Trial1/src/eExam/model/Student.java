package eExam.model;

import eExam.controller.Multipurpose;

import java.util.ArrayList;

public class Student {

    private static final Student s = new Student();
    private int ID;
    private String name;
    private String password;
    private int departmentID;
    private int levelID;
    private ArrayList<Subject> subjects = new ArrayList<>();

    private Student() {

    }

    public static Student getInstance() {
        return s;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }


    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public void logout() {
        ID = 0;
        name = null;
        password = null;
        departmentID = 0;
        levelID = 0;
        subjects.clear();
        Multipurpose.subjectInUse = null;
        Multipurpose.examInUse = null;
    }
}
