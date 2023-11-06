package com.sarayeva.cybercubebetask.controller;

import static com.sarayeva.cybercubebetask.constant.Constants.PROFILE_ID_HEADER;

import com.sarayeva.cybercubebetask.dto.ProfileResponseDto;
import com.sarayeva.cybercubebetask.dto.ProfileRequestDto;
import com.sarayeva.cybercubebetask.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-management/profiles")
@RequiredArgsConstructor
public class ProfileController {

  private final ProfileService profileService;

  @PostMapping
  public ResponseEntity<ProfileResponseDto> saveProfile(
      @RequestBody @Valid ProfileRequestDto profileRequest) {
    return new ResponseEntity<>(profileService.saveProfile(profileRequest), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<ProfileResponseDto> getProfile(
      @RequestHeader(PROFILE_ID_HEADER) Long profileId) {
    return new ResponseEntity<>(profileService.retrieveProfile(profileId), HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<ProfileResponseDto> updateProfile(@RequestHeader(PROFILE_ID_HEADER) Long profileId,
      @RequestBody ProfileRequestDto profileRequest) {
    return new ResponseEntity<>(profileService.updateProfile(profileRequest, profileId),
        HttpStatus.CREATED);
  }
}
