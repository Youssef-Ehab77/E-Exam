package eExam.controller;

import eExam.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Admin_HomePage_Controller {
    private final Professor p = Professor.getInstance();
    private final Multipurpose m = Multipurpose.getInstance();

    @FXML
    private Button btn_assign_request;

    @FXML
    private Button btn_departments_levels;

    @FXML
    private Button btn_subjects;

    @FXML
    void assign_request_press(ActionEvent e) throws IOException {
        m.change_scene(e, "Admin_Assign_Request");
    }

    @FXML
    void department_level_press(ActionEvent e) throws IOException {
        m.change_scene(e, "Admin_Departments_And_Levels");
    }

    @FXML
    void subject_press(ActionEvent e) throws IOException {
        m.change_scene(e, "Admin_Subjects_Handle");

    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_HomePage");
    }
}
