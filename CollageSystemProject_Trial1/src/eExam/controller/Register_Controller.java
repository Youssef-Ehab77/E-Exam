package eExam.controller;

import eExam.model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Register_Controller {

    @FXML
    private ToggleGroup gender;
    @FXML
    private DatePicker date_date_of_birth;
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
        RadioButton rb = (RadioButton) gender.getSelectedToggle();
        String genderType = rb.getText();
        String dob = date_date_of_birth.getValue().toString();

        if (password.equals(confirmPassword) && !username.equals("")) {
            DBConnection db = DBConnection.getInstance();
            int value = db.add_user(username, password, genderType, dob);
            if (value != 0) {
                lbl_error.setText("Done!");
                lbl_error.setTextFill(Paint.valueOf("GREEN"));
            } else lbl_error.setText("Error Happened Inserting Data");
        } else {
            lbl_error.setText("Please Enter A Valid Username And Password and fill all the data!");
        }
    }

    public void press_back_to_login(ActionEvent e) throws IOException {
        Parent root;
        if (Welcome_Controller.userType.equals("student")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Login_Student.fxml")));
        } else root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Login_Professor.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
