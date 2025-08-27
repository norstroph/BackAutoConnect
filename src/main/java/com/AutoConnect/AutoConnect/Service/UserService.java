package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.Role;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.UserMapper;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.execption.NotFoundHandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final  PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;

    }

   public UserResponseDTO saveTechnicians(UserRequestDTO userRequest) {
       User user = UserMapper.UserResponseDTOToUser( saveUser(userRequest));
            user.setRole(Role.TECHNICIAN);
            User created = userRepository.save(user);

        return UserMapper.UserToUserResponseDTO(created) ;
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new NotFoundHandlerException("Email already exists");
        }

        User user = UserMapper.UserRequestDTOToUser(userRequest);

            if (userRequest.getSiren() == null || userRequest.getSiren().isBlank()) {
                user.setRole(Role.CUSTOMERS);
            } else {
                user.setRole(Role.ENGINEER);
            }

        String raw = user.getPassword();
        if (raw != null && !raw.startsWith("$2a$") && !raw.startsWith("$2b$") && !raw.startsWith("$2y$")) {
            user.setPassword(encoder.encode(raw));
        }
        return userRepository.save(user);
    }

    public List<UserResponseDTO> findByRoleTechnicians(Role role){
        List<User> users = userRepository.findByRole(role)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found"));

        return users.stream()
                .map(UserMapper::UserToUserResponseDTO)
                .toList();
    }

    public Optional<User> FindByname(String name){
        return userRepository.findByName(name);
    }

    public List<User> findAll(){
        return userRepository.findAll();

    }
    public Optional<User> FindByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
