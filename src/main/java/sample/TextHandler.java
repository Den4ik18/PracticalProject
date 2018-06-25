package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;

public class TextHandler {

    private final tesseract.TessBaseAPI instance;

    private final lept.PIX image;

    private BytePointer bytePointer;

    private final String output;

    public TextHandler(String input) {
        instance = new tesseract.TessBaseAPI();

        initTessDataTrainingPath("src/main/resources","eng");//English


        image = lept.pixRead(input);

        instance.SetImage(image);
        bytePointer = instance.GetUTF8Text();

        output = bytePointer.getString();

    }

    private void initTessDataTrainingPath(String path, String language){
        instance.Init(path, language);
    }

    public String printText(){
        return output;
    }
}
