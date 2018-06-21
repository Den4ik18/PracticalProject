package sample;

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

public class Controller {
    @FXML
    ImageView image;


    @FXML
    private HBox box;

    @FXML
    private TextArea text_recognition_output;

    public void load_file(){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Load file");
        fileChooser.setInitialDirectory(new File("./"));

        File selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());

        TextHandler test;

        if(selectedFile != null){
            text_recognition_output.setText("");
            test = new TextHandler(selectedFile.getAbsoluteFile().toString());
            text_recognition_output.setText(test.printText());


            image.setImage(new Image(selectedFile.toURI().toString()));
        }else{
            System.out.println("dupa");
        }
    }

    public void save_text(){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("./"));
        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(box.getScene().getWindow());

        if(file != null){
            SaveFile(text_recognition_output.getText(), file);
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
}
