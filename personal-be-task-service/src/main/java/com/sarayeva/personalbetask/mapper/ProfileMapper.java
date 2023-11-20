package com.sarayeva.personalbetask.mapper;

import com.sarayeva.personalbetask.domain.Profile;
import com.sarayeva.personalbetask.dto.ProfileResponseDto;
import com.sarayeva.personalbetask.dto.ProfileRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {

  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

  ProfileResponseDto toDto(Profile profile);

  Profile toProfile(ProfileRequestDto dto);

  Profile toProfile(@MappingTarget Profile profile, ProfileRequestDto dto);
}
