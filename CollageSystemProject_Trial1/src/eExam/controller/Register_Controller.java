package eExam.controller;

import eExam.model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Register_Controller {
    @FXML
    private Label lbl_error;
    @FXML
    private PasswordField pf_confirm_password;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private Button btn_signup;


    public void press_signup(ActionEvent e) throws SQLException {
        String username = tf_username.getText();
        String password = pf_password.getText();
        String confirmPassword = pf_confirm_password.getText();

        if (password.equals(confirmPassword)) {
            DBConnection db = new DBConnection();
            int value = db.add_user(username, password);
            if (value != 0) {
                lbl_error.setText("Done!");
                lbl_error.setTextFill(Paint.valueOf("GREEN"));
            }else lbl_error.setText("Error Happened Inserting Data");
        } else {
            lbl_error.setText("Please Enter A Valid Username And Password");
        }
    }

    public void press_back_to_login(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Login_Student.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
