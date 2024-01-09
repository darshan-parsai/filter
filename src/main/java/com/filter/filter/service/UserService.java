package com.filter.filter.service;

import com.filter.filter.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();
}
