package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RejectRequest {

    @Autowired
    ActivityRequestService service;

    @RequestMapping("/admin/activityRequestReject")
    public String rejectRequest(@RequestParam("id") int id){
        ActivityRequest request = service.getRequest(id);

        if(!request.getStatus().equals("Pending")){
            return "redirect:/admin/activitiesRequests";
        }

        service.rejectRequest(request);

        return "redirect:/admin/activitiesRequests";
    }

}
