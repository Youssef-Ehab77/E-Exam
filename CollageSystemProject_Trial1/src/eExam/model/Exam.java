package eExam.model;

import java.sql.Time;
import java.util.HashMap;

public class Exam {

    private static final Exam e = new Exam();
    private String name;
    private String ID;
    private int numberOfQuestions;
    private Time examTime;
    private final HashMap<String, String> examData = new HashMap<String, String>();
    private int result;

    private Exam() {

    }

    public static Exam getInstance() {
        return e;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Time getExamTime() {
        return examTime;
    }

    public void setExamTime(Time examTime) {
        this.examTime = examTime;
    }

    public HashMap<String, String> getExamData() {
        return examData;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
