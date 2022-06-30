package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetActivity {

    @Autowired
    ActivityService service;

   @RequestMapping("/getActivityById")
    public ModelAndView getActivity(@RequestParam("id") int id){
        ModelAndView mv = new ModelAndView();
        Activity activity = service.getActivity(id);
        mv.addObject("activity", activity);
        mv.setViewName("activityPage");
        return mv;
    }

    @RequestMapping("/getActivityByName")
    public ModelAndView getActivity(@RequestParam("name") String name){
        ModelAndView mv = new ModelAndView();
        Activity activity = service.getActivity(name);
        mv.addObject("activity", activity);
        mv.setViewName("/WEB-INF/pages/activities");
        return mv;
    }

}
