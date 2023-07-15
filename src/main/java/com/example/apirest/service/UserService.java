package com.example.apirest.service;

import com.example.apirest.models.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component

public interface UserService {



    User  createUser(User user);

    Optional<User> getUserById(Integer id);

    List<User> getAllUsers();

    void deleteUser(Integer id);

    User updateUser(User user);

}
