package eExam.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Professor_Make_An_Exam_Controller {
    private final Multipurpose m = Multipurpose.getInstance();
    public TextField tf_number_of_questions;
    public TextField tf_grade;
    public TextField tf_start_time;
    public TextField tf_end_time;
    @FXML
    private Label lbl_welcome;

    public void initialize() {
        lbl_welcome.setText("Make An Exam For " + Multipurpose.subjectName);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject");
    }

}
