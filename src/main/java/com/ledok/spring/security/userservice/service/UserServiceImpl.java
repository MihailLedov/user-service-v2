package com.ledok.spring.security.userservice.service;

import com.ledok.spring.security.userservice.advice.UserAlreadyExistsException;
import com.ledok.spring.security.userservice.advice.UserNotFoundException;
import com.ledok.spring.security.userservice.controller.dto.RegisterRequest;
import com.ledok.spring.security.userservice.controller.dto.UpdateDto;
import com.ledok.spring.security.userservice.controller.dto.UserDto;
import com.ledok.spring.security.userservice.jpa.entity.UserEntity;
import com.ledok.spring.security.userservice.jpa.repository.UserRepository;
import com.ledok.spring.security.userservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("Имя пользователя уже существует");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole("USER");
        user.setActive(true);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto getUserById(long id) {
        UserEntity user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("Пользователя с таким id: " + id + " не существует!"));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String email) {

        // 1. Проверка внесенного email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        // роверяем существует ли пользователь с таким email
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException("Пользователь с email '" + email + "' не найден");
        }
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("При повторной проверке пользователь не был найден"));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto getUserByUsername(String username) {
        // 1. Проверка внесенного username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        // Проверяем существует ли пользователь с таким username
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException("Пользователь с именем '" + username + "' не найден");
        }
        // Получение пользователя
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("При повторной проверке пользователь не был найден"));
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(UpdateDto updateDto) {
        // Проверяем есть ли такой пользователь
        UserEntity userEntity = userRepository.findById(updateDto.getId()).
                orElseThrow(() -> new UserNotFoundException("Пользователя с таким id: " + updateDto.getId() + " не существует!"));
        // Проверяем не занято ли такое имя пользователя
        if (updateDto.getUsername() != null) {
            if (!updateDto.getUsername().equals(userEntity.getUsername()) &&
                    userRepository.existsByUsername(updateDto.getUsername())) {
                throw new UserAlreadyExistsException("Пользователь с таким именем : " + updateDto.getUsername() + " уже существует!");
            }
            userEntity.setUsername(updateDto.getUsername());
        }
        // Проверяем не занят ли Email другим пользователем
        if (updateDto.getEmail() != null) {
            if (!updateDto.getEmail().equals(userEntity.getEmail()) &&
                    userRepository.existsByEmail(updateDto.getEmail())) {
                throw new UserAlreadyExistsException("Пользователь с таким Email : " + updateDto.getEmail() + " уже существует!");
            }
            userEntity.setEmail(updateDto.getEmail());
        }
            return userMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким id: " + id + " не существует!"));
        userRepository.deleteById(user.getId());
    }
}
