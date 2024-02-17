package org.geekhub.hw11.controller;

import org.geekhub.hw11.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ModelAndView users(ModelAndView modelAndView) {
        modelAndView.setViewName("users");
        modelAndView.addObject("users", service.getAllUsers());
        return modelAndView;
    }
}
