package com.example.apirest.controllers;


import com.example.apirest.models.entity.User;
import com.example.apirest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
       return  userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("{id}")
    public User searchUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);

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
