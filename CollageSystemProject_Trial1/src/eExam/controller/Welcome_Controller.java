package eExam.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Welcome_Controller {

    @FXML
    private Button btn_professor;
    @FXML
    private Button btn_Student;
    Login_Controller lc = new Login_Controller();

    public void press_professor(ActionEvent e) throws IOException {
        lc.userType = "professor";
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Login_Professor.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void press_student(ActionEvent e) throws IOException {
        lc.userType = "student";
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Login_Student.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
