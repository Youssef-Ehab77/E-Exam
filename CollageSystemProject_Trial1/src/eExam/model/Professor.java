package eExam.model;

import java.util.ArrayList;

public class Professor {

    private ArrayList<String> subjects = new ArrayList<>();
    private String name;
    private String password;
    private int ID;
    private boolean admin;

    //Admin


    public void addSubject(String subject) {
        this.subjects.add(subject);
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
        subjects.clear();
        admin = false;
        name = null;
        password = null;
        ID = 0;
    }
}
