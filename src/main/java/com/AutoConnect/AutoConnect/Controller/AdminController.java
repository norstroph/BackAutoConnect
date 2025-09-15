package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService){
        this.userService = userService;

    }

    @GetMapping()
    public ResponseEntity<List<User>> allUser(){
        return ResponseEntity.ok( userService.findAll());
    }
}
