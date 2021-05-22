package eExam.model;

public class Level {

    private int ID;
    private int departmentID;
    private String name;

    public Level(int ID, int departmentID, String name) {
        this.ID = ID;
        this.departmentID = departmentID;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
