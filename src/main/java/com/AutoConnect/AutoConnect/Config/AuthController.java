package com.AutoConnect.AutoConnect.Config;

import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    public AuthController(JwtUtil jwtUtil , UserRepository userRepository){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User newUser){
        User user = userRepository.findByName(newUser.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
