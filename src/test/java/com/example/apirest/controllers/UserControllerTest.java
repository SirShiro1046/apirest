package com.example.apirest.controllers;

import com.example.apirest.models.entity.User;
import com.example.apirest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        objectMapper=new ObjectMapper();
    }

    @Test
    void whengetAllUsers() throws Exception{
        User user = new User();
        List<User> usersMockResponse = new ArrayList<>();
        usersMockResponse.add(user);
        usersMockResponse.add(user);
        usersMockResponse.add(user);

        Mockito
                .when(userService.getAllUsers())
                .thenReturn(usersMockResponse);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void whendeleteUserById() throws Exception {
        User user = new User();
        user.setId(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService, Mockito.times(1)).deleteUser(user.getId());



    }
    @Test
    void whensearchUserById() throws Exception {
        User user = new User();
        user.setId(1);

        Mockito
                .when(userService.getUserById(1))
                .thenReturn(Optional.of(user));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setId(1);

        Mockito.when(userService.createUser(Mockito.any(User.class)))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setNombre("jose");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}