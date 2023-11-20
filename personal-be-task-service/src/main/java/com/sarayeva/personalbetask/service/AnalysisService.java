package com.sarayeva.personalbetask.service;

import com.sarayeva.personalbetask.dto.AnalysisResponseDto;
import com.sarayeva.personalbetask.dto.AnalysisRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AnalysisService {

  AnalysisResponseDto saveAnalysis(AnalysisRequestDto analysisDto, Long profileId);

  AnalysisResponseDto getAnalysis(Long profileId, Long analysisId);

  List<AnalysisResponseDto> getAnalyses(Long profileId, Pageable pageable);
}
