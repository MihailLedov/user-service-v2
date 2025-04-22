package com.ledok.spring.security.userservice.mapper;


import com.ledok.spring.security.userservice.config.SecurityConfig;
import com.ledok.spring.security.userservice.controller.dto.RegisterRequest;
import com.ledok.spring.security.userservice.controller.dto.UserDto;
import com.ledok.spring.security.userservice.persistence.entity.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

        //        UserDto toDto(UserEntity user);
//
//        @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))")
//        @Mapping(target = "role", constant = "USER")
//        @Mapping(target = "active", constant = "true")
//        UserEntity toEntity(RegisterRequest request, @Context PasswordEncoder passwordEncoder);
//    }
//        @Mapping(target = "id", source = "id")
//        @Mapping(target = "username", source = "username")
//        @Mapping(target = "email", source = "email")
//        @Mapping(target = "role", source = "role")
        default UserDto toDto(UserEntity user){
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setEmail(user.getEmail());
                userDto.setRole(user.getRole());
                return userDto;
        }


        default UserEntity toEntity(RegisterRequest request, PasswordEncoder passwordEncoder) {
                UserEntity user = new UserEntity();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setEmail(request.getEmail());
                user.setRole("USER");
                user.setActive(true);
                return user;
        }
}
