package eExam.controller;

import eExam.model.Questions;
import eExam.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Student_Exam_Controller {

    private final Multipurpose m = Multipurpose.getInstance();
    private final Student s = Student.getInstance();
    private final ArrayList<Questions> q = Multipurpose.examInUse.getQuestions();
    private HashMap<Integer, String> answers = new HashMap<>();
    private int index = 0;

    @FXML
    private Label lbl_question;
    @FXML
    private ToggleGroup options;
    @FXML
    private RadioButton rb_option1;
    @FXML
    private RadioButton rb_option3;
    @FXML
    private RadioButton rb_option2;
    @FXML
    private RadioButton rb_option4;
    @FXML
    private Label lbl_welcome;


    public void initialize() throws SQLException {
        lbl_welcome.setText(Multipurpose.examInUse.getName() + " Exam");
        Multipurpose.db.get_questions_in_exam(Multipurpose.examInUse.getID());

        for (Questions q : Multipurpose.examInUse.getQuestions()) {
            answers.put(q.getId(), null);
        }

        put_question();
    }

    public void put_question() {
        lbl_question.setText(q.get(index).getQuestion());
        rb_option1.setText(q.get(index).getOption_1());
        rb_option2.setText(q.get(index).getOption_2());
        rb_option3.setText(q.get(index).getOption_3());
        rb_option4.setText(q.get(index).getOption_4());
    }

    @FXML
    void save_value() {
        answers.put(q.get(index).getId(), ((RadioButton) options.getSelectedToggle()).getText());
    }

    @FXML
    void next_question(ActionEvent event) {
        if (index < Multipurpose.examInUse.getNumberOfQuestions() - 1) {
            index++;
            put_question();
            answered_check();
        } else m.displayMessage("Last Question", "There is no more questions!", "Please submit if you finished");
    }

    @FXML
    void previous_question(ActionEvent event) {
        if (index > 0) {
            index--;
            put_question();
            answered_check();
        } else m.displayMessage("First Question", "This is the first question!", null);
    }

    public void answered_check() {
        String value = answers.get(q.get(index).getId());
        if (value != null) {
            if (rb_option1.getText().equals(value)) {
                rb_option1.setSelected(true);
            } else if (rb_option2.getText().equals(value)) {
                rb_option2.setSelected(true);
            } else if (rb_option3.getText().equals(value)) {
                rb_option3.setSelected(true);
            } else if (rb_option4.getText().equals(value)) {
                rb_option4.setSelected(true);
            }
        } else options.selectToggle(null);
    }

    @FXML
    void submit() throws SQLException {
        for (Integer name : answers.keySet()) {
            int key = name;
            String value = answers.get(name);
            System.out.println(key + " " + value);
        }
        int result = Multipurpose.db.submit_exam(Multipurpose.examInUse.getID(), s.getID(), answers);
        m.displayMessage("Exam is Over!", "Your Certified Grade is " + result + "/" + Multipurpose.examInUse.getGrade(), null);
    }


    public void navigation_handler(ActionEvent e) throws IOException {
        m.navigation_handler(e, "Student_Subject_Exams");
    }
}
