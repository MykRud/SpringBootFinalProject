package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.ActivityRequest;
import com.spring_final.SpringFinalProject.service.ActivityRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetRequests {

    @Autowired
    ActivityRequestService requestService;

    @RequestMapping("/admin/activitiesRequests")
    public ModelAndView getActivities(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "5") int size){
        ModelAndView mv = new ModelAndView();

        int numberOfRequests = requestService.getNumberOfRequests();
        int totalPages = (int) Math.ceil((double) numberOfRequests /
                (double) size);
        List<ActivityRequest> requests = requestService.getRequestsInLimit(page, size);

        mv.addObject("requests", requests);
        mv.addObject("currentPage", page);
        mv.addObject("pageSize", size);
        mv.addObject("totalPages", totalPages);
        mv.setViewName("WEB-INF/pages/admin/activity-requests");
        return mv;
    }

}
