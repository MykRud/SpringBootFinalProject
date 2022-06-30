package com.spring_final.SpringFinalProject.validator;


import com.spring_final.SpringFinalProject.model.TypeOfActivity;

import java.util.ArrayList;
import java.util.List;

public class TypeValidator extends CommonValidator{


    public static List<String> validateState(TypeOfActivity type) {

        setResourceBundle("en");

        List<String> validationErrors = new ArrayList<>();
        if (!validateForBlankValue(type.getName())) {
            validationErrors.add(resourceBundle.getString("common.error.empty_name"));
        }
        if(!validateForSize(2, 50, type.getName())){
            validationErrors.add(resourceBundle.getString("common.error.name_out_of_range"));
        }

        return validationErrors;
    }

}
