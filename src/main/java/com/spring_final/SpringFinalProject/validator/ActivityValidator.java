package com.spring_final.SpringFinalProject.validator;


import com.spring_final.SpringFinalProject.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityValidator extends CommonValidator {

    public static List<String> validateState(Activity activity){

        setResourceBundle("en");

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(activity.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if(!validateForSize(2, 50, activity.getName())){
            validationErrors.add(resourceBundle.getString("activity.error.name_out_of_range"));
        }

        if(!validateForSize(0, 2500, activity.getDescription())){
            validationErrors.add(resourceBundle.getString("activity.error.description_out_of_range"));
        }

        return validationErrors;
    }

}
