package com.sarayeva.personalbetask.service.impl;

import static com.sarayeva.personalbetask.util.Utility.createProfile;
import static com.sarayeva.personalbetask.util.Utility.createProfileRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sarayeva.personalbetask.domain.Profile;
import com.sarayeva.personalbetask.dto.ProfileResponseDto;
import com.sarayeva.personalbetask.dto.ProfileRequestDto;
import com.sarayeva.personalbetask.exception.ProfileNotFoundException;
import com.sarayeva.personalbetask.repository.ProfileRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

  @Mock
 private ProfileRepository profileRepositoryMock;
  @InjectMocks
  private ProfileServiceImpl profileServiceUnderTest;

  @Test
  void saveProfile_success_returnProfile() {
    // setup
    ProfileRequestDto profileRequestDto = createProfileRequestDto();
    Profile profile = createProfile(1L);
    when(profileRepositoryMock.save(any())).thenReturn(profile);
    // run
    ProfileResponseDto result = profileServiceUnderTest.saveProfile(profileRequestDto);
    // verify
    Assertions.assertEquals(profile.getName(), result.getName());
    Assertions.assertEquals(profile.getSurname(), result.getSurname());
    Assertions.assertEquals(profile.getBudget(), result.getBudget());
  }

  @Test
  void retrieveProfile_success_returnProfile() {
    // setup
    Profile profile = createProfile(1L);
    when(profileRepositoryMock.findProfileById(any())).thenReturn(Optional.ofNullable(profile));
    // run
    ProfileResponseDto result = profileServiceUnderTest.retrieveProfile(1L);
    // verify
    Assertions.assertNotNull(profile);
    Assertions.assertEquals(profile.getName(), result.getName());
    Assertions.assertEquals(profile.getSurname(), result.getSurname());
    Assertions.assertEquals(profile.getBudget(), result.getBudget());
  }

  @Test
  void retrieveProfile_error_throwProfileNotFoundException() {
    // setup
    when(profileRepositoryMock.findProfileById(any())).thenReturn(Optional.empty());
    // verify
    Assertions.assertThrows(ProfileNotFoundException.class, ()->profileServiceUnderTest.retrieveProfile(1L));
  }

  @Test
  void updateProfile_success_returnProfile() {
    // setup
    ProfileRequestDto profileRequestDto = createProfileRequestDto();
    Profile profile = createProfile(1L);
    when(profileRepositoryMock.findProfileById(any())).thenReturn(Optional.of(profile));
    when(profileRepositoryMock.save(profile)).thenReturn(profile);
    profile.setName("test name 2");
    // run
    ProfileResponseDto result = profileServiceUnderTest.updateProfile(profileRequestDto, 1L);
    // verify
    Assertions.assertEquals(profile.getName(), result.getName());
    Assertions.assertEquals(profile.getSurname(), result.getSurname());
    Assertions.assertEquals(profile.getBudget(), result.getBudget());
  }


}