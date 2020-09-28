package com.codebenders.mementouser.mapper;

import com.codebenders.mementouser.domain.UserData;
import com.codebenders.mementouser.shared.UserDto;
import com.codebenders.mementouser.web.model.CreateUserRequestModel;
import com.codebenders.mementouser.web.model.UserResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserDto userDataToUserDto(UserData userData);

    UserData userDtoToUserData(UserDto userDto);

    UserResponseModel userDtoToUserResponseModel(UserDto userDto);

    UserDto userResponseModelToUserDto(UserResponseModel userResponseModel);

    UserDto createUserRequestModelToUserDto(CreateUserRequestModel createUserRequestModel);

}
