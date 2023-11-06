package com.sarayeva.cybercubebetask.util;

import com.sarayeva.cybercubebetask.constant.AnalysisType;
import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.AnalysisRequestDto;
import com.sarayeva.cybercubebetask.dto.AnalysisResponseDto;
import com.sarayeva.cybercubebetask.dto.ProfileRequestDto;
import java.math.BigDecimal;
import java.util.List;

public class Utility {

  public static ProfileRequestDto createProfileRequestDto() {
    return ProfileRequestDto.builder()
        .name("test name")
        .surname("test surname")
        .budget(BigDecimal.valueOf(1000))
        .build();
  }

  public static Profile createProfile(Long profileId) {
    Profile profile = new Profile();
    profile.setName("test name");
    profile.setSurname("test surname");
    profile.setBudget(BigDecimal.valueOf(1000));
    profile.setId(profileId);
    return profile;
  }

  public static Analysis createAnalysis() {
    Analysis analysis = new Analysis();
    analysis.setId(1L);
    analysis.setType(AnalysisType.FIRST);
    analysis.setOwner(createProfile(1L));
    analysis.setViewers(List.of(createProfile(5L)));
    analysis.setHiddenInfo("test hidden info");
    return analysis;
  }

  public static AnalysisResponseDto createAnalysisResponseDto() {
    return AnalysisResponseDto.builder()
        .owner(1L)
        .viewers(List.of(5L))
        .hiddenInfo("test hidden info")
        .type(AnalysisType.FIRST)
        .build();
  }

  public static AnalysisRequestDto createAnalysisRequestDto() {
    return AnalysisRequestDto.builder()
        .viewers(List.of(5L))
        .hiddenInfo("test hidden info")
        .type(AnalysisType.FIRST)
        .build();
  }
}
