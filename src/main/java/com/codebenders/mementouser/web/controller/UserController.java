package com.codebenders.mementouser.web.controller;

import javax.validation.Valid;
import java.util.UUID;

import com.codebenders.mementouser.mapper.UserMapper;
import com.codebenders.mementouser.service.UserService;
import com.codebenders.mementouser.shared.UserDto;
import com.codebenders.mementouser.web.model.CreateUserRequestModel;
import com.codebenders.mementouser.web.model.UserResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    ResponseEntity<UserResponseModel> getUserById(@PathVariable("userId") UUID userId) {
        UserDto userDto = userService.findUserById(userId);
        return new ResponseEntity(userMapper.userDtoToUserResponseModel(userDto), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        UserDto userDto = userMapper.createUserRequestModelToUserDto(userDetails);

        try {
            UserDto savedDto = userService.createUser(userDto);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", "/api/v1/user/" + savedDto.getId().toString());
            return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

//    @PutMapping("/{userId}")
//    public ResponseEntity updateUser(@PathVariable("userId") UUID userId, @RequestBody UserResponse userResponse) {
//        //TODO Get User from the DB and update User
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

}

