package com.example.toiletfinderapp.service;

import com.example.toiletfinderapp.dao.UserMapper;
import com.example.toiletfinderapp.entity.JsonResult;
import com.example.toiletfinderapp.entity.User;
import com.example.toiletfinderapp.security.JwtHelper;
import com.example.toiletfinderapp.util.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtHelper jwtHelp;


    @Override
    public void addUser(User user) {
        BCryptUtil util = new BCryptUtil();
        String hash = util.encode(user.getPassword());
        user.setPassword(hash);
        userMapper.insertSelective(user);
    }

    @Override
    public JsonResult login(HttpServletResponse response, String username, String password) {
        User personInfo = userMapper.selectByAuth(username);
        if (personInfo == null) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        String storedHash = personInfo.getPassword();
        BCryptUtil bCryptUtil = new BCryptUtil();
        boolean match = bCryptUtil.matches(password, storedHash);
        if (!match) {
            throw new RuntimeException("Incorrect Username or Password");
        }
        com.example.toiletfinderapp.security.User loginUser = com.example.toiletfinderapp.security.User.parse(personInfo);
        String token = jwtHelp.generateToken(loginUser);
        return new JsonResult<String>(token);
    }
}
