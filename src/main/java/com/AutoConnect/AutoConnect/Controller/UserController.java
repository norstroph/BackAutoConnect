package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> allUser(){

        return ResponseEntity.ok( userService.findAll());
    }
}
