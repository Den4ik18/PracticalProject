package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
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
    private Button getText;
    Image saveIcon = new Image("file:icon/world.png");
    ImageView saveView = new ImageView(saveIcon);

    ImageView imageViewUkr = new ImageView(new Image("file:icon/ukr.png"));
    ImageView imageViewEN = new ImageView(new Image("file:icon/eng.png"));
    ImageView imageViewRus = new ImageView(new Image("file:icon/rus.png"));

    ImageView imageViewUkr1 = new ImageView(new Image("file:icon/ukr.png"));
    ImageView imageViewEN1 = new ImageView(new Image("file:icon/eng.png"));
    ImageView imageViewRus1 = new ImageView(new Image("file:icon/rus.png"));

    MenuItem menuItemUkr = new MenuItem("Ukraine", imageViewUkr);
    MenuItem menuItemEN = new MenuItem("English", imageViewEN);
    MenuItem menuItemRus = new MenuItem("Russia", imageViewRus);
    @FXML
    private MenuButton language;
    private String languageName="eng";


    @FXML
    public void initialize() {

        getText.setVisible(false);
        language.setGraphic(saveView);
        language.getItems().add(menuItemUkr);
        language.getItems().add(menuItemEN);
        language.getItems().add(menuItemRus);

    }
    @FXML
   void selctLanguage (){

        menuItemUkr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                language.setGraphic(imageViewUkr1);
                languageName="ukr";

                language.setText("Ukraine");
            }
        });

        menuItemEN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                language.setGraphic(imageViewEN1);
                languageName="eng";

                language.setText("English");
            }
        });

        menuItemRus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                language.setGraphic(imageViewRus1);
                languageName="rus";

                language.setText("Russia");
            }

        });



    }
    @FXML
    void save_text(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File("./text/"));
        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
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
    FileChooser fileChooser = new FileChooser();
    File selectedFile;
    @FXML
    void load_file(ActionEvent event) {


        fileChooser.setTitle("Load file");
        fileChooser.setInitialDirectory(new File("./images/"));

        selectedFile = fileChooser.showOpenDialog(box.getScene().getWindow());
        TextHandler test;

        if(selectedFile != null){

            image.setImage(new Image(selectedFile.toURI().toString()));
            getText.setVisible(true);
        }else{
            System.out.println("Image not loaded!");
            getText.setVisible(false);

        }
    }
    @FXML
    void get_text (){


        TextHandler test;
        text.setText("");
        test = new TextHandler(selectedFile.getAbsoluteFile().toString(),languageName);
        text.setText(test.printText());
    }


}

