package aflammino_week10;

/** 
 * @Course: SDEV 250 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Assignment Name: aflammino_week10
 * @Date: Apr 19, 2017
 * @Subclass Validation Description:  Checks for empty strings and fields
 */
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Created by Flammino on 4/07/2017.
 */
public class Validation {
    public boolean emptyString(String s) {
        return s.isEmpty();
    }

    public boolean emptyArrayList(ArrayList a){
        return a.isEmpty();
    }

    public boolean emptyTextArea(TextArea t){
        return t.getText().trim().equals("");
    }
    public  boolean emptyTextField(TextField t){
        return t.getText().trim().equals("");
    }
}
