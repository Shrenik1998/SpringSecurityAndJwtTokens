package com.scaler.springsecurityandjwttokens.controller;


import com.scaler.springsecurityandjwttokens.DTO.LoginDto;
import com.scaler.springsecurityandjwttokens.DTO.UserDto;
import com.scaler.springsecurityandjwttokens.models.Users;
import com.scaler.springsecurityandjwttokens.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request)
    {
        return "hello " + request.getSession().getId();
    }

    @PostMapping("/register")
    public Users register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto)
    {
        return userService.verify(loginDto);
    }
}
