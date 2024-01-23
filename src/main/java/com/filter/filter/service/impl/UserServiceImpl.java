package com.filter.filter.service.impl;

import com.filter.filter.model.User;
import com.filter.filter.repository.UserRepo;
import com.filter.filter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User saveUser(User user) {
        User newuser = User.builder().id(user.getId()).email(user.getEmail()).password(passwordEncoder.encode(user.getPassword())).build();
        userRepo.save(newuser);
        user = User.builder().id(newuser.getId()).email(user.getEmail()).password(user.getPassword()).build();
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
}
