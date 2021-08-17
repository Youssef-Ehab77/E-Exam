package eExam.controller;


import eExam.model.Professor;
import eExam.model.Questions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class Professor_Add_Questions_To_Exam_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Professor p = Professor.getInstance();
    public static ObservableList<Questions> observableList = FXCollections.observableArrayList();
    boolean examQuestionIsUpdated = false;
    private static String currentExamSelected = null;
    private int index = -1;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbl_welcome;
    @FXML
    private RadioButton radio_mcq;
    @FXML
    private ToggleGroup question_type;
    @FXML
    private RadioButton radio_true_or_false;
    @FXML
    private TextField tf_id;
    @FXML
    private TextArea tf_question;
    @FXML
    private TextField tf_option1;
    @FXML
    private TextField tf_option2;
    @FXML
    private TextField tf_option3;
    @FXML
    private TextField tf_option4;
    @FXML
    private TextField tf_correct_answer;
    @FXML
    private TextField tf_question_grade;
    @FXML
    private TableView<Questions> table_view;
    @FXML
    private TableColumn<Questions, Integer> col_id;
    @FXML
    private TableColumn<Questions, String> col_type;
    @FXML
    private TableColumn<Questions, String> col_question;
    @FXML
    private TableColumn<Questions, String> col_option1;
    @FXML
    private TableColumn<Questions, String> col_option2;
    @FXML
    private TableColumn<Questions, String> col_option3;
    @FXML
    private TableColumn<Questions, String> col_option4;
    @FXML
    private TableColumn<Questions, String> col_correct_answer;
    @FXML
    private TableColumn<Questions, Integer> col_question_grade;

    public void initialize() throws SQLException {
        lbl_welcome.setText("Exam " + Multipurpose.examInUse.getName() + " for " + Multipurpose.subjectInUse.getSubjectName() + " subject");
        update_questions();
    }

    public void update_questions() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_option1.setCellValueFactory(new PropertyValueFactory<>("option_1"));
        col_option2.setCellValueFactory(new PropertyValueFactory<>("option_2"));
        col_option3.setCellValueFactory(new PropertyValueFactory<>("option_3"));
        col_option4.setCellValueFactory(new PropertyValueFactory<>("option_4"));
        col_correct_answer.setCellValueFactory(new PropertyValueFactory<>("correct_answer"));
        col_question_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        if (observableList.isEmpty()) {
            Multipurpose.db.get_questions_in_exam(Multipurpose.examInUse.getID());
            currentExamSelected = Multipurpose.examInUse.getName();
        }  else if (!currentExamSelected.equals(Multipurpose.examInUse)) {
            observableList.clear();
            Multipurpose.db.get_questions_in_exam(Multipurpose.examInUse.getID());
            currentExamSelected = Multipurpose.examInUse.getName();
        }else if (examQuestionIsUpdated) {
            examQuestionIsUpdated = false;
            observableList.clear();
            Multipurpose.db.get_questions_in_exam(Multipurpose.examInUse.getID());
        }
        table_view.setItems(observableList);
    }

    public void selected_question(MouseEvent event) {
        index = table_view.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
        tf_id.setText(col_id.getCellData(index).toString());
        tf_question.setText(col_question.getCellData(index));
        tf_option1.setText(col_option1.getCellData(index));
        tf_option2.setText(col_option2.getCellData(index));
        tf_option3.setText(col_option3.getCellData(index));
        tf_option4.setText(col_option4.getCellData(index));
        tf_correct_answer.setText(col_correct_answer.getCellData(index));
        tf_question_grade.setText(col_question_grade.getCellData(index).toString());
        String type = col_type.getCellData(index);
        if (type != null) {
            if (type.equalsIgnoreCase("True/False")) {
                radio_true_or_false.setSelected(true);
            } else radio_mcq.setSelected(true);
        }

    }

    public void update_question_data(ActionEvent e) throws SQLException {
        examQuestionIsUpdated = true;
        Multipurpose.db.update_question_data(Integer.parseInt(tf_id.getText()), Integer.parseInt(tf_question_grade.getText())
                , ((RadioButton) question_type.getSelectedToggle()).getText(), tf_question.getText()
                , tf_option1.getText(), tf_option2.getText(), tf_option3.getText(), tf_option4.getText(), tf_correct_answer.getText());
        update_questions();
        m.displayMessage("Done!", "Value Updated Successfully!", "");
    }

    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Professor_Subject_Exam");
    }

}
