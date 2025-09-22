package com.AutoConnect.AutoConnect.Service;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.DTO.UserloginRequestDTO;
import com.AutoConnect.AutoConnect.Entity.Garage;
import com.AutoConnect.AutoConnect.Entity.Enum.Role;
import com.AutoConnect.AutoConnect.Entity.User;
import com.AutoConnect.AutoConnect.Mapper.UserMapper;
import com.AutoConnect.AutoConnect.Repository.GarageRepository;
import com.AutoConnect.AutoConnect.Repository.UserRepository;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import com.AutoConnect.AutoConnect.execption.NotFoundHandlerException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RestTemplateService restTemplateService;
    private final GarageRepository garageRepository;

    private final  PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil, RestTemplateService restTemplateService, GarageRepository garageRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.restTemplateService = restTemplateService;
        this.garageRepository = garageRepository;

    }

   public UserResponseDTO saveTechnicians(UserRequestDTO userRequest, String authHeader) {
       User user = saveUser(userRequest);
       User findId = userRepository.findById(user.getId())
               .orElse(new User());
            findId.setRole(Role.TECHNICIAN);
            User created = userRepository.save(user);

       String token = authHeader.replace("Bearer ", "");
       String userEmail = jwtUtil.extractEmail(token);
       User engineerUser = userRepository.findByEmail(userEmail)
               .orElseThrow(() -> new RuntimeException("User not found"));
       Garage garageId = garageRepository.findById(engineerUser.getId())
               .orElseThrow(() -> new RuntimeException("Garage not found"));
       List<User> users = new ArrayList<>();
       users.add(created);
        garageId.setTechnicians(users);
        garageId.setQuantityTechnicians(1);

        return UserMapper.UserToUserResponseDTO(created) ;
    }

    public User saveUser(UserRequestDTO userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new NotFoundHandlerException("Email already exists");
        }

        User user = UserMapper.UserRequestDTOToUser(userRequest);

            if (userRequest.getSiren() == null || userRequest.getSiren().isBlank()) {
                user.setRole(Role.CUSTOMERS);
            } else {

                user.setRole(Role.ENGINEER);
                String raw = user.getPassword();
                if (raw != null && !raw.startsWith("$2a$") && !raw.startsWith("$2b$") && !raw.startsWith("$2y$")) {
                    user.setPassword(encoder.encode(raw));
                }
                User created = userRepository.save(user);
                Garage  garage = restTemplateService.createGarage(userRequest,created);
                Garage geocodeGarage = restTemplateService.createGeocode(garage);

                UserMapper.UserToUserResponseDTO(created);
                return created;
            }

        String raw = user.getPassword();
        if (raw != null && !raw.startsWith("$2a$") && !raw.startsWith("$2b$") && !raw.startsWith("$2y$")) {
            user.setPassword(encoder.encode(raw));
        }

        User created = userRepository.save(user);


        return created;
    }

    public List<UserResponseDTO> findByRoleTechnicians(String authHeader){
        String token = authHeader.replace("Bearer ", "");
        String userEmail = jwtUtil.extractEmail(token);
        User engineerUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Garage garageId = garageRepository.findById(engineerUser.getId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));
        Garage garage = garageRepository.findById(engineerUser.getGarage().getId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));
        List<User> techGarage = userRepository.findByGarageIdAndRole(garage.getId(), Role.TECHNICIAN);
        return techGarage.stream()
                .map(UserMapper::UserToUserResponseDTO)
                .toList();
    }

    public ResponseEntity<?> createToken(UserloginRequestDTO newUser){
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
