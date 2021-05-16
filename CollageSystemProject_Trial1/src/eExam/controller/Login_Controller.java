package eExam.controller;

import eExam.model.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class Login_Controller {

    @FXML
    private Label lbl_errormsg;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;


    /**
     * press_login()-->
     * <p>
     * After pressing login in the fxml login page
     * the method take the text value in the username and password field
     * and put the in  @name and @password variables.
     * After that it checks if the name and password are not empty
     * then it makes an object from the database class and sends the
     * name and the password to a method called check_login to check if
     * the username and the password are in the database.
     * NOTE:: In the database they call the static method userType form
     * Welcome_Controller class to know wither if the user is student
     * or professor.
     * If the username or password are wrong or empty we show an error message
     * to the user.
     */
    public void press_login(ActionEvent event) throws IOException, SQLException {
        String name = tf_username.getText();
        String password = pf_password.getText();
        if (!name.equals("") && !password.equals("")) {
            DBConnection dbc = DBConnection.getInstance();
            boolean loggedIn = dbc.check_login(name, password);
            if (!loggedIn) {
                lbl_errormsg.setText("Wrong Username or Password!");
                displayMessage();
            } else if (loggedIn && Welcome_Controller.userType.equals("student")) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Student_HomePage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } else {
                Professor_HomePage_Controller.professor.setName(name);
                Professor_HomePage_Controller.professor.setPassword(password);
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_HomePage.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            }
        }
    }


    //here we call this method to change the scene to the register page
    public void press_register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Register.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    //here this method returns us to the welcome page
    public void press_home(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/WelcomePage.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void displayMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Wrong Username or Password!");
        alert.showAndWait();
    }



}
