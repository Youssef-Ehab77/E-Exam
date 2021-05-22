package eExam.model;

import eExam.controller.*;

import java.sql.*;

public class DBConnection implements DB {

    private static final DBConnection db = new DBConnection();
    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    private final Student s = Student.getInstance();
    private Statement stmt;

    /**
     * DBConnection is the constructor where i set my connection to database
     * wither its a cloud database (AWS) or local host
     */
    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //local db
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gamd1998");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sw_test1", "root", "gamd1998");

            //AWS cloud db
            //con = DriverManager.getConnection("jdbc:mysql://database-1.ccpxmnqempna.us-east-2.rds.amazonaws.com:3306/project1", "admin", "gamd1998");

            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public static DBConnection getInstance() {
        return db;
    }

    /**
     * check_login method checks if the user is in the database and wither
     * he is professor or a student
     *
     * @param name     username that the user entered
     * @param password password that the user entered
     * @return returns a boolean to know wither if the username are correct or even exist or not
     * @throws SQLException we call the static variable @userType from the welcome page controller to know wither
     *                      the user entered the professor or the student portal to login.
     *                      if the data was right we set the user id to use it later on.
     */
    public boolean check_login(String name, String password) throws SQLException {
        String sql;
        if (Multipurpose.userType.equals("student")) {
            sql = "select * from student where name = '" + name + "' and password = '" + password + "'";
        } else
            sql = "select  * from professor where name = '" + name + "' and password='" + password + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) {
            return false;
        } else if (Multipurpose.userType.equals("professor")) {
            rs.next();
            p.setID(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPassword(rs.getString("password"));
            p.setAdmin(rs.getInt("admin"));
            p.setGender(rs.getString("gender"));
            p.setBirthDate(rs.getString("birth date"));
            p.setStatus(rs.getInt("status"));
        } else {
            rs.next();
            s.setID(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setPassword(rs.getString("password"));
            s.setDepartmentID(rs.getInt("department_id"));
            s.setLevelID(rs.getInt("level_id"));
        }
        return true;
    }


    /**
     * @param name     username that the user entered
     * @param password password that the user entered and we confirm it before we send it
     * @param gender   user gender
     * @param dob      date of birth
     * @return returns zero or value to confirm that the edit is done
     * @throws SQLException And here we also check wither the user is professor or student.
     */

    @Override
    public int add_user_professor(String name, String password, String gender, String dob) throws SQLException {
        String sql = "insert into professor(name,password,gender,`birth date`) values('" + name + "'," +
                "'" + password + "','" + gender + "','" + dob + "')";
        int rs = stmt.executeUpdate(sql);
        return rs;
    }

    @Override
    public int add_user_student(String name, String password, String gender, String dob, String department, String level) throws SQLException {
        String sql = "insert into student(name,password,gender,birth_date,department_id,level_id) values('" + name + "'," +
                "'" + password + "','" + gender + "','" + dob + "',(select id from department where name = '" + department + "')," +
                "(select id from level where name = '" + level + "'))";
        int rs = stmt.executeUpdate(sql);
        return rs;
    }

    @Override
    public int get_account_request_count() throws SQLException {
        String sql = "select Count(status) from professor where status = 0";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * @param id user id
     * @throws SQLException We get all the subjects that the professor teaches and add it to the Professor's
     *                      subject Arraylist by calling the static object from the professor home page controller.
     */

    public void get_professor_subjects(int id) throws SQLException {
        String sql = "select id, name\n" +
                "from professor_subject ps\n" +
                "         join subject s\n" +
                "              on ps.subject_id = id\n" +
                "where professor_id =\n " + id;
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            p.addSubject(new Subject(rs.getInt("id"), rs.getString("name")));
        }
    }

    public void get_students_in_subject(int professor_id, String subject_name) throws SQLException {
        String sql = "SELECT id, name, 7th, 12th, final\n" +
                "from student_subject ss\n" +
                "         join student s\n" +
                "              on ss.student_id = s.id\n" +
                "where ss.professor_id = " + professor_id + "\n" +
                "  and ss.subject_id =(select id from subject where subject.name = '" + subject_name + "')";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Professor_Subject_Controller.ol.add(new Student_Subjects(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("7th"), rs.getInt("12th"), rs.getInt("final")));
        }
    }

    public void update_student_grade(String student_id, String subject_name, String grade_7th, String grade_12th, String grade_final) throws SQLException {
        String sql = "UPDATE student_subject\n" +
                "SET 7th   = " + grade_7th + ",\n" +
                "    12th  = " + grade_12th + ",\n" +
                "    final = " + grade_final + "\n" +
                "WHERE (student_id = " + student_id + ")\n" +
                "  and subject_id = (select id from subject where name ='" + subject_name + "')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public int make_an_exam(Exam exam) throws SQLException {
        String sql = "INSERT INTO `exam` (`name`, `grade`, `number_of_questions`, `start_time`, `end_time`,`professor_id`,`subject_id`)\n" +
                "VALUES ('" + exam.getName() + "', '" + exam.getGrade() + "', '"
                + exam.getNumberOfQuestions() + "', '" + exam.getStart_time() + "', '" + exam.getEnd_time() + "','" +
                p.getID() + "','" + Multipurpose.subjectInUse.getID() + "')";
        int rs = stmt.executeUpdate(sql);
        String sql2 = "SELECT id from exam where name = '" + exam.getName() + "' and " +
                "professor_id = '" + p.getID() + "' and subject_id = '" + Multipurpose.subjectInUse.getID() + "'";
        ResultSet rs2 = stmt.executeQuery(sql2);
        rs2.next();
        return rs2.getInt("id");
    }

    @Override
    public void get_subject_exam(int professor_id, int subject_id) throws SQLException {
        String sql = "select id, name,grade,number_of_questions,start_time,end_time from exam where professor_id = " + professor_id + " " +
                "and subject_id = " + subject_id + "";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Multipurpose.subjectInUse.addExam(new Exam(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("grade"), rs.getInt("number_of_questions"), rs.getString("start_time"),
                    rs.getString("end_time")));
        }
    }

    @Override
    public void initialize_exam(int numberOfQuestion, int examID) throws SQLException {
        String sql = "insert into questions (exam_id) values ('" + examID + "')";
        int rs;
        for (int i = 0; i < numberOfQuestion; i++) {
            rs = stmt.executeUpdate(sql);
        }
    }

    @Override
    public void get_questions_in_exam(int exam_id) throws SQLException {
        String sql = "select * from questions where exam_id  = " + exam_id + "";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Professor_Add_Questions_To_Exam_Controller.observableList.add(new Questions(rs.getInt("id"), rs.getInt("grade"),
                    rs.getString("type"), rs.getString("question"), rs.getString("option_1"),
                    rs.getString("option_2"), rs.getString("option_3"), rs.getString("option_4"),
                    rs.getString("correct_answer")));
        }
    }

    @Override
    public void update_question_data(int id, int grade, String type, String question, String option_1, String option_2, String option_3, String option_4, String correct_answer) throws SQLException {
        String sql = "UPDATE questions SET `type` = '" + type + "', `question` = '" + question + "'," +
                " `option_1` = '" + option_1 + "', `option_2` = '" + option_2 + "', `option_3` = '" + option_3 + "'," +
                " `option_4` = '" + option_4 + "', `correct_answer` = '" + correct_answer + "', `grade` = '" + grade +
                "' WHERE (`id` = '" + id + "') and (`exam_id` = '" + Multipurpose.examInUse.getID() + " ')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void get_all_professors() throws SQLException {
        String sql = "SELECT name from professor where status = 1";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Admin_Assign_Request_Controller.professorsOL.add(rs.getString("name"));
        }
    }

    @Override
    public void get_selected_professor_subjects(String name) throws SQLException {
        String sql = "select name\n" +
                "from subject\n" +
                "where name not in\n" +
                "      (select name\n" +
                "       from subject s\n" +
                "                join (select *\n" +
                "                      from professor_subject\n" +
                "                      where professor_id = (select id from professor where professor.name = '" + name + "')) ps\n" +
                "                     on s.id = ps.subject_id)";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Admin_Assign_Request_Controller.subjectsOL.add(rs.getString("name"));
        }
    }

    @Override
    public void assign_subject_to_professor(String professor_name, String subject_name) throws SQLException {
        String sql = "insert into professor_subject (professor_id, subject_id)\n" +
                "values ((select id from professor where name = '" + professor_name + "'), " +
                "(select id from subject where name = '" + subject_name + "'));";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void get_professors_request() throws SQLException {
        String sql = "SELECT name from professor where status = 0";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Admin_Assign_Request_Controller.requestsOL.add(rs.getString("name"));
        }
    }

    @Override
    public void approve_professor_request(String name) throws SQLException {
        String sql = "UPDATE professor SET status = 1 WHERE id = (select id from  (select id,name from professor)AS p where p.name = '" + name + "')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void decline_professor_request(String name) throws SQLException {
        String sql = "DELETE from professor where id = (select id from (select * from professor) as pn where pn.name = '" + name + "')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void get_departments() throws SQLException {
        String sql = "select * from department";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Admin_Departments_And_Levels_Controller.departmentOL.add(new Department(rs.getInt("id"), rs.getString("name")));
        }
    }

    @Override
    public void get_levels() throws SQLException {
        String sql = "select * from level";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Admin_Departments_And_Levels_Controller.levelOL.add(new Level(rs.getInt("id"), rs.getInt("department_id"), rs.getString("name")));
        }
    }

    @Override
    public void add_new_department(String departmentName) throws SQLException {
        String sql = "INSERT INTO department (name) VALUES ('" + departmentName + "')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void add_new_level(int departmentID, String levelName) throws SQLException {
        String sql = "INSERT INTO level (name, department_id) VALUES ('" + levelName + "','" + departmentID + "')";
        int rs = stmt.executeUpdate(sql);
    }

    @Override
    public void get_all_subjects() throws SQLException {
        String sql = "SELECT * FROM subject";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Admin_Subjects_Handle_Controller.subjectOL.add(new Subject(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("level_id"), rs.getInt("department_id")));
        }
    }

    @Override
    public void get_all_department_name(String page) throws SQLException {
        String sql = "SELECT name FROM department";
        ResultSet rs = stmt.executeQuery(sql);

        if (page.equals("admin")) {
            while (rs.next()) {
                Admin_Subjects_Handle_Controller.departmentOL.add(rs.getString("name"));
            }
        } else {
            while (rs.next()) {
                Student_Register_Controller.departmentOL.add(rs.getString("name"));
            }
        }

    }

    @Override
    public void get_all_department_levels_names(String departmentName, String page) throws SQLException {
        String sql = "SELECT name FROM level where department_id = (SELECT id FROM department where name = '" + departmentName + "')";
        ResultSet rs = stmt.executeQuery(sql);
        if (page.equals("admin")) {
            while (rs.next()) {
                Admin_Subjects_Handle_Controller.levelOL.add(rs.getString("name"));
            }
        } else {
            while (rs.next()) {
                Student_Register_Controller.levelOL.add(rs.getString("name"));
            }
        }

    }

    @Override
    public void add_new_subject(String subjectName, String departmentName, String levelName) throws SQLException {
        String sql = "INSERT INTO subject (name, level_id, department_id) VALUES ('" + subjectName + "'," +
                "(SELECT id FROM level WHERE level.name ='" + levelName + "')," +
                "(SELECT id FROM department WHERE department.name = '" + departmentName + "'))";
        int rs = stmt.executeUpdate(sql);
    }
}
