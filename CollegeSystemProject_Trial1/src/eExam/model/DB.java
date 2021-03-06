package eExam.model;

import java.sql.SQLException;
import java.util.HashMap;

public interface DB {

    boolean check_login(String name, String password) throws SQLException;

    int add_user_professor(String name, String password, String gender, String dob) throws SQLException;

    int add_user_student(String name, String password, String gender, String dob, String department, String level) throws SQLException;

    int get_account_request_count() throws SQLException;

    void get_professor_subjects(int id) throws SQLException;

    void get_students_in_subject(int professor_id, String subject_name) throws SQLException;

    void update_student_grade(String student_id, String subject_name, String grade_7th, String grade_12th, String grade_final) throws SQLException;

    int make_an_exam(Exam exam) throws SQLException;

    void get_subject_exam(int professor_id, int subject_id) throws SQLException;

    void initialize_exam(int numberOfQuestion, int examID) throws SQLException;

    void get_questions_in_exam(int exam_id) throws SQLException;

    void update_question_data(int id, int grade, String type, String question, String option_1, String option_2, String option_3, String option_4, String correct_answer) throws SQLException;

    void get_all_professors() throws SQLException;

    void get_selected_professor_subjects(String name) throws SQLException;

    void assign_subject_to_professor(String professor_name, String subject_name) throws SQLException;

    void get_professors_request() throws SQLException;

    void approve_professor_request(String name) throws SQLException;

    void decline_professor_request(String name) throws SQLException;

    void get_departments() throws SQLException;

    void get_levels() throws SQLException;

    void add_new_department(String departmentName) throws SQLException;

    void add_new_level(int departmentID, String levelName) throws SQLException;

    void get_all_subjects() throws SQLException;

    void get_all_department_name(String page) throws SQLException;

    void get_all_department_levels_names(String departmentName, String page) throws SQLException;

    void add_new_subject(String subjectName, String departmentName, String levelName) throws SQLException;

    void get_student_subject(int department, int level) throws SQLException;

    void get_subject_exam_student(int subject_id) throws SQLException;

    int submit_exam(int examID, int studentID, HashMap<Integer, String> answers) throws SQLException;

    int exam_time() throws SQLException;
}
