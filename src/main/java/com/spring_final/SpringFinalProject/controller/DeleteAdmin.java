package com.spring_final.SpringFinalProject.controller;

import com.spring_final.SpringFinalProject.model.User;
import com.spring_final.SpringFinalProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * A controller that allows you to delete admin role for user
 *
 * @author Misha Rudyk
 * @see com.spring_final.SpringFinalProject.model.Role
 * @see User
 * @see UserService
 */
@Controller
@Slf4j
public class DeleteAdmin {

    @Autowired
    private UserService userService;

    /**
     * Mapped method
     *
     * @return page with users
     */
    @Secured("ADMIN")
    @RequestMapping("/admin/deleteAdmin")
    public String deleteAdmin(@RequestParam("user_id") int user_id) {
        log.info("Fetching user with id {}", user_id);
        User user = userService.getUser(user_id);
        user.getRoles().remove(userService.getRole("ADMIN"));
        log.info("Updating user without ADMIN role");
        userService.updateUser(user);
        return "redirect:users";
    }

}
