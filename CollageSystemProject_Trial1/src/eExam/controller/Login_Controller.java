package eExam.controller;

import eExam.model.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

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
 * Multipurpose class to know wither the user is student
 * or professor.
 * If the username or password are wrong or empty we show an error message
 * to the user.
 */

public class Login_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    @FXML
    private Label lbl_errormsg;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;


    public void press_login(ActionEvent e) throws IOException, SQLException {
        String name = tf_username.getText();
        String password = pf_password.getText();
        if (!name.equals("") && !password.equals("")) {
            boolean loggedIn = Multipurpose.db.check_login(name, password);
            if (!loggedIn) {
                lbl_errormsg.setText("Wrong Username or Password!");
                m.displayMessage("Error", null, "Wrong Username or Password!");
            } else if (Multipurpose.userType.equals("professor") && p.getStatus() == 1) {
                m.change_scene(e, "Professor_HomePage");
            } else if (Multipurpose.userType.equals("professor") && p.getStatus() == 0) {
                m.displayMessage("Not Authorized!", null, "The Admin didn't approve your request yet!");
            } else {
                m.change_scene(e, "Student_HomePage");
            }
        }
    }


    //here we call this method to change the scene to the register page
    public void press_register(ActionEvent e) throws IOException {
        m.change_scene(e, "Register");
    }

    //here this method returns us to the welcome page
    public void press_home(ActionEvent e) throws IOException {
        m.change_scene(e, "WelcomePage");
    }

}
