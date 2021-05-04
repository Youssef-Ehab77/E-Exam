package eExam.model;

import java.util.ArrayList;

public class Student {

    private String id;
    private String name;
    private String password;
    private ArrayList<Subject> subjects = new ArrayList<>();

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
