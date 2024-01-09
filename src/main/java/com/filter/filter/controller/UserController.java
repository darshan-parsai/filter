package com.filter.filter.controller;

import com.filter.filter.model.User;
import com.filter.filter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save-user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/get-users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

}
