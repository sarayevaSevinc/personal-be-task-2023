package com.sarayeva.cybercubebetask.mapper;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.AnalysisByOwnerResponseDto;
import com.sarayeva.cybercubebetask.dto.AnalysisByViewerResponseDto;
import com.sarayeva.cybercubebetask.dto.CreateAnalysisRequestDto;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnalysisMapper {


    @Mapping(target = "analysis.viewers", source = "viewersList")
    public abstract Analysis mapToAnalysis(@MappingTarget Analysis analysis, CreateAnalysisRequestDto analysisDto, List<User> viewersList);

    @Mapping(target = "owner", source = "owner", qualifiedByName = "fromUserToUserId")
    @Mapping(target = "viewers", source = "viewers", qualifiedByName = "fromUserToUserId")
    public abstract AnalysisByOwnerResponseDto mapToAnalysisByOwnerResponseDto(Analysis analysis);

    @Mapping(target = "owner", source = "owner", qualifiedByName = "fromUserToUserId")
    @Mapping(target = "viewers", source = "viewers", qualifiedByName = "fromUserToUserId")
    public abstract AnalysisByViewerResponseDto mapToAnalysisByViewerResponseDto(Analysis analysis);

    public abstract List<AnalysisByOwnerResponseDto> mapToAnalysisByOwnerResponseDtoList(List<Analysis> analyses);

    public abstract List<AnalysisByViewerResponseDto> mapToAnalysisByViewerResponseDtoList(List<Analysis> analyses);

    @Named("fromUserToUserId")
    Long mapUserToUserId(User user) {
        return user.getId();
    }
}
