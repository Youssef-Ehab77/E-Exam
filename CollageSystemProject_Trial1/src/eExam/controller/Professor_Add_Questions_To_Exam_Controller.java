package eExam.controller;


import javafx.event.ActionEvent;

import java.io.IOException;

public class Professor_Add_Questions_To_Exam_Controller {
    private final Multipurpose m = Multipurpose.getInstance();

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject_Exam");
    }

}
