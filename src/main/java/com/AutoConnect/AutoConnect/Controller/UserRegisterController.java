package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class UserRegisterController {

    private final UserService userService;
    public UserRegisterController(UserService userService){
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

}
