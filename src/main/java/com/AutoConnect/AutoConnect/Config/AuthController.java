package com.AutoConnect.AutoConnect.Config;

import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public AuthController(JwtUtil jwtUtil , UserRepository userRepository, PasswordEncoder encoder){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User newUser){
        User user = userRepository.findByEmail(newUser.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user == null || !encoder.matches(newUser.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Identifiants incorrects");
        }
        String token = jwtUtil.generateToken(newUser.getEmail(), user.getRole().name());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", user.getEmail()
        ));
    }

}
