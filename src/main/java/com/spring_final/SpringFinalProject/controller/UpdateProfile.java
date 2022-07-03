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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UpdateProfile {

    @Autowired
    UserService service;

    @Secured("USER")
    @RequestMapping("/userProfileUpdate")
    public ModelAndView updateProfile(@ModelAttribute("user") User user){
        ModelAndView mv = new ModelAndView();
        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getPassword() == null ||
                user.getAge() == 0 ||
                user.getContact() == null) {
            mv.setViewName("WEB-INF/pages/profile-update");
            return mv;
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User foundUser = service.getUser(username);

        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPassword(user.getPassword());
        foundUser.setAge(user.getAge());
        foundUser.setContact(user.getContact());


        List<String> errors = UserValidator.validateState(foundUser);

        if(!errors.isEmpty()){
            mv.addObject("errors", errors);
            mv.setViewName("WEB-INF/pages/profile-update");
            return mv;
        }

        service.updateProfile(foundUser);

        mv.setViewName("redirect:/profile");
        return mv;
    }

}
