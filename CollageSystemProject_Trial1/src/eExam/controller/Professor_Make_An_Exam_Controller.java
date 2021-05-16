package eExam.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Professor_Make_An_Exam_Controller {

    @FXML
    private Label lbl_welcome;

    public void initialize() {
        lbl_welcome.setText("Make An Exam For " + Professor_Subject_Controller.subjectName);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        Parent r;
        String clicked = ((Button) e.getSource()).getText();
        if (clicked.equals("Home")) {
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_HomePage.fxml")));
        } else if (clicked.equals("Logout")) {
            Professor_HomePage_Controller.professor.logout();
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Login.fxml")));
        } else {
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Subject.fxml")));
        }
        assert r != null;
        Scene scene = new Scene(r);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

}
