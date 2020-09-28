package com.codebenders.mementouser.service;

import java.util.UUID;

import com.codebenders.mementouser.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    UserDto findUserById(UUID userId);

    UserDto createUser(UserDto userDto);

    UserDto getUserDetailsByEmail(String userName);
}
