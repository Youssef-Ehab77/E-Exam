package eExam.controller;

import eExam.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Multipurpose class is for multipurpose stuff duh!
 * 1- We can from this class class some main static variables that will be needed throw out
 * the project.
 * <p>
 * 2- We also created an instance from every class we are going to use so we can call them from here.
 * <p>
 * 3- We also created some functions that will be called several time in different situations
 * like changing the current scene or navigate throw the project's pages.
 */

public class Multipurpose {

    private static final Multipurpose m = new Multipurpose();
    public static Professor professor = Professor.getInstance();
    public static final DB db = DB_Proxy.getInstance();
    public static final Subject subject = Subject.getInstance();
    public static Exam exam = new Exam();
    public static String userType;

    private Multipurpose() {
    }

    public static Multipurpose getInstance() {
        return m;
    }

    public void navigation_handler(ActionEvent e, String page) throws IOException {
        String clicked = ((Button) e.getSource()).getText();
        Parent r;
        if (clicked.equals("Home")) {
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_HomePage.fxml")));
        } else if (clicked.equals("Logout")) {
            Professor.getInstance().logout();
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Login.fxml")));
        } else {
            r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/" + page + ".fxml")));
        }
        assert r != null;
        Scene scene = new Scene(r);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void displayMessage(String title, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void change_scene(ActionEvent event, String page) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/" + page + ".fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

}
