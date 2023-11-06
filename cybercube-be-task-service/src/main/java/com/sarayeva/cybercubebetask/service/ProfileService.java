package com.sarayeva.cybercubebetask.service;

import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.ProfileRequestDto;
import com.sarayeva.cybercubebetask.dto.ProfileResponseDto;
import java.util.List;

public interface ProfileService {

  ProfileResponseDto saveProfile(ProfileRequestDto profileDto);

  ProfileResponseDto retrieveProfile(Long profileId);

  Profile findProfile(Long profileId);

  List<Profile> findAllProfilesByIds(List<Long> ids);

  ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long profileId);

  void updateProfile(Profile profile);
}
