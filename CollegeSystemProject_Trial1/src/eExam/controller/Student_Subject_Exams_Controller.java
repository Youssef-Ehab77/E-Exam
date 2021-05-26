package eExam.controller;

import eExam.model.Exam;
import eExam.model.Student;
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

public class Student_Subject_Exams_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Student s = Student.getInstance();
    private int x_axis = 25, y_axis = 175;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbl_welcome;
    private static String current_subject = null;


    public void initialize() throws SQLException {
        lbl_welcome.setText("Exams for "+Multipurpose.subjectInUse.getSubjectName());
        if (Multipurpose.subjectInUse.getExams().isEmpty()) {
            Multipurpose.db.get_subject_exam_student(Multipurpose.subjectInUse.getID());
            current_subject = Multipurpose.subjectInUse.getSubjectName();
        } else if (!Multipurpose.subjectInUse.getExams().isEmpty() && !current_subject.equals(Multipurpose.subjectInUse.getSubjectName())) {
            Multipurpose.subjectInUse.getExams().clear();
            Multipurpose.db.get_subject_exam_student(Multipurpose.subjectInUse.getID());
            current_subject = Multipurpose.subjectInUse.getSubjectName();
        }

        for (Exam exam : Multipurpose.subjectInUse.getExams()) {
            Button btn = new Button();
            btn.setText(exam.getName());
            btn.setId(exam.getName().trim());
            btn.setLayoutX(x_axis);
            btn.setLayoutY(y_axis);
            btn.setOnAction(e -> {
                Multipurpose.examInUse = exam;
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
        Parent r = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Student_Exam.fxml")));
        Scene scene = new Scene(r);
        Stage stage = (Stage) e.getScene().getWindow();
        stage.setScene(scene);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Student_HomePage");
    }
}
