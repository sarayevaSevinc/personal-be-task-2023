package com.sarayeva.personalbetask.mapper;

import com.sarayeva.personalbetask.domain.Analysis;
import com.sarayeva.personalbetask.domain.Profile;
import com.sarayeva.personalbetask.dto.AnalysisRequestDto;
import com.sarayeva.personalbetask.dto.AnalysisResponseDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public abstract class AnalysisMapper {


  @Mapping(target = "viewers", source = "viewersList")
  @Mapping(target = "owner", source = "owner")
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdTime", ignore = true)
  @Mapping(target = "updatedTime", ignore = true)
  public abstract Analysis toAnalysis(AnalysisRequestDto analysisDto, List<Profile> viewersList,
      Profile owner);

  @Mapping(target = "owner", source = "owner", qualifiedByName = "fromProfileToProfileId")
  @Mapping(target = "viewers", source = "viewers", qualifiedByName = "fromProfileToProfileId")
  public abstract AnalysisResponseDto toAnalysisDto(Analysis analysis);

  public AnalysisResponseDto toAnalysisDto(Analysis analysis, Profile profile) {
    AnalysisResponseDto analysisResponseDto = toAnalysisDto(analysis);
    if (!checkOwner(analysis, profile)) {
      analysisResponseDto.setHiddenInfo(null);
    }
    return analysisResponseDto;
  }

  public List<AnalysisResponseDto> toAnalysisDtoList(List<Analysis> analyses, Profile profile) {
    return analyses.stream().map(analysis -> toAnalysisDto(analysis, profile))
        .collect(Collectors.toList());
  }

  private boolean checkOwner(Analysis analysis, Profile profile) {
    return Objects.equals(profile.getId(), analysis.getOwner().getId());
  }

  @Named("fromProfileToProfileId")
  Long mapProfileToProfileId(Profile profile) {
    return profile.getId();
  }
}
