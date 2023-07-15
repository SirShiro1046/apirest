package com.example.apirest.service;

import com.example.apirest.models.entity.User;
import com.example.apirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User createUser(User user){
        log.info("Entrando a createUser");
        return userRepository.save(user);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Integer id){
        log.info("Entrando a getUserById");
        return  userRepository.findById(id);

    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers(){
        log.info("Entrando a getAllUsers");
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public void deleteUser(Integer id){
        log.info("Entrando a deleteUser");
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public User updateUser(User user){
        log.info("Entrando a updateUser");
        userRepository.save(user);
        return user;
    }
}
