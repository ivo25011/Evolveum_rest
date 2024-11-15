package com.example.Evolveum_rest.controller;

import com.example.Evolveum_rest.model.Users;
import com.example.Evolveum_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{name}")
    public ResponseEntity<String> getUser(@PathVariable String  name){
        return userService.getUserByName(name);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PutMapping("/{name}")
    public ResponseEntity<String> updateUser(@PathVariable String name, @RequestBody Users updatedUser) {
        return userService.updateUser(name, updatedUser);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        return userService.deleteUser(name);
    }
}
