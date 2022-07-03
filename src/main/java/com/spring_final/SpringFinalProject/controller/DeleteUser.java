package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteUser {

    @Autowired
    UserService service;

    @Secured("ADMIN")
    @RequestMapping("/admin/userDelete")
    public String deleteUser(@RequestParam("id") int id){

        if(service.getUser(id) == null)
            return "WEB-INF/pages/admin/users";

        service.deleteUser(id);
        return "redirect:/admin/users";
    }


}
