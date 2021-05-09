package eExam.model;

import java.sql.SQLException;

public class DB_Proxy implements DB {
    @Override
    public boolean check_login(String name, String password) throws SQLException {
        return false;
    }

    @Override
    public int add_user(String name, String password) throws SQLException {
        return 0;
    }
}
