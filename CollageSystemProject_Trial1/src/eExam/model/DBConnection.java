package eExam.model;

import java.sql.*;

public class DBConnection implements DB {

    private Connection con;
    private Statement stmt;

    public DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //local db
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gamd1998");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sw_test1", "root", "gamd1998");

            //AWS cloud db
            //con = DriverManager.getConnection("jdbc:mysql://database-1.ccpxmnqempna.us-east-2.rds.amazonaws.com:3306/project1", "admin", "gamd1998");

            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
    }

    public boolean check_login(String name, String password) throws SQLException {

        String sql = "select * from login where Name = '" + name + "' and BINARY Password = '" + password + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.isBeforeFirst()) {
            return false;
        } else {
            con.close();
            return true;
        }
    }

    public int add_user(String name, String password) throws SQLException {
        String sql = "insert into login(name,password) values('" + name + "','" + password + "')";
        int rs = stmt.executeUpdate(sql);
        con.close();
        return rs;
    }

}
