package org.example.controller;

import org.example.entity.User;
import org.example.entity.dto.UserDTO;
import org.example.service.CarService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<?> authUser(@RequestBody UserDTO user){
        try {
            return ResponseEntity.ok(userService.authUser(user));
        } catch (BadCredentialsException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

//    @GetMapping(path = "/user/home")
//    public String helloUser(){
//        return "Hello, user!";
//    }
//
//    @GetMapping(path = "/admin/home")
//    public String helloAdmin(){
//        return "Hello, admin!";
//    }
}
