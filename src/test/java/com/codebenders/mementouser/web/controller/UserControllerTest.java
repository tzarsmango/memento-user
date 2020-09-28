package com.codebenders.mementouser.web.controller;

import java.util.UUID;

import com.codebenders.mementouser.web.model.UserResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void getUserByWrongId() throws Exception {
        mockMvc.perform(get("/api/v1/user/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/api/v1/user/" + UUID.randomUUID().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void createNewUser() throws Exception {
        UserResponseModel user = UserResponseModel.builder().firstName("tom").lastName("jones").email("tj@mail.com").build();
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(userJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}