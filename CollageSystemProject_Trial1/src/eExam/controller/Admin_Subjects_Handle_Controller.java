package eExam.controller;

import eExam.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class Admin_Subjects_Handle_Controller {
    private final Multipurpose m = Multipurpose.getInstance();
    public static ObservableList<Subject> subjectOL = FXCollections.observableArrayList();
    public static ObservableList<Object> departmentOL = FXCollections.observableArrayList();
    public static ObservableList<Object> levelOL = FXCollections.observableArrayList();
    private static boolean addedSubject = false;
    private static String currentDepartmentSelected = "";

    @FXML
    private TextField tf_subject_name;
    @FXML
    private ChoiceBox<Object> cb_department;
    @FXML
    private ChoiceBox<Object> cb_level;
    @FXML
    private Button btn_add_subject;
    @FXML
    private TableView<Subject> table_subject;
    @FXML
    private TableColumn<Subject, Integer> col_subject_id;
    @FXML
    private TableColumn<Subject, String> col_subject_name;
    @FXML
    private TableColumn<Subject, Integer> col_department_id;
    @FXML
    private TableColumn<Subject, Integer> col_level_id;


    public void initialize() throws SQLException {
        update_table();
        update_department_choice_box();
    }

    public void update_table() throws SQLException {
        col_subject_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_subject_name.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        col_level_id.setCellValueFactory(new PropertyValueFactory<>("levelID"));
        col_department_id.setCellValueFactory(new PropertyValueFactory<>("departmentID"));

        if (subjectOL.isEmpty()) {
            Multipurpose.db.get_all_subjects();
        } else if (addedSubject) {
            subjectOL.clear();
            addedSubject = false;
            Multipurpose.db.get_all_subjects();
        }
        table_subject.setItems(subjectOL);
    }

    public void update_department_choice_box() throws SQLException {
        if (departmentOL.isEmpty()) {
            Multipurpose.db.get_all_department_name();
            cb_department.setItems(departmentOL);
            if (!departmentOL.isEmpty()) {
                cb_department.setValue(departmentOL.get(0));
            }
        }
    }

    @FXML
    public void get_selected_department_levels(ActionEvent event) throws SQLException {
        if (!currentDepartmentSelected.equals(cb_department.getValue().toString())) {
            currentDepartmentSelected = cb_department.getValue().toString();
            cb_level.getItems().clear();
            Multipurpose.db.get_all_department_levels_names(currentDepartmentSelected);
            cb_level.setItems(levelOL);
            if (!levelOL.isEmpty()) {
                cb_level.setValue(levelOL.get(0));
            }
        }
    }

    @FXML
    public void add_subject(ActionEvent event) throws SQLException {
        if (tf_subject_name.getText() != null && cb_level.getValue() != null && cb_department.getValue() != null) {
            Multipurpose.db.add_new_subject(tf_subject_name.getText(), cb_department.getValue().toString(), cb_level.getValue().toString());
            tf_subject_name.clear();
            cb_department.getItems().clear();
            cb_level.getItems().clear();
            currentDepartmentSelected = "";
            addedSubject = true;
            update_table();
            m.displayMessage("Subject Added!", "Subject Added Successfully!", null);
        }
    }


    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Admin_HomePage");
    }


    public void clear(MouseEvent event) {
        cb_level.getItems().clear();
    }
}
