package eExam.model;

public class Subject {
    private static final Subject s = new Subject();
    private String subjectName;
    private int ID;

    private Subject(){

    }

    public static Subject getInstance(){
        return s;
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
