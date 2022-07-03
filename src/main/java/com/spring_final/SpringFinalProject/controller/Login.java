package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class Login {

    //@Autowired
    //UserService service;

    @RequestMapping("/login")
    public String addActivityPost(/*@ModelAttribute("user") User user, HttpServletRequest request*/){
        /*String username = user.getUsername();
        String password = user.getPassword();
        if(username == null || password == null)
            return "login";
        User us = service.getUser(username);

        List<String> errors = new ArrayList<>();
        if(us == null){
            Locale locale = Locale.getDefault();
            errors.add(ResourceBundle.getBundle("i18n.messages",
                    locale).getString("login.error.user"));
            return "login";
        }

        if(!BCrypt.checkpw(password, us.getPassword())){
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("authUser", us);

        return "redirect:home";*/
        return "login";
    }

}
