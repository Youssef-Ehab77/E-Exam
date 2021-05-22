package eExam.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * In this class we just see if the user is professor or student
 * and then set the static variable in Multipurpose class to the user's type.
 */

public class Welcome_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    @FXML
    private Button btn_professor;
    @FXML
    private Button btn_Student;

    public void press_professor(ActionEvent e) throws IOException {
        m.change_scene(e, "Professor_Login");
        Multipurpose.userType = "professor";
    }

    public void press_student(ActionEvent e) throws IOException {
        m.change_scene(e, "Student_Login");
        Multipurpose.userType = "student";
    }

}
