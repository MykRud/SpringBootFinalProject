package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetUsers {

    @Autowired
    UserService service;

    @RequestMapping("/admin/users")
    public ModelAndView getUsers(){
        ModelAndView mv = new ModelAndView();
        List<User> users = service.getUsers();

        mv.addObject("users", users);

        Role admin = service.getRole("ADMIN");
        mv.addObject("adminRole", admin);

        mv.setViewName("WEB-INF/pages/admin/users");
        return mv;
    }

}
