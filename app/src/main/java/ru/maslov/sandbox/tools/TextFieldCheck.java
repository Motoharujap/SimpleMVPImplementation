package ru.maslov.sandbox.tools;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Администратор on 09.09.2016.
 */
public class TextFieldCheck {
    //do not pass view other than TextView or EditText
    public static boolean isTextFieldEmpty(View textField){
        if (textField instanceof TextView) {
            return ((TextView)textField).getText().toString().trim().equals("");
        } else if (textField instanceof EditText){
            return ((EditText)textField).getText().toString().trim().equals("");
        }
        throw new RuntimeException("You can use this method only with EditText or TextView");
    }
}
