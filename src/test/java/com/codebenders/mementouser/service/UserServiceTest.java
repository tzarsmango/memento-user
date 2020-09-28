package com.codebenders.mementouser.service;

import java.util.UUID;

import com.codebenders.mementouser.shared.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
class UserServiceTest {

    @Configuration
    static class TestConfig {
        @Bean
        UserService userService() {
            return new UserServiceTestImpl();
        }
    }

    @Autowired
    UserService userService;

    @Test
    void findUserById() {
        UUID randomId = UUID.randomUUID();
        UserDto user = userService.findUserById(randomId);

        assertEquals(randomId, user.getId());
    }
}