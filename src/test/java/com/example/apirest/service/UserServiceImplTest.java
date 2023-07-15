package com.example.apirest.service;

import com.example.apirest.models.entity.User;
import com.example.apirest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Test
    void whengetAllUser(){
        List<User> resposeList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setNombre("sadas");
        user1.setApellido("algo");
        user1.setEmail("myandroid");
        user1.setPassword("1234");
        resposeList.add(user1);
        Mockito.when(userRepository.findAll())
                .thenReturn(resposeList);

        List<User> users = userService.getAllUsers();
        Mockito
                .verify(userRepository, Mockito.times(1))
                .findAll();
        assertNotNull(users);
        assertEquals(1,users.size());
    }

    @Test
    void whencreateUser(){
        User user1 = new User();
        user1.setId(1);
        user1.setNombre("sadas");
        user1.setApellido("algo");
        user1.setEmail("myandroid");
        user1.setPassword("1234");

        Mockito
                .when(userRepository.save(user1))
                .thenReturn(user1);

        User user2 = userService.createUser(user1);
        Mockito
                .verify(userRepository, Mockito.times(1))
                .save(user1);

        assertNotNull(user2);
        assertEquals("sadas",user2.getNombre());

    }

    @Test
    void whenupdateUser(){
        User user1 = new User();
        user1.setId(1);
        user1.setNombre("sadas");
        user1.setApellido("algo");
        user1.setEmail("myandroid");
        user1.setPassword("1234");
        Mockito
                .when(userRepository.save(user1))
                .thenReturn(user1);

        User user2 = userService.updateUser(user1);

        Mockito
                .verify(userRepository, Mockito.times(1))
                .save(user1);

        assertNotNull(user2);
        assertEquals("sadas",user2.getNombre());
    }

    @Test
    void whengetUserById(){
        User user1 = new User();
        user1.setId(1);
        user1.setNombre("sadas");
        user1.setApellido("algo");
        user1.setEmail("myandroid");
        user1.setPassword("1234");
        Mockito
                .when(userRepository.findById(1))
                .thenReturn(Optional.of(user1));
        Optional<User> respuesta = userService.getUserById(1);
        Mockito
                .verify(userRepository,Mockito.times(1))
                .findById(1);
        assertNotNull(respuesta);
        assertEquals(1,respuesta.get().getId());
        assertEquals("sadas",respuesta.get().getNombre());
        assertEquals("algo",respuesta.get().getApellido());
        assertEquals("myandroid",respuesta.get().getEmail());
        assertEquals("1234",respuesta.get().getPassword());

    }

    @Test
    void whendeleteUser(){
        User user1 = new User();
        user1.setId(1);
        user1.setNombre("sadas");
        user1.setApellido("algo");
        user1.setEmail("myandroid");
        user1.setPassword("1234");

        Mockito
                .doNothing()
                .when(userRepository)
                .deleteById(1);

        userService.deleteUser(user1.getId());

        Mockito
                .verify(userRepository, Mockito.times(1))
                .deleteById(1);

    }
}