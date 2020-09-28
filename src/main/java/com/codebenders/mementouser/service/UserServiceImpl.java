package com.codebenders.mementouser.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.codebenders.mementouser.domain.UserData;
import com.codebenders.mementouser.mapper.UserMapper;
import com.codebenders.mementouser.repository.UserRepository;
import com.codebenders.mementouser.shared.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto findUserById(UUID userId) {
        log.debug("Finding user by id: " + userId);

        Optional<UserData> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            log.debug("Found UserId: " + userId);
            return userMapper.userDataToUserDto(userOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + userId);
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserData user = userMapper.userDtoToUserData(userDto);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserData savedUser = userRepository.save(user);
        return userMapper.userDataToUserDto(savedUser);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserData userData = userRepository.findByEmail(email);

        if(userData == null) throw new UsernameNotFoundException(email);

        return userMapper.userDataToUserDto(userData);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserData userData = userRepository.findByEmail(userName);

        if(userData == null) throw new UsernameNotFoundException(userName);

        return new User(userData.getEmail(), userData.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }
}
