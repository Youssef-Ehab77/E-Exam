package eExam.model;

import java.sql.SQLException;

public interface DB {
    boolean check_login(String name, String password) throws SQLException;

    int add_user(String name, String password, String gender, String dob) throws SQLException;

}
