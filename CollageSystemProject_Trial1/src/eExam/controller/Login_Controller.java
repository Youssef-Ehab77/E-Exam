package eExam.controller;

import eExam.model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Login_Controller {

    public String userType;
    @FXML
    private Label lbl_errormsg;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;


    public void press_login(ActionEvent event) throws IOException, SQLException {
        String name = tf_username.getText();
        String password = pf_password.getText();

        if (!name.equals("") && !password.equals("")) {
            DBConnection dbc = new DBConnection();
            boolean loggedIn = dbc.check_login(name, password);
            if (loggedIn) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/HomePage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } else lbl_errormsg.setText("Wrong Username or Password!");
        }
    }


    public void press_register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Register.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


}
