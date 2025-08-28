package com.AutoConnect.AutoConnect.Controller;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Role;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.UserMapper;
import com.AutoConnect.AutoConnect.Service.UserService;
import jakarta.validation.Valid;
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
/*    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequest) {
        User user = UserMapper.UserRequestDTOToUser(userRequest);
        User newUser = userService.saveUser(user);
        UserResponseDTO userResponseDTO = UserMapper.UserToUserResponseDTO(newUser);
        return ResponseEntity.ok(userResponseDTO) ;
    }*/
}
