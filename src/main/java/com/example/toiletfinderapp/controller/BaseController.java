package com.example.toiletfinderapp.controller;

import com.example.toiletfinderapp.security.JwtHelper;
import com.example.toiletfinderapp.service.CommentService;
import com.example.toiletfinderapp.service.ToiletService;
import com.example.toiletfinderapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    UserService adminService;

    @Autowired
    CommentService commentService;

    @Autowired
    JwtHelper jwtHelp;

    @Autowired
    ToiletService toiletService;
}
