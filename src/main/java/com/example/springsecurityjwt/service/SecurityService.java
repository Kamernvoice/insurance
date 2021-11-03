package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.UserDto;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.exception.ValidationException;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class SecurityService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserConverter userConverter;

    public UserDto saveUser(User user) throws ValidationException{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        validateUser(user);
        if(findByLogin(user.getLogin()) == null) {
            UserDto userDTO = userConverter.fromUserToUserDto(userRepository.save(user));
            return userDTO;
        }
        return null;
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    private void validateUser(User user) throws ValidationException {
        if (isNull(user)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(user.getLogin()) || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }
}
