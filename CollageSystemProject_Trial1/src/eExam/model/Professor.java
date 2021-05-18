package eExam.model;

import eExam.controller.Multipurpose;

import java.util.ArrayList;

public class Professor {

    private static final Professor p = new Professor();
    private int ID;
    private String name;
    private String password;
    private boolean admin;
    private String gender;
    private String birthDate;
    private ArrayList<String> subjects = new ArrayList<>();


    private Professor(){

    }

    public static Professor getInstance() {
        return p;
    }


    public void addSubject(String subject) {
        this.subjects.add(subject);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public void logout() {
        ID = 0;
        name = null;
        password = null;
        admin = false;
        gender = null;
        birthDate = null;
        subjects.clear();
        Multipurpose.subject.getExams().clear();
    }


}
