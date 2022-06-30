package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.Role;
import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/admin/userUpdate")
    public ModelAndView updateUser(@RequestParam("user_id") int user_id,
            @ModelAttribute("user") User user, @RequestParam(value = "adminRole", defaultValue = "None") String admin) {
        ModelAndView mv = new ModelAndView();
        Role adminRole = service.getRole("ADMIN");

        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null ||
                user.getAge() == 0 || user.getGender() == null ||
                user.getContact() == null) {
            mv.addObject("adminR", adminRole);
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
        if(admin.equals("true")) {
            foundUser.getRoles().add(adminRole);
        }

        List<String> errors = UserValidator.validateState(user);

        service.updateUser(user);

        mv.setViewName("redirect:/admin/users");
        return mv;
    }
}
