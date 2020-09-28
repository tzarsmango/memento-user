package com.codebenders.mementouser.service;

import java.util.ArrayList;
import java.util.UUID;

import com.codebenders.mementouser.shared.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceTestImpl implements UserService {
    @Override
    public UserDto findUserById(UUID userId) {
        return UserDto.builder().id(userId).firstName("Jon").lastName("Margin").build();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        return UserDto.builder().id(UUID.randomUUID()).firstName("Jon").lastName("Margin").email(email).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("jm@email.com", "qwerty", true, true, true, true, new ArrayList<>());
    }
}
