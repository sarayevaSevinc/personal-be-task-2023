package com.sarayeva.cybercubebetask.mapper;

import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToDto(User user);

    User mapToUser(UserDto userDto);
    User mapToUser(@MappingTarget User user, UserDto userDto);
}
