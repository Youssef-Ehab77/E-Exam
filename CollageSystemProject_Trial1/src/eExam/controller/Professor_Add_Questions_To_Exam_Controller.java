package eExam.controller;


import eExam.model.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Professor_Add_Questions_To_Exam_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private Exam e = null;
    @FXML
    private Label lbl_welcome;

    public void initialize() {

        for (Exam exam : Multipurpose.subject.getExams()) {
            if (exam.getName().equals(Multipurpose.exam.getName())) {
                e = exam;
                break;
            }
        }
        assert e != null;
        lbl_welcome.setText("Exam " + e.getName() + " for " + Multipurpose.subject.getSubjectName() + " subject");


    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject_Exam");
    }

}
