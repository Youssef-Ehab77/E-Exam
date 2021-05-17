package eExam.controller;

import eExam.model.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class Professor_Make_An_Exam_Controller {

    private final Multipurpose m = Multipurpose.getInstance();

    @FXML
    private Label lbl_welcome;
    @FXML
    private TextField tf_number_of_questions;
    @FXML
    private TextField tf_grade;
    @FXML
    private TextField tf_start_hour;
    @FXML
    private TextField tf_start_min;
    @FXML
    private TextField tf_start_sec;
    @FXML
    private TextField tf_end_hour;
    @FXML
    private TextField tf_end_min;
    @FXML
    private TextField tf_end_sec;
    @FXML
    private Button btn_confirm;
    @FXML
    private DatePicker tf_exam_day;
    @FXML
    private TextField tf_exam_name;


    public void initialize() {
        lbl_welcome.setText("Make An Exam For " + Multipurpose.subject.getSubjectName());
    }

    @FXML
    void confirm_exam(ActionEvent event) throws SQLException {
        int number_of_questions, grade;
        String name, start_time, end_time;

        name = tf_exam_name.getText();
        number_of_questions = Integer.parseInt(tf_number_of_questions.getText());
        grade = Integer.parseInt(tf_grade.getText());
        start_time = tf_exam_day.getValue().toString() + " " + tf_start_hour.getText() + ":" + tf_start_min.getText() + ":" + tf_start_sec.getText();
        end_time = tf_exam_day.getValue().toString() + " " + tf_end_hour.getText() + ":" + tf_end_min.getText() + ":" + tf_end_sec.getText();

        Exam exam = new Exam(name, grade, number_of_questions, start_time, end_time);

        Multipurpose.db.make_an_exam(exam);
        m.displayMessage("Done", "Exam Saved Successfully", "");
    }



    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject");
    }

}
