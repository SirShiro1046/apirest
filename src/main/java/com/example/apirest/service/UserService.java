package com.example.apirest.service;

import com.example.apirest.models.entity.User;
import com.example.apirest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        log.info("Entrando a createUser");
        return userRepository.save(user);
    }

    public User getUserById(Integer id){
        log.info("Entrando a getUserById");
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    public List<User> getAllUsers(){
        log.info("Entrando a getAllUsers");
        return userRepository.findAll();
    }

    public void deleteUser(Integer id){
        log.info("Entrando a deleteUser");
        userRepository.deleteById(id);
    }

    public void updateUser(User user){
        log.info("Entrando a updateUser");
        userRepository.save(user);
    }

}
