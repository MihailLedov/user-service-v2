package com.ledok.spring.security.userservice.mapper;


import com.ledok.spring.security.userservice.controller.dto.RegisterRequest;
import com.ledok.spring.security.userservice.controller.dto.UserDto;
import com.ledok.spring.security.userservice.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "password", ignore = true) // Пароль обрабатывается отдельно
//    @Mapping(target = "role", constant = "USER")
//    @Mapping(target = "active", constant = "true")
//    UserEntity toEntity(UserDto userDto);

    UserDto toEntity(RegisterRequest request);
}
