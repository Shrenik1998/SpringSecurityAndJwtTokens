package com.scaler.springsecurityandjwttokens.service;

import com.scaler.springsecurityandjwttokens.DTO.LoginDto;
import com.scaler.springsecurityandjwttokens.DTO.UserDto;
import com.scaler.springsecurityandjwttokens.models.Users;
import com.scaler.springsecurityandjwttokens.repo.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ThreadPoolTaskSchedulerBuilder threadPoolTaskSchedulerBuilder;

    @Autowired
    private JWTService jwtService;

    public Users register(UserDto userDto)
    {

        Users user = userRepo.findByUserName(userDto.getUserName());

        if(user == null)
        {
            user = new Users();
            //        user.setId(userDto.getId());
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setRole(userDto.getRole());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }

        return user;
    }

    public String verify(LoginDto userDto)
    {
        Users user = new Users();
//        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
//        user.setRole(userDto.getRole());

        Authentication authenticaion = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if(authenticaion.isAuthenticated())
        {
            return jwtService.generateToken(user.getUserName());
        }

        return "FAIL";
    }
}
