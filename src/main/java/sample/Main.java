package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Project");
        primaryStage.setScene(new Scene(root, 693, 355));
        primaryStage.getIcons().add(new Image("icon/main.png"));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
