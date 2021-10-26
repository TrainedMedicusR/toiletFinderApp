package com.example.toiletfinderapp.controller;

import com.example.toiletfinderapp.security.User;
import com.example.toiletfinderapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login", produces = "application/json")
    public Object login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        // This function is used to login
        // response is used to show login situations
        return userService.login(response, username, password);
    }

    @GetMapping(value = "/current", produces = "application/json")
    // Achieve the current token of the specfic user
    public Object currentUser(@AuthenticationPrincipal User user) {
        return user;
    }

    /**
     * Create a new user by given information
     */
    @ApiOperation("Register a new user")
    @PostMapping("/register")
    public String addUser(com.example.toiletfinderapp.entity.User user) {
        String pwd = user.getPassword();
        String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,18}$";
        boolean isMatch = Pattern.matches(regex, pwd);
        if (isMatch) {
            try {
                userService.addUser(user);
                return null;
            } catch (DataIntegrityViolationException e) {
                throw new RuntimeException("Username is already occupied");
            }

        } else {
            throw new RuntimeException("Password should be of length 6-18 with number and lowercase letter and uppercase letter");
        }
    }

}
