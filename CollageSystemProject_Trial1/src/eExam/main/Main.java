package eExam.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("..//view/WelcomePage.fxml")));
        primaryStage.setTitle("E-Exam Application");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("..//view/Images/eexam.png"))));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
