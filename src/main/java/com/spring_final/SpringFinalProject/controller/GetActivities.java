package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetActivities {

    @Autowired
    ActivityService service;

    @RequestMapping("/activities")
    public ModelAndView getActivities(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "5") int size,
                                      @RequestParam(value = "sort", defaultValue = "by-name") String sort){
        ModelAndView mv = new ModelAndView();

        int numberOfActivities = service.getNumberOfActivities();
        int totalPages = (int) Math.ceil((double) numberOfActivities /
                (double) size);
        List<Activity> activities = null;

        if(sort.equals("by-name"))
            activities = service.getActivitiesInLimitByName(size, page);
        else if(sort.equals("by-category"))
            activities = service.getActivitiesInLimitByType(size, page);
        else if(sort.equals("by-users"))
            activities = service.getActivitiesInLimitByNumberOfUsers(size, page);

        //activities = service.getActivities();

        mv.addObject("activities", activities);
        mv.addObject("currentPage", page);
        mv.addObject("pageSize", size);
        mv.addObject("totalPages", totalPages);
        mv.addObject("sort", sort);
        mv.setViewName("WEB-INF/pages/activities");
        return mv;
    }

}
