package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.controller.dto.RegisterRequest;
import com.ledok.spring.security.userservice.controller.dto.UpdateDto;
import com.ledok.spring.security.userservice.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    // Операции с пользователем
    UserDto registerUser(RegisterRequest registerRequest);

    List<UserDto> getAllUsers();

    UserDto getUserById(long id);

    UserDto getUserByEmail(String email);

    UserDto getUserByUsername(String username);

    UserDto updateUser(UpdateDto updateDto);

    void deleteUser(long id);
}
