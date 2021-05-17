package eExam.model;

import java.sql.SQLException;

public class DB_Proxy implements DB {

    private static final DB db_proxy = new DB_Proxy();
    private final DB real_db = DBConnection.getInstance();

    private DB_Proxy() {

    }

    public static DB getInstance() {
        return db_proxy;
    }

    @Override
    public boolean check_login(String name, String password) throws SQLException {
        return real_db.check_login(name, password);
    }

    @Override
    public int add_user(String name, String password, String gender, String dob) throws SQLException {
        return real_db.add_user(name, password, gender, dob);
    }

    @Override
    public void get_subject_data(String name) throws SQLException {
        real_db.get_subject_data(name);
    }

    @Override
    public void get_professor_subjects(int id) throws SQLException {
        real_db.get_professor_subjects(id);
    }

    @Override
    public void get_students_in_subject(int professor_id, String subject_name) throws SQLException {
        real_db.get_students_in_subject(professor_id, subject_name);
    }

    @Override
    public void update_student_grade(String student_id, String subject_name, String grade_7th, String grade_12th, String grade_final) throws SQLException {
        real_db.update_student_grade(student_id, subject_name, grade_7th, grade_12th, grade_final);
    }

    @Override
    public void make_an_exam(Exam exam) throws SQLException {
        real_db.make_an_exam(exam);
    }

    @Override
    public void get_subject_exam(int professor_id, int subject_id) throws SQLException {
        real_db.get_subject_exam(professor_id, subject_id);
    }

}
