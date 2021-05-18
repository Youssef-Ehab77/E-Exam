package eExam.controller;


import eExam.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Professor_Add_Questions_To_Exam_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    @FXML
    private Label lbl_welcome;

    public void initialize() {
        lbl_welcome.setText("Exam " + Multipurpose.examInUse.getName() + " for " + Multipurpose.subjectInUse.getSubjectName() + " subject");
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject_Exam");
    }

}
