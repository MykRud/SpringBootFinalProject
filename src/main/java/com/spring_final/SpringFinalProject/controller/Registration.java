package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import com.spring_final.SpringFinalProject.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Registration {

    @Autowired
    UserService service;

    @RequestMapping("/registration")
    public ModelAndView registration(@ModelAttribute("user") User user){
        ModelAndView mv = new ModelAndView();
        if(user.getFirstName() == null || user.getLastName() == null ||
        user.getUsername() == null || user.getPassword() == null ||
        user.getAge() == 0 || user.getGender() == null ||
        user.getContact() == null) {
            mv.setViewName("registration");
            return mv;
        }

        User foundUser = service.getUser(user.getUsername());

        if(foundUser != null) {
            mv.setViewName("registration");
            return mv;
        }

        List<String> errors = UserValidator.validateState(user);

        if(!errors.isEmpty()){
            mv.addObject("errors", errors);
            mv.setViewName("registration");
            return mv;
        }

        service.addUser(user);

        mv.setViewName("redirect:/login");
        return mv;
    }
}
