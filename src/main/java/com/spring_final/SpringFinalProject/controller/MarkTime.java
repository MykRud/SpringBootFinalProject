package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Activity;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.ActivityService;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.TimeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
public class MarkTime {

    @Autowired
    ActivityService service;
    @Autowired
    UserService userService;

    @RequestMapping("/markTime")
    public ModelAndView markTime(@RequestParam("activity_id") int activityId,
                                 @RequestParam("days") int days,
                                 @RequestParam("hours") int hours,
                                 @RequestParam("minutes") int minutes,
                                 HttpSession session){

        ModelAndView mv = new ModelAndView();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        Activity activity = service.getActivity(activityId);

        boolean canMatkTime = false;
        for(User us : activity.getUsers()){
            if(us.getUsername().equals(user.getUsername())){
                canMatkTime = true;
                break;
            }
        }

        if(!canMatkTime) {
            mv.setViewName("/WEB-INF/pages/activities");
            return mv;
        }

        int duration = minutes + hours * 60 + days * 24 * 60;

        Locale locale = Locale.getDefault();

        List<String> errors = TimeValidator.validateState(String.valueOf(days),
                String.valueOf(hours), String.valueOf(minutes));

        if(!errors.isEmpty()){
            mv.addObject("errors", errors);
            mv.setViewName("/WEB-INF/pages/activities");
            return mv;
        }

        service.takeActivityTime(activityId, user, duration);

        mv.setViewName("redirect:/activities");
        return mv;
    }

}
