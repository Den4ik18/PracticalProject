package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;

public class Controller {

    @FXML
    private HBox box;

    @FXML
    private ImageView image;


    @FXML
    private Button loadFile;

    @FXML
    private TextArea text;

    @FXML
    private Button saveText;



    @FXML
    void save_text(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("./text/"));

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);


        File file = fileChooser.showSaveDialog(box.getScene().getWindow());

        if(file != null){
            SaveFile(text.getText(), file);
        }
    }
    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void load_file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Load file");
        fileChooser.setInitialDirectory(new File("./images/"));

        File selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());
        TextHandler test;

        if(selectedFile != null){
            text.setText("");
            test = new TextHandler(selectedFile.getAbsoluteFile().toString());
            text.setText(test.printText());
            image.setImage(new Image(selectedFile.toURI().toString()));
        }else{
            System.out.println("Image not loaded!");
        }
    }
}

