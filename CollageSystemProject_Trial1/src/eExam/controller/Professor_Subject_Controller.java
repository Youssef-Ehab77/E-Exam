package eExam.controller;

import eExam.model.Professor;
import eExam.model.Student_Subjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;


/**
 * In this class we are going to make 4 main things:
 * 1- Show students name,id and grades for the subject that professor clicked in his home page.
 * 2- He will be able to edit and change his students grades from the table in easy way.
 * 3- He can make go for a new page to make a new exam for the currently selected subject.
 * 4- He can show the exams finished or coming.
 * <p>
 * The General idea is to connect the table with the class Student_Subject and to make an
 * ObservableList (A list that enables listeners to track changes when they occur) to carry the required data
 * from the database.
 *
 * @Method initialize
 * we check that if the ObservableList is empty or not, if it's empty we will go get the data from the database,
 * if it's not empty we will check if the data in the ObservableList is for the current subject or not, if it's
 * for the current subject then we will just show it in the table, else we will clear the ObservableList and get
 * the new data for the new subject from the database.
 * @Method selected_student
 * when the user click on a specific user in the table we send the mouse event with the index of the table (row) for
 * the method, then we fill the text fields with the data from this row so the user will be able to edit/change his data.
 * @Method update_student_grade
 * Here when the user click update student button we take the values form the text fields and update it in the database
 */

public class Professor_Subject_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    public static ObservableList<Student_Subjects> ol = FXCollections.observableArrayList();

    private int index = -1;
    boolean studentGradeIsUpdated = false;
    private static String currentSubjectSelected = null;


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
    @FXML
    private Button btn_make_an_exam;
    @FXML
    private Button btn_show_exams;


    public void initialize() throws SQLException {
        lbl_subject_name.setText(Multipurpose.subjectInUse.getSubjectName());
        update_table();
    }

    public void update_table() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_7th.setCellValueFactory(new PropertyValueFactory<>("grade_7th"));
        col_12th.setCellValueFactory(new PropertyValueFactory<>("grade_12th"));
        col_final.setCellValueFactory(new PropertyValueFactory<>("grade_final"));

        if (ol.isEmpty()) {
            Multipurpose.db.get_students_in_subject(p.getID(), Multipurpose.subjectInUse.getSubjectName());
            currentSubjectSelected = Multipurpose.subjectInUse.getSubjectName();
        } else if (studentGradeIsUpdated && currentSubjectSelected.equals(Multipurpose.subjectInUse)) {
            studentGradeIsUpdated = false;
            ol.clear();
            Multipurpose.db.get_students_in_subject(p.getID(), Multipurpose.subjectInUse.getSubjectName());
        } else if (!currentSubjectSelected.equals(Multipurpose.subjectInUse)) {
            ol.clear();
            Multipurpose.db.get_students_in_subject(p.getID(), Multipurpose.subjectInUse.getSubjectName());
            currentSubjectSelected = Multipurpose.subjectInUse.getSubjectName();
        }
        table.setItems(ol);
    }

    public void selected_student(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        tf_id.setText(col_id.getCellData(index).toString());
        tf_name.setText(col_name.getCellData(index));
        tf_7th.setText(col_7th.getCellData(index).toString());
        tf_12th.setText(col_12th.getCellData(index).toString());
        tf_final.setText(col_final.getCellData(index).toString());
    }

    public void update_student_grades(ActionEvent e) throws SQLException {
        studentGradeIsUpdated = true;
        Multipurpose.db.update_student_grade(tf_id.getText(), Multipurpose.subjectInUse.getSubjectName(), tf_7th.getText(), tf_12th.getText(), tf_final.getText());
        update_table();
        m.displayMessage("Done Updating!", "Value Updated Successfully!", "");
    }

    public void make_an_exam(ActionEvent e) throws IOException {
        m.change_scene(e, "Professor_Make_An_Exam");
    }

    @FXML
    void edit_exam(ActionEvent e) throws IOException {
        m.change_scene(e, "Professor_Subject_Exam");
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        ol.clear();
        m.navigation_handler(e, "Professor_HomePage");
    }
}
