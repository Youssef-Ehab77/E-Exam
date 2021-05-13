package eExam.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class Professor_Subject_Controller {
    @FXML
    private Label lbl_subject_name;
    public static String subjectName;

    public void initialize(){
        lbl_subject_name.setText("You Clicked "+subjectName+" MOTHERFUCKER!!");
    }
}
