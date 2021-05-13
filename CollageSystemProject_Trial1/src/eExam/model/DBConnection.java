package eExam.model;

import eExam.controller.Welcome_Controller;

import java.sql.*;

public class DBConnection implements DB {

    private Connection con;
    private Statement stmt;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //local db
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gamd1998");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sw_test1", "root", "gamd1998");

            //AWS cloud db
            //con = DriverManager.getConnection("jdbc:mysql://database-1.ccpxmnqempna.us-east-2.rds.amazonaws.com:3306/project1", "admin", "gamd1998");

            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public boolean check_login(String name, String password) throws SQLException {

        String sql;
        if (Welcome_Controller.userType.equals("student")) {
            sql = "select * from student where name = '" + name + "' and password = '" + password + "'";
        } else
            sql = "select  * from professor where name = '" + name + "' and password='" + password + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) {
            con.close();
            return false;
        } else {
            con.close();
            return true;
        }
    }

    @Override
    public int add_user(String name, String password, String gender, String dob) throws SQLException {
        String sql;
        if (Welcome_Controller.userType.equals("student")) {
            sql = "insert into student(name,password,gender,birth_date) values('" + name + "'," +
                    "'" + password + "','" + gender + "','" + dob + "')";
        } else sql = "insert into professor(name,password,gender,birth_date) values('" + name + "'," +
                "'" + password + "','" + gender + "','" + dob + "')";
        int rs = stmt.executeUpdate(sql);
        con.close();
        return rs;
    }


}
