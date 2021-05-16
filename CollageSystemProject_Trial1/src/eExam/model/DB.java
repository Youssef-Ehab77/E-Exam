package eExam.model;

import java.sql.SQLException;

public interface DB {
    boolean check_login(String name, String password) throws SQLException;

    int add_user(String name, String password, String gender, String dob) throws SQLException;

    void get_professor_subjects(int id) throws SQLException;

    void get_students_in_subject(int professor_id, String subject_name) throws SQLException;

    void update_student_grade(String student_id, String subject_name, String grade_7th, String grade_12th, String grade_final) throws SQLException;


}
