package com.example.apirest.controllers;


import com.example.apirest.models.entity.User;
import com.example.apirest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class  UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
       return  userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("{id}")
    public ResponseEntity<User> searchUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() ->ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
         userService.deleteUser(id);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user){
        user.setId(id);
        userService.updateUser(user);
    }

}
