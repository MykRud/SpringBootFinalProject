package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.TypeOfActivity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.TypeOfActivityService;
import com.spring_final.SpringFinalProject.validator.ActivityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller @Slf4j
public class AddActivity {

    @Autowired
    TypeOfActivityService typeService;
    @Autowired
    ActivityService activityService;

    @Secured("ADMIN")
    @RequestMapping("/admin/activitiesAdd")
    public ModelAndView addActivityPost(@ModelAttribute("activity") Activity activity){
        ModelAndView mv = new ModelAndView();
        mv.addObject("types", typeService.getTypes());
        if(activity.getName() == null){
            mv.setViewName("WEB-INF/pages/admin/add-activity");
            return mv;
        }
        TypeOfActivity type = typeService.getType(activity.getType().getName());
        if(type != null)
            activity.setType(type);
        activity.setStatus("Pending");

        List<String> errors = ActivityValidator.validateState(activity);
        if(!errors.isEmpty()){
            mv.addObject("errors", errors);
            mv.setViewName("WEB-INF/pages/admin/add-activity");
            return mv;
        }
        log.info("Adding activity {}...", activity.getName());
        activityService.addActivity(activity);
        mv.setViewName("redirect:/activities");
        return mv;
    }
}
