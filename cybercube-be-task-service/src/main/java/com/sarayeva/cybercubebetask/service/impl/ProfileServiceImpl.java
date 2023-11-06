package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.ProfileRequestDto;
import com.sarayeva.cybercubebetask.dto.ProfileResponseDto;
import com.sarayeva.cybercubebetask.exception.ProfileNotFoundException;
import com.sarayeva.cybercubebetask.mapper.ProfileMapper;
import com.sarayeva.cybercubebetask.repository.ProfileRepository;
import com.sarayeva.cybercubebetask.service.ProfileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

  private final ProfileRepository profileRepository;

  @Override
  public ProfileResponseDto saveProfile(ProfileRequestDto profileDto) {
    log.info("save new profile request: {}", profileDto);
    Profile profile = ProfileMapper.INSTANCE.toProfile(profileDto);
    return ProfileMapper.INSTANCE.toDto(profileRepository.save(profile));
  }

  @Override
  public ProfileResponseDto retrieveProfile(Long profileId) {
    log.info("retrieveProfile profile with profileId: {}", profileId);
    Profile profile = findProfile(profileId);
    return ProfileMapper.INSTANCE.toDto(profile);
  }

  @Override
  public Profile findProfile(Long profileIdd) {
    return profileRepository.findProfileById(profileIdd)
        .orElseThrow(ProfileNotFoundException::new);
  }

  @Override
  public List<Profile> findAllProfilesByIds(List<Long> ids) {
    return profileRepository.findAllProfileByIds(ids);
  }

  @Override
  public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, Long profileId) {
    log.info("update profile request: {}", profileRequestDto);
    Profile profile = findProfile(profileId);
    profile = ProfileMapper.INSTANCE.toProfile(profile, profileRequestDto);
    return ProfileMapper.INSTANCE.toDto(profileRepository.save(profile));
  }

  @Override
  public void updateProfile(Profile profile) {
    profileRepository.save(profile);
  }
}
