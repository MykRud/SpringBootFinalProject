package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SetAdmin {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/setAdmin")
    public String setAdmin(@RequestParam("user_id") int user_id){
        User user = userService.getUser(user_id);
        user.getRoles().add(userService.getRole("ADMIN"));
        userService.updateUser(user);
        return "redirect:users";
    }

}
