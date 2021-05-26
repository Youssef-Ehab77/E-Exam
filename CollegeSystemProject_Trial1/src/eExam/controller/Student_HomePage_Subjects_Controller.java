package eExam.controller;

import eExam.model.Student;
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

public class Student_HomePage_Subjects_Controller {

    private final Student s = Student.getInstance();
    private final Multipurpose m = Multipurpose.getInstance();
    private int x_axis = 25, y_axis = 100;

    @FXML
    private Label lbl_welcome;
    @FXML
    private AnchorPane root;


    public void initialize() throws SQLException {
        lbl_welcome.setText("Welcome " + s.getName());

        if (s.getSubjects().isEmpty()) {
            Multipurpose.db.get_student_subject(s.getDepartmentID(),s.getLevelID());
        }
        for (Subject subject : s.getSubjects()) {
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
        Parent r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Student_Subject_Exams.fxml")));
        Scene scene = new Scene(r);
        Stage stage = (Stage) e.getScene().getWindow();
        stage.setScene(scene);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Student_HomePage");
    }
}

