package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteAdmin {

    @Autowired
    private UserService userService;

    @Secured("ADMIN")
    @RequestMapping("/admin/deleteAdmin")
    public String deleteAdmin(@RequestParam("user_id") int user_id){
        User user = userService.getUser(user_id);
        user.getRoles().remove(userService.getRole("ADMIN"));
        userService.updateUser(user);
        return "redirect:users";
    }

}
