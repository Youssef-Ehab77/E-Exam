package eExam.model;

public class Student_Subjects {

    private static final Student_Subjects ss = new Student_Subjects();
    public int id, grade_7th, grade_12th, grade_final;
    public String name;

    private Student_Subjects(){

    }

    public static Student_Subjects getInstance(){
        return ss;
    }

    public Student_Subjects(int id, String name, int grade_7th, int grade_12th, int grade_final) {
        this.id = id;
        this.name = name;
        this.grade_7th = grade_7th;
        this.grade_12th = grade_12th;
        this.grade_final = grade_final;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudent_id() {
        return id;
    }

    public void setStudent_id(int student_id) {
        this.id = student_id;
    }

    public int getGrade_7th() {
        return grade_7th;
    }

    public void setGrade_7th(int grade_7th) {
        this.grade_7th = grade_7th;
    }

    public int getGrade_12th() {
        return grade_12th;
    }

    public void setGrade_12th(int grade_12th) {
        this.grade_12th = grade_12th;
    }

    public int getGrade_final() {
        return grade_final;
    }

    public void setGrade_final(int grade_final) {
        this.grade_final = grade_final;
    }


}
