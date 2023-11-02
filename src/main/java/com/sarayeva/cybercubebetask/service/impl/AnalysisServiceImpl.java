package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.config.AnalysisBudgetConfig;
import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.AnalysisByOwnerResponseDto;
import com.sarayeva.cybercubebetask.dto.AnalysisByViewerResponseDto;
import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.dto.CreateAnalysisRequestDto;
import com.sarayeva.cybercubebetask.enums.AnalysisType;
import com.sarayeva.cybercubebetask.exception.AnalysisNotFoundException;
import com.sarayeva.cybercubebetask.exception.InsufficientBudgetException;
import com.sarayeva.cybercubebetask.mapper.AnalysisMapper;
import com.sarayeva.cybercubebetask.repository.AnalysisRepository;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import com.sarayeva.cybercubebetask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisRepository analysisRepository;
    private final AnalysisMapper analysisMapper;
    private final UserService userService;
    private final AnalysisBudgetConfig analysisBudgetConfig;


    @Override
    public AnalysisByOwnerResponseDto saveAnalysis(CreateAnalysisRequestDto analysisDto, Long userId) {
        User owner = userService.getUserInternal(userId);
        Analysis analysis = createAnalysis(owner, analysisDto);
        Analysis save = analysisRepository.save(analysis);
        log.info("saved new analysis with id={}, and userId={}", save.getId(), userId);
        return analysisMapper.mapToAnalysisByOwnerResponseDto(save);
    }

    @Override
    public AnalysisDto getAnalysis(Long userId, Long analysisId) {
        User userById = userService.getUserInternal(userId);
        AnalysisByOwnerResponseDto analysisByOwner = getAnalysisByOwner(userById, analysisId);
        if (analysisByOwner == null) {
            return getAnalysisByViewer(userById, analysisId);
        }
        return analysisByOwner;
    }

    @Override
    public List<AnalysisDto> getAnalysisList(Long userId) {
        User userById = userService.getUserInternal(userId);
        List<AnalysisDto> resultList = new ArrayList<>();
        resultList.addAll(getAnalysesListByOwner(userById));
        resultList.addAll(getAnalysesListByViewer(userById));
        return resultList;
    }

    private Analysis createAnalysis(User user, CreateAnalysisRequestDto analysisDto) {
        log.info("creating analysis with type={}, viewers={}", analysisDto.getType(), analysisDto.getViewers());
        BigDecimal budgetForAnalysis = getBudgetForAnalysis(analysisDto.getType());
        if (!checkBudgetForAnalysis(user, budgetForAnalysis)) {
            throw new InsufficientBudgetException();
        }
        Analysis analysis = new Analysis();
        List<User> viewersList = analysisDto.getViewers().stream().map(userService::getUserInternal).collect(Collectors.toList());
        analysis = analysisMapper.mapToAnalysis(analysis, analysisDto, viewersList);
        analysis.setOwner(user);
        user.setBudget(user.getBudget().subtract(budgetForAnalysis));
        userService.updateUser(user);
        return analysis;
    }

    private BigDecimal getBudgetForAnalysis(AnalysisType analysisType) {
        // getting necessary budget for creating analysis based on its type
        if (analysisType == AnalysisType.FIRST) {
            return analysisBudgetConfig.getTypeFirst();
        }
        return analysisBudgetConfig.getTypeSecond();
    }

    private boolean checkBudgetForAnalysis(User user, BigDecimal budget) {
        // checking if user has enough budget to trigger new analysis
        return user.getBudget().compareTo(budget) > 0;
    }

    private AnalysisByOwnerResponseDto getAnalysisByOwner(User user, Long analysisId) {
        log.info("finding analysis by id={} and owner={}", analysisId, user.getId());
        Analysis analysis = analysisRepository.findAnalysisByIdAndOwner(analysisId, user);
        if (analysis == null) {
            return null;
        }
        return analysisMapper.mapToAnalysisByOwnerResponseDto(analysis);
    }

    private AnalysisByViewerResponseDto getAnalysisByViewer(User user, Long analysisId) {
        log.info("finding analysis by id={} and viewer={}", analysisId, user.getId());
        Analysis analysis = analysisRepository.findAnalysisByIdAndViewersIsContaining(analysisId, user);
        if (analysis == null) {
            throw new AnalysisNotFoundException();
        }
        return analysisMapper.mapToAnalysisByViewerResponseDto(analysis);
    }

    private List<AnalysisByOwnerResponseDto> getAnalysesListByOwner(User user) {
        log.info("finding analyses list by owner={}", user.getId());
        List<Analysis> analysesByOwner = analysisRepository.findAnalysesByOwner(user);
        return analysisMapper.mapToAnalysisByOwnerResponseDtoList(analysesByOwner);
    }

    private List<AnalysisByViewerResponseDto> getAnalysesListByViewer(User user) {
        log.info("finding analyses list by viewer={}", user.getId());
        List<Analysis> analysesByViewersContaining = analysisRepository.findAnalysesByViewersContaining(user);
        return analysisMapper.mapToAnalysisByViewerResponseDtoList(analysesByViewersContaining);
    }
}
