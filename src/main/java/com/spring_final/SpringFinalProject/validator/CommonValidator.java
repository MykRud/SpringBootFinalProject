package com.spring_final.SpringFinalProject.validator;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommonValidator {

    protected static ResourceBundle resourceBundle;

    protected static boolean validateForBlankValue(String value){
        return !value.isBlank();
    }

    protected static boolean validateForSize(int minSize, int maxSize, String value){
        return value.length() >= minSize && value.length() <= maxSize;
    }

    protected static void setResourceBundle(String lang){
        if(lang != null)
            resourceBundle = ResourceBundle.getBundle("messages", Locale.forLanguageTag(lang));
        else
            resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    }

}
