package com.example.springsecurityjwt.controller;

//import com.example.springsecurityjwt.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.example.springsecurityjwt.entity.User;
//
//import java.util.List;
//
//@RestController
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping(value = "/users")
//    public ResponseEntity<?> create(@RequestBody User user) {
//        userRepository.save(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/users")
//    public ResponseEntity<List<User>> findAll() {
//        final List<User> clients = userRepository.findAll();
//
//        return clients != null &&  !clients.isEmpty()
//                ? new ResponseEntity<>(clients, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping(value = "/users/{id}")
//    public ResponseEntity<User> getById(@PathVariable(name = "id") int id) {
//        final User user = userRepository.getById(id);
//
//        return user != null
//                ? new ResponseEntity<>(user, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping(value = "/users/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
//        if (userRepository.existsById(id)) {
//            user.setId(id);
//            final User updated = userRepository.save(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
//
//    @DeleteMapping(value = "/users/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
//}
