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
    public int add_user_professor(String name, String password, String gender, String dob) throws SQLException {
        return real_db.add_user_professor(name, password, gender, dob);
    }

    @Override
    public int add_user_student(String name, String password, String gender, String dob, String department, String level) throws SQLException {
        return real_db.add_user_student(name, password, gender, dob, department, level);
    }

    @Override
    public int get_account_request_count() throws SQLException {
        return real_db.get_account_request_count();
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
    public int make_an_exam(Exam exam) throws SQLException {
        return real_db.make_an_exam(exam);
    }

    @Override
    public void get_subject_exam(int professor_id, int subject_id) throws SQLException {
        real_db.get_subject_exam(professor_id, subject_id);
    }

    @Override
    public void initialize_exam(int numberOfQuestion, int examID) throws SQLException {
        real_db.initialize_exam(numberOfQuestion, examID);
    }

    @Override
    public void get_questions_in_exam(int exam_id) throws SQLException {
        real_db.get_questions_in_exam(exam_id);
    }

    @Override
    public void update_question_data(int id, int grade, String type, String question, String option_1, String option_2, String option_3, String option_4, String correct_answer) throws SQLException {
        real_db.update_question_data(id, grade, type, question, option_1, option_2, option_3, option_4, correct_answer);
    }

    @Override
    public void get_all_professors() throws SQLException {
        real_db.get_all_professors();
    }

    @Override
    public void get_selected_professor_subjects(String name) throws SQLException {
        real_db.get_selected_professor_subjects(name);
    }

    @Override
    public void assign_subject_to_professor(String professor_name, String subject_name) throws SQLException {
        real_db.assign_subject_to_professor(professor_name, subject_name);
    }

    @Override
    public void get_professors_request() throws SQLException {
        real_db.get_professors_request();
    }

    @Override
    public void approve_professor_request(String name) throws SQLException {
        real_db.approve_professor_request(name);
    }

    @Override
    public void decline_professor_request(String name) throws SQLException {
        real_db.decline_professor_request(name);
    }

    @Override
    public void get_departments() throws SQLException {
        real_db.get_departments();
    }

    @Override
    public void get_levels() throws SQLException {
        real_db.get_levels();
    }

    @Override
    public void add_new_department(String departmentName) throws SQLException {
        real_db.add_new_department(departmentName);
    }

    @Override
    public void add_new_level(int departmentID, String levelName) throws SQLException {
        real_db.add_new_level(departmentID, levelName);
    }

    @Override
    public void get_all_subjects() throws SQLException {
        real_db.get_all_subjects();
    }

    @Override
    public void get_all_department_name(String page) throws SQLException {
        real_db.get_all_department_name(page);
    }

    @Override
    public void get_all_department_levels_names(String departmentName, String page) throws SQLException {
        real_db.get_all_department_levels_names(departmentName, page);
    }

    @Override
    public void add_new_subject(String subjectName, String departmentName, String levelName) throws SQLException {
        real_db.add_new_subject(subjectName, departmentName, levelName);
    }

}
