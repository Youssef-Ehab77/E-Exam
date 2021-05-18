package eExam.controller;

import eExam.model.Professor;
import eExam.model.Subject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @Method initialize
 * we check if the professor's subject arrayList is empty, if so we get them form the database
 * by professor's id and put them in the ArrayList.
 * <p>
 * In The for loop:
 * we creat buttons manually for the Professor_HomePage, the number of buttons equal to the number
 * of subjects that the professor teach. (we call the subjects from the arraylist we implemented earlier).
 * We have Buttons_HomePage.css file that added the design to the buttons so we dont have to write all
 * the frontend design work here.
 * <p>
 * We set the button text and id equals to the subject and we added a
 * setOnAction function on all the buttons call the function change_scene to change the scene
 * with the name of the button to start making action on this subject.
 * <p>
 * int x_axis is the x-axis of the root AnchorPane in the FXML page. 1000px
 * int y_axis is the y-axis of the root AnchorPane in the FXML page. 800px
 * <p>
 * The if condition ( x and y ) changes the position of the button placements.
 * So our scene is 1000x800px and the button size is 300x180 and we move it with that if condition
 * to put them on the screen.
 * <p>
 * Last of all the navigation_handler is the method that controllers the navigation between the scenes ('home page',
 * 'logout', 'go back', etc).
 */

public class Professor_HomePage_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbl_welcome;
    private int x_axis = 25, y_axis = 100;

    public void initialize() throws SQLException, IOException {
        lbl_welcome.setText("Welcome Professor " + p.getName());
        if (p.getSubjects().isEmpty()) {
            Multipurpose.db.get_professor_subjects(p.getID());
        }
        for (Subject subject : p.getSubjects()) {
            Button btn = new Button();
            btn.setText(subject.getSubjectName());
            btn.setId(subject.getSubjectName());
            btn.setLayoutX(x_axis);
            btn.setLayoutY(y_axis);
            btn.setOnAction(e -> {
                Multipurpose.subjectInUse = subject;
                try {
                    change_scene(btn);
                } catch (IOException | SQLException ioException) {
                    ioException.printStackTrace();
                }
            });
            root.getChildren().add(btn);
            if (x_axis < 675) {
                x_axis += 325;
            } else {
                x_axis = 25;
                y_axis += 200;
            }
        }
    }

    public void change_scene(Button e) throws IOException, SQLException {
        Parent r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Subject.fxml")));
        Scene scene = new Scene(r);
        Stage stage = (Stage) e.getScene().getWindow();
        stage.setScene(scene);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_HomePage");
    }
}

