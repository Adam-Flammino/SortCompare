/**
 * @Course: SDEV 350 ~ Java Programming II
 * @Author Name: Adam Flammino
 * @Assignment Name: aflammino_week10
 * @Date: Apr 07, 2017
 * @Subclass Validation Description:  Checks for empty strings and fields
 */

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class Validation {

    /**
     * Checks for an empty string
     * @param s
     * @return true if empty, false if not
     */
    public boolean emptyString(String s) {
        return s.isEmpty();
    }

    /**
     * Checks for empty arrayList
     * @param a
     * @return true if empty, false if not
     */
    public boolean emptyArrayList(ArrayList a){
        return a.isEmpty();
    }

    /**
     * Checks for empty textArea
     * @param t
     * @return true if empty, false if not
     */
    public boolean emptyTextArea(TextArea t){
        return t.getText().trim().equals("");
    }

    /**
     * Checks for empty textField
     * @param t
     * @return true if empty, false if not
     */
    public  boolean emptyTextField(TextField t){
        return t.getText().trim().equals("");
    }
}
