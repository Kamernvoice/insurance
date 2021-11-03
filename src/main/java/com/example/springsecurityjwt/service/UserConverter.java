package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.UserDto;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private RoleRepository roleRepository;

    public UserConverter(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLogin(userDto.getLogin());
        user.setRole(roleRepository.findByName(userDto.getRole()));
        user.setPhone(userDto.getPhone());
        user.setRating(userDto.getRating());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .role(user.getRole().getName())
                .phone(user.getPhone())
                .rating(user.getRating())
                .build();
    }
}
