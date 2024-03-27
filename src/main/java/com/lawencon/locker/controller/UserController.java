package com.lawencon.locker.controller;

import com.lawencon.locker.model.Users;
import com.lawencon.locker.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
//    @Autowired
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        Users newUser = userService.createUser(user);
        return ResponseEntity.ok("User registered successfully with ID: " + newUser.getUserId());
    }
}
