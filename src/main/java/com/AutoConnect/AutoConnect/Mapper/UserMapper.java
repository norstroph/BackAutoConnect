package com.AutoConnect.AutoConnect.Mapper;

import com.AutoConnect.AutoConnect.DTO.UserRequestDTO;
import com.AutoConnect.AutoConnect.DTO.UserResponseDTO;
import com.AutoConnect.AutoConnect.Entity.User;

public class UserMapper {
    public static User UserRequestDTOToUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getSurname());
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        return user;
    }
    public static UserResponseDTO UserToUserResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setPhone(user.getPhone());
        return userResponseDTO;
    }
    public static User UserResponseDTOToUser(UserResponseDTO userResponseDTO){
        User user = new User();
        user.setEmail(userResponseDTO.getEmail());
        user.setName(userResponseDTO.getName());
        user.setPhone(userResponseDTO.getPhone());
        return user;
    }

    public static User UserRequestWithoutinstantiationToUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getSurname());
        user.setName(userRequestDTO.getName());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        return user;
    }

}
