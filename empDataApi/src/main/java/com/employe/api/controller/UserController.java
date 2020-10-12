package com.employe.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employe.api.model.User;
import com.employe.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object regiterUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @PostMapping("/login")
    public Object loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping("/get")
    public Object getUser(HttpServletRequest request) {
    	System.out.println("getuserIDDD..."+request.getAttribute("userId"));
        ObjectId userId = (ObjectId) request.getAttribute("userId");
        return userService.getUser(userId);
    }
    
    @GetMapping("/isAuthorized")
    public Object getAuthorize(HttpServletRequest request) {
    	String token = request.getHeader("Authorization");
        return userService.getAuthorize(token);
    }
}