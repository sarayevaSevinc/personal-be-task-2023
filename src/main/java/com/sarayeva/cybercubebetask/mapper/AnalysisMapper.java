package com.sarayeva.cybercubebetask.mapper;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnalysisMapper {
    AnalysisMapper INSTANCE = Mappers.getMapper(AnalysisMapper.class);

    AnalysisDto mapToDto(Analysis analysis);

    @Mapping(target = "analysis.owner", source = "owner")
    Analysis mapToAnalysis(@MappingTarget Analysis analysis,  AnalysisDto analysisDto, User owner);
}
