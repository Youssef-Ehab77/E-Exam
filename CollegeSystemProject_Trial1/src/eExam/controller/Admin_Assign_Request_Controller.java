package eExam.controller;

import eExam.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class Admin_Assign_Request_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    public static ObservableList<Object> professorsOL = FXCollections.observableArrayList();
    public static ObservableList<Object> subjectsOL = FXCollections.observableArrayList();
    public static ObservableList<Object> requestsOL = FXCollections.observableArrayList();
    private static String currentProfessor = "";
    private static boolean isUpdated = false;
    @FXML
    private ChoiceBox<Object> cb_professors;
    @FXML
    private ChoiceBox<Object> cb_subjects;
    @FXML
    private Button btn_assign;
    @FXML
    private ChoiceBox<Object> cb_requests;
    @FXML
    private Button btn_decline;
    @FXML
    private Button btn_approve;


    public void initialize() throws SQLException {
        update_assign_choice_boxes();
        update_request_choice_box();
    }

    private void update_assign_choice_boxes() throws SQLException {
        if (professorsOL.isEmpty() || isUpdated) {
            if (isUpdated) {
                isUpdated = false;
                professorsOL.clear();
                subjectsOL.clear();
            }
            Multipurpose.db.get_all_professors();
            cb_professors.setItems(professorsOL);
            if (!professorsOL.isEmpty()) {
                cb_professors.setValue(professorsOL.get(0));
            }
        }
    }

    private void update_request_choice_box() throws SQLException {
        Multipurpose.db.get_professors_request();
        cb_requests.setItems(requestsOL);
    }

    public void show_professor_subjects() throws SQLException {
        if (!currentProfessor.equals(cb_professors.getValue().toString())) {
            currentProfessor = cb_professors.getValue().toString();
            subjectsOL.clear();
            Multipurpose.db.get_selected_professor_subjects(cb_professors.getSelectionModel().getSelectedItem().toString());
            cb_subjects.setItems(subjectsOL);
            if (!subjectsOL.isEmpty()) {
                cb_subjects.setValue(subjectsOL.get(0));
            }
        }
    }

    public void assign_subject_to_professor() throws SQLException {
        Multipurpose.db.assign_subject_to_professor(cb_professors.getValue().toString(), cb_subjects.getValue().toString());
        isUpdated = true;
        update_assign_choice_boxes();
        m.displayMessage("Done", "Subject Assigned Successfully!", null);
    }

    public void approve_request() throws SQLException {
        Multipurpose.db.approve_professor_request(cb_requests.getValue().toString());
        requestsOL.clear();
        cb_requests.getItems().clear();
        update_request_choice_box();
        m.displayMessage("Done","User Approved!",null);
    }

    public void decline_request() throws SQLException {
        Multipurpose.db.decline_professor_request(cb_requests.getValue().toString());
        requestsOL.clear();
        cb_requests.getItems().clear();
        update_request_choice_box();
        m.displayMessage("Done","User Deleted!",null);
    }

    public void clear_subject_list(MouseEvent event) {
        cb_subjects.getItems().clear();
        currentProfessor = "";
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        professorsOL.clear();
        subjectsOL.clear();
        requestsOL.clear();
        m.navigation_handler(e, "Admin_HomePage");
    }


}
