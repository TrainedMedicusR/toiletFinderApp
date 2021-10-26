package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.entity.JsonResult;
import com.example.toiletfinderapp.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    void addUser(User user);

    JsonResult login(HttpServletResponse response, String username, String password);
}
