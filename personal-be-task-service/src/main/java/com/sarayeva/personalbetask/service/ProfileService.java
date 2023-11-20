package com.sarayeva.personalbetask.service;

import com.sarayeva.personalbetask.domain.Profile;
import com.sarayeva.personalbetask.dto.ProfileRequestDto;
import com.sarayeva.personalbetask.dto.ProfileResponseDto;
import java.util.List;

public interface ProfileService {

  ProfileResponseDto saveProfile(ProfileRequestDto profileDto);

  ProfileResponseDto retrieveProfile(Long profileId);

  Profile findProfile(Long profileId);

  List<Profile> findAllProfilesByIds(List<Long> ids);

  ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long profileId);

  void updateProfile(Profile profile);
}
