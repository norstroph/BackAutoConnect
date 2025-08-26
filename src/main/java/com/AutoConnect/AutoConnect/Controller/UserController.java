package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.Entity.Role;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> allUser(){
        return ResponseEntity.ok( userService.findAll());
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setRole(Role.ADMIN);
        return userService.saveUser(user);
    }
}
