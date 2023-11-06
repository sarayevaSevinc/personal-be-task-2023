package com.sarayeva.cybercubebetask.mapper;

import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.ProfileResponseDto;
import com.sarayeva.cybercubebetask.dto.ProfileRequestDto;
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
