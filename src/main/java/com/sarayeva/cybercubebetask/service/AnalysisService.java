package com.sarayeva.cybercubebetask.service;

import com.sarayeva.cybercubebetask.dto.AnalysisByOwnerResponseDto;
import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.dto.CreateAnalysisRequestDto;

import java.util.List;

public interface AnalysisService {
     AnalysisByOwnerResponseDto saveAnalysis (CreateAnalysisRequestDto analysisDto, Long id);

     AnalysisDto getAnalysis(Long userId, Long analysisId);
     List<AnalysisDto> getAnalysisList(Long userId);
}
