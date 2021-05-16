package eExam.model;

public class Subject {
    private static final Subject s = new Subject();
    private String subjectName;
    private String level;
    private String ID;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
