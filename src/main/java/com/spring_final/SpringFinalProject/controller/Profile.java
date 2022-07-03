package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class Profile {

    @Autowired
    UserService service;

    @Secured("USER")
    @RequestMapping("/profile")
    public ModelAndView profile(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = service.getUser(username);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/WEB-INF/pages/profile");
        return mv;
    }

}
