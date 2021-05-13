package eExam.controller;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.sql.*;
import java.util.ArrayList;

public class Professor_HomePage_Controller {

    public TabPane tp;
    static String name;
    static String password;


    public void initialize() throws SQLException {
        Statement stmt = null;
        ArrayList<String> al = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "gamd1998");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }

        String sql = "select name\n" +
                "from professor_subject ps\n" +
                "         join subject s\n" +
                "              on ps.subject_id = id\n" +
                "where professor_id =\n" +
                "      (select id from professor where name ='" + name + "' and password = '" + password + "')";

        assert stmt != null;
        ResultSet rs = stmt.executeQuery(sql);
        // we used the system.out to get the name of the 1st tab loaded on the screen (Fixed the problem on the 1st try)
        if (rs.isBeforeFirst()) System.out.println(tp.getSelectionModel().selectedItemProperty().get().getText());
        tp.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(newValue.getText()));

        while (rs.next()) {
            al.add(rs.getString(1));
        }

        for (String name : al) {
            tp.getTabs().add(new Tab(name));
        }
    }
}

