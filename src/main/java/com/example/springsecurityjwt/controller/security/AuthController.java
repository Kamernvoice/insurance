package com.example.springsecurityjwt.controller.security;

import com.example.springsecurityjwt.config.jwt.JwtProvider;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.exception.ValidationException;
import com.example.springsecurityjwt.repository.RoleRepository;
import com.example.springsecurityjwt.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.java.Log;

import javax.validation.Valid;

@Log
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) throws ValidationException {
        User u = new User();
        u.setName(registrationRequest.getName());
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setRole(roleRepository.findByName("ROLE_" + registrationRequest.getRole()));
        if(registrationRequest.getPhone() != null) u.setPhone(registrationRequest.getPhone());
        AuthRequest request = new AuthRequest(u.getName(), u.getLogin(), u.getPassword(), u.getPhone());
        return securityService.saveUser(u) != null
                ? new ResponseEntity<>(auth(request), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        User user = securityService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user);
        return new AuthResponse(token);
    }
}
