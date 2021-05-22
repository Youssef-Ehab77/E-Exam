package eExam.controller;

import eExam.model.Department;
import eExam.model.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class Admin_Departments_And_Levels_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    public static ObservableList<Department> departmentOL = FXCollections.observableArrayList();
    public static ObservableList<Level> levelOL = FXCollections.observableArrayList();

    boolean addedDepartment = false;
    boolean addedLevel = false;

    @FXML
    private TableView<Department> table_departments;
    @FXML
    private TableColumn<Department, Integer> col_department_id_departments;
    @FXML
    private TableColumn<Department, String> col_department_name;

    @FXML
    private TableView<Level> table_levels;
    @FXML
    private TableColumn<Level, Integer> col_level_id;
    @FXML
    private TableColumn<Level, Integer> col_department_id_levels;
    @FXML
    private TableColumn<Level, String> col_level_name;

    @FXML
    private TextField tf_new_department_name;
    @FXML
    private Button btn_add_department;
    @FXML
    private TextField tf_new_level_name;
    @FXML
    private TextField tf_level_department_id;
    @FXML
    private Button btn_add_level;

    public void initialize() throws SQLException {
        update_department_table();
        update_level_table();
    }

    public void update_department_table() throws SQLException {
        col_department_id_departments.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_department_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        if (departmentOL.isEmpty()) {
            Multipurpose.db.get_departments();
        } else if (addedDepartment) {
            addedDepartment = false;
            departmentOL.clear();
            Multipurpose.db.get_departments();
        }
        table_departments.setItems(departmentOL);
    }

    public void update_level_table() throws SQLException {
        col_level_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_department_id_levels.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
        col_level_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        if (levelOL.isEmpty()) {
            Multipurpose.db.get_levels();
        } else if (addedLevel) {
            addedLevel = false;
            levelOL.clear();
            Multipurpose.db.get_levels();
        }
        table_levels.setItems(levelOL);
    }

    @FXML
    void add_department(ActionEvent event) throws SQLException {
        if (tf_new_department_name.getText() != null) {
            Multipurpose.db.add_new_department(tf_new_department_name.getText());
        }
        addedDepartment = true;
        m.displayMessage("Added", "Department Added Successfully!", null);
        update_department_table();
    }

    @FXML
    void add_level(ActionEvent event) throws SQLException {
        if (tf_level_department_id != null && tf_new_level_name != null) {
            Multipurpose.db.add_new_level(Integer.parseInt(tf_level_department_id.getText()), tf_new_level_name.getText());
        }
        addedLevel = true;
        m.displayMessage("Added Level", "Level Added Successfully!", null);
        update_level_table();
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Admin_HomePage");
    }
}
