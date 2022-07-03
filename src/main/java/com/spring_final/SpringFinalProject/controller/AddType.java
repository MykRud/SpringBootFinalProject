package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import com.spring_final.SpringFinalProject.validator.TypeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller @Slf4j
public class AddType {

    @Autowired
    TypeOfActivityService service;

    @Secured("ADMIN")
    @RequestMapping("/admin/typesAdd")
    public ModelAndView addType(@ModelAttribute("type") TypeOfActivity type){
        ModelAndView mv = new ModelAndView();
        if(type.getName() == null) {
            mv.setViewName("WEB-INF/pages/admin/add-type");
            return mv;
        }
        if(service.findType(type.getName()) != null) {
            mv.setViewName("WEB-INF/pages/admin/add-type");
            return mv;
        }

        List<String> errors = TypeValidator.validateState(type);

        log.info("Adding type {}...", type.getName());
        service.addType(type);

        if(!errors.isEmpty()){
            mv.addObject("errors", errors);
            mv.setViewName("/WEB-INF/pages/admin/add-type");
            return mv;
        }

        mv.setViewName("redirect:/activities");
        return mv;
    }

}
