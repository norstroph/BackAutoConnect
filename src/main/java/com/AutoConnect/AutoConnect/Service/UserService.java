package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.Entity.Role;
import com.AutoConnect.AutoConnect.Entity.User;
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

    public User saveUser(User user){
        user.setRole(Role.ADMIN);
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new NotFoundHandlerException("Email already exist");
        }

        String raw = user.getPassword();
        if (raw != null && !raw.startsWith("$2a$") && !raw.startsWith("$2b$") && !raw.startsWith("$2y$")) {
            user.setPassword(encoder.encode(raw));
        }
        return userRepository.save(user);
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
