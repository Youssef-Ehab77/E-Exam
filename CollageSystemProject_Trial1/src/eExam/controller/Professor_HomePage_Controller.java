package eExam.controller;

import eExam.model.DBConnection;
import eExam.model.Professor;
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

public class Professor_HomePage_Controller {

    /**
     * We create two objects 1. DBConnection  2. Professor.
     * We get the professor name from the getter in professor class.
     * And then we call get_professor_subjects from the DBConnection class
     * and in the method it gets all the subjects from the database by the professor
     * id and put them by calling the professor setter method throw the static
     * object we created here to set all the subjects in an ArrayList.
     * <p>
     * The for loop:
     * we creat buttons manually for the Professor_HomePage, the number of buttons equal to the number
     * of subjects that the professor teach. (we call the subjects from the arraylist we implemented earlier).
     * We have Buttons_HomePage.css file that added the design to the buttons so we dont have to write all
     * the frontend work here.
     * <p>
     * We set the button text and id equals to the subject and we added a
     * setOnAction function on all the buttons to take us to the next page based on what we clicked.
     * <p>
     * int x_axis is the x-axis of the root AnchorPane in the FXML page. 1000px
     * int y_axis is the y-axis of the root AnchorPane in the FXML page. 800px
     * <p>
     * The if condition ( x and y ) changes the position of the button placements.
     * So our scene is 1000x800px and the button size is 300x180 and we move it with that if condition
     * to put them on the screen.
     */

    public static Professor professor = new Professor();
    private final DBConnection db = new DBConnection();
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbl_welcome;

    int x_axis = 25, y_axis = 100;

    public void initialize() throws SQLException, IOException {
        lbl_welcome.setText("Welcome Professor " + professor.getName());
        db.get_professor_subjects(professor.getName(), professor.getPassword());

        for (String subject : professor.getSubjects()) {
            Button btn = new Button();
            btn.setText(subject);
            btn.setId(subject.trim());
            btn.setLayoutX(x_axis);
            btn.setLayoutY(y_axis);
            btn.setOnAction(e -> {
                try {
                    change_scene(btn);
                } catch (IOException ioException) {
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

    public void change_scene(Button e) throws IOException {
        Professor_Subject_Controller.subjectName = e.getText();
        Parent r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Subject.fxml")));
        Scene scene = new Scene(r);
        Stage stage = (Stage) e.getScene().getWindow();
        stage.setScene(scene);
    }
}

