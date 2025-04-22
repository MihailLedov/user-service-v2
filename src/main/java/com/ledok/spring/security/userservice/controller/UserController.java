package com.ledok.spring.security.userservice.controller;

import com.ledok.spring.security.userservice.controller.dto.RegisterRequest;
import com.ledok.spring.security.userservice.controller.dto.UpdateDto;
import com.ledok.spring.security.userservice.controller.dto.UserDto;
import com.ledok.spring.security.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/search/email")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/search/username")
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateDto updateDto) {
        return ResponseEntity.ok(userService.updateUser(updateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
