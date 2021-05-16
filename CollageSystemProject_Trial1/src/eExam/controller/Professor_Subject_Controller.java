package eExam.controller;

import eExam.model.DBConnection;
import eExam.model.Student_Subjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class Professor_Subject_Controller {
    @FXML
    private Label lbl_subject_name;
    @FXML
    private TableView<Student_Subjects> table;
    @FXML
    private TableColumn<Student_Subjects, Integer> col_id;
    @FXML
    private TableColumn<Student_Subjects, String> col_name;
    @FXML
    private TableColumn<Student_Subjects, Integer> col_7th;
    @FXML
    private TableColumn<Student_Subjects, Integer> col_12th;
    @FXML
    private TableColumn<Student_Subjects, Integer> col_final;
    @FXML
    private Button btn_make_an_exam;
    @FXML
    private Button btn_show_exams;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_7th;
    @FXML
    private TextField tf_12th;
    @FXML
    private TextField tf_final;
    @FXML
    private Button btn_update_student;
    private int index = -1;

    public static String subjectName;
    DBConnection db = DBConnection.getInstance();
    public static ObservableList<Student_Subjects> ol = FXCollections.observableArrayList();

    public void initialize() throws SQLException {
        lbl_subject_name.setText(subjectName);
        col_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_7th.setCellValueFactory(new PropertyValueFactory<>("grade_7th"));
        col_12th.setCellValueFactory(new PropertyValueFactory<>("grade_12th"));
        col_final.setCellValueFactory(new PropertyValueFactory<>("grade_final"));
        if (ol.isEmpty()) {
            db.get_students_in_subject(Professor_HomePage_Controller.professor.getID(), subjectName);
        }
        table.setItems(ol);
    }

    public void selected_student(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tf_id.setText(col_id.getCellData(index).toString());
        tf_name.setText(col_name.getCellData(index).toString());
        tf_7th.setText(col_7th.getCellData(index).toString());
        tf_12th.setText(col_12th.getCellData(index).toString());
        tf_final.setText(col_final.getCellData(index).toString());
    }

    public void update_student_grades(ActionEvent e) throws SQLException, IOException {
        db.update_student_grade(tf_id.getText(), subjectName, tf_7th.getText(), tf_12th.getText(), tf_final.getText());
        displayMessage();
    }

    public void make_an_exam(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Make_An_Exam.fxml")));
        assert root != null;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        String clicked = ((Button) e.getSource()).getText();
        Parent root;

        if (clicked.equals("Home")) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_HomePage.fxml")));
            ol.clear();
        } else if (clicked.equals("Logout")) {
            ol.clear();
            Professor_HomePage_Controller.professor.logout();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_Login.fxml")));
        } else {
            ol.clear();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/Professor_HomePage.fxml")));
        }

        assert root != null;
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void displayMessage() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done Updating!");
        alert.setHeaderText("Value Updated Successfully!");
        alert.setContentText("Note: You Will See The Update If you Refresh The Page!");
        alert.showAndWait();
    }
}
