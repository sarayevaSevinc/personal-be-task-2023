package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.config.AnalysisBudgetConfig;
import com.sarayeva.cybercubebetask.constant.AnalysisType;
import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.AnalysisRequestDto;
import com.sarayeva.cybercubebetask.dto.AnalysisResponseDto;
import com.sarayeva.cybercubebetask.exception.AnalysisNotFoundException;
import com.sarayeva.cybercubebetask.exception.BadOperationException;
import com.sarayeva.cybercubebetask.exception.InsufficientBudgetException;
import com.sarayeva.cybercubebetask.exception.ViewerNotFoundException;
import com.sarayeva.cybercubebetask.mapper.AnalysisMapper;
import com.sarayeva.cybercubebetask.repository.AnalysisRepository;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import com.sarayeva.cybercubebetask.service.ProfileService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisServiceImpl implements AnalysisService {

  private final AnalysisRepository analysisRepository;
  private final AnalysisMapper analysisMapper;
  private final ProfileService profileService;
  private final AnalysisBudgetConfig analysisBudgetConfig;

  @Override
  @Transactional
  public AnalysisResponseDto saveAnalysis(AnalysisRequestDto analysisDto, Long profileId) {
    log.info("save new analysis request:{}", analysisDto);
    checkViewers(analysisDto.getViewers(), profileId);
    Profile profile = profileService.findProfile(profileId);
    BigDecimal budgetForAnalysis = getBudgetForAnalysis(analysisDto.getType());
    if (!checkBudgetForAnalysis(profile, budgetForAnalysis)) {
      throw new InsufficientBudgetException();
    }
    Analysis analysis = createAnalysis(profile, analysisDto);
    profile.setBudget(profile.getBudget().subtract(budgetForAnalysis));
    profileService.updateProfile(profile);
    Analysis save = analysisRepository.save(analysis);
    log.info("test={}",save );
    log.info("saved new analysis with id={}, and userId={}", save.getId(), profileId);
    return analysisMapper.toAnalysisDto(analysis);
  }

  @Override
  public AnalysisResponseDto getAnalysis(Long profileId, Long analysisId) {
    log.info("get analysis with profileId={}, analysisId={}", profileId, analysisId);
    Profile profile = profileService.findProfile(profileId);
    Analysis analysis = analysisRepository.findAnalysisByIdAndProfile(analysisId,
            profileId)
        .orElseThrow(AnalysisNotFoundException::new);
    return analysisMapper.toAnalysisDto(analysis, profile);
  }

  @Override
  public List<AnalysisResponseDto> getAnalyses(Long profileId, Pageable pageable) {
    log.info("get analyses with profileId={}", profileId);
    Profile profile = profileService.findProfile(profileId);
    List<Analysis> analyses = analysisRepository.findAnalysesByOwnerOrViewersContainingOrderByUpdatedTimeDesc(
        profile,
        profile, pageable);
    return analysisMapper.toAnalysisDtoList(analyses, profile);
  }

  private Analysis createAnalysis(Profile profile, AnalysisRequestDto analysisDto) {
    List<Profile> viewersList = profileService.findAllProfilesByIds(analysisDto.getViewers());
    checkViewerList(viewersList, analysisDto.getViewers());
    return analysisMapper.toAnalysis(analysisDto, viewersList, profile);
  }

  private void checkViewers(List<Long> viewerIdList, Long profileId) {
    if (viewerIdList!= null && viewerIdList.contains(profileId)) {
      throw new BadOperationException();
    }
  }

  private void checkViewerList(List<Profile> viewerList, List<Long> viewerIdList) {
    if (viewerIdList!= null && viewerList.size() != viewerIdList.size()) {
      throw new ViewerNotFoundException();
    }
  }

  private BigDecimal getBudgetForAnalysis(AnalysisType analysisType) {
    // getting necessary budget for creating analysis based on its type
    if (analysisType == AnalysisType.FIRST) {
      return analysisBudgetConfig.getTypeFirst();
    }
    return analysisBudgetConfig.getTypeSecond();
  }

  private boolean checkBudgetForAnalysis(Profile profile, BigDecimal budget) {
    // checking if user has enough budget to trigger new analysis
    return profile.getBudget().compareTo(budget) >= 0;
  }
}
