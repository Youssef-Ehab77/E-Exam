package eExam.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.SQLException;

public class Student_Register_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    public static ObservableList<Object> departmentOL = FXCollections.observableArrayList();
    public static ObservableList<Object> levelOL = FXCollections.observableArrayList();


    @FXML
    private ChoiceBox<Object> cb_department;
    @FXML
    private ChoiceBox<Object> cb_level;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;
    @FXML
    private PasswordField pf_confirm_password;
    @FXML
    private DatePicker date_date_of_birth;
    @FXML
    private ToggleGroup gender;
    @FXML
    private Label lbl_error;


    public void initialize() throws SQLException {
        update_department_choice_box();
    }

    public void update_department_choice_box() throws SQLException {
        if (departmentOL.isEmpty()) {
            Multipurpose.db.get_all_department_name("register");
        }
        cb_department.setItems(departmentOL);
        if (!departmentOL.isEmpty()) cb_department.setValue(departmentOL.get(0));
    }

    public void press_show_button(ActionEvent e) throws SQLException {
        if (levelOL.isEmpty()) {
            Multipurpose.db.get_all_department_levels_names(cb_department.getValue().toString(), "register");
        }
        if (cb_level.getItems().isEmpty()) {
            cb_level.setItems(levelOL);
        }
        if (!levelOL.isEmpty()) cb_level.setValue(levelOL.get(0));
    }

    public void clear_levels(MouseEvent e) {
        cb_level.getItems().clear();
    }

    public void press_signup(ActionEvent e) throws SQLException {
        String dob = null, department, level = null;
        String username = tf_username.getText();
        String password = pf_password.getText();
        String confirmPassword = pf_confirm_password.getText();
        RadioButton rb = (RadioButton) gender.getSelectedToggle();
        String genderType = rb.getText();
        if (date_date_of_birth.getValue() != null) dob = date_date_of_birth.getValue().toString();
        department = cb_department.getValue().toString();
        if (!cb_level.getItems().isEmpty()) level = cb_level.getValue().toString();

        if (password.equals(confirmPassword) && !username.equals("") && department != null && level != null) {
            int value = Multipurpose.db.add_user_student(username, password, genderType, dob, department, level);
            if (value != 0) {
                lbl_error.setText("Done!");
                m.displayMessage("Done", "You Have Created A New Account!", null);
                lbl_error.setTextFill(Paint.valueOf("GREEN"));
            } else lbl_error.setText("Error Happened Inserting Data");
        } else {
            lbl_error.setText("Please Enter A Valid Username And Password and fill all the data!");
            m.displayMessage("Error", "Please Enter A Valid Username And Password and fill all the data!", null);
        }
    }

    public void press_back_to_login(ActionEvent e) throws IOException {
        m.change_scene(e, "Student_Login");
    }
}
