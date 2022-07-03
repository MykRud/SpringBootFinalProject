package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteActivity {

    @Autowired
    ActivityService service;

    @Secured("ADMIN")
    @RequestMapping("/admin/activityDelete")
    public String deleteActivity(@RequestParam("activity_id") int id){
        service.deleteActivity(id);
        return "redirect:/activities";
    }


}
