package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UpdateUser {

    @Autowired
    UserService service;

    @Secured("ADMIN")
    @RequestMapping("/admin/userUpdate")
    public ModelAndView updateUser(@RequestParam("user_id") int user_id,
            @ModelAttribute("user") User user) {
        ModelAndView mv = new ModelAndView();

        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null ||
                user.getAge() == 0 || user.getGender() == null ||
                user.getContact() == null) {
            mv.setViewName("WEB-INF/pages/admin/update-user");
            return mv;
        }

        User foundUser = service.getUser(user_id);

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setUsername(user.getUsername());
        foundUser.setAge(user.getAge());
        foundUser.setGender(user.getGender());
        foundUser.setContact(user.getContact());

        List<String> errors = UserValidator.validateState(user);

        service.updateUser(user);

        mv.setViewName("redirect:/admin/users");
        return mv;
    }
}
