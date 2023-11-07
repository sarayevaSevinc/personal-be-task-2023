package com.sarayeva.cybercubebetask.service.impl;

import static com.sarayeva.cybercubebetask.util.Utility.createAnalysis;
import static com.sarayeva.cybercubebetask.util.Utility.createAnalysisRequestDto;
import static com.sarayeva.cybercubebetask.util.Utility.createAnalysisResponseDto;
import static com.sarayeva.cybercubebetask.util.Utility.createProfile;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.sarayeva.cybercubebetask.config.AnalysisBudgetConfig;
import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.Profile;
import com.sarayeva.cybercubebetask.dto.AnalysisRequestDto;
import com.sarayeva.cybercubebetask.dto.AnalysisResponseDto;
import com.sarayeva.cybercubebetask.exception.AnalysisNotFoundException;
import com.sarayeva.cybercubebetask.exception.InsufficientBudgetException;
import com.sarayeva.cybercubebetask.exception.ViewerNotFoundException;
import com.sarayeva.cybercubebetask.mapper.AnalysisMapper;
import com.sarayeva.cybercubebetask.repository.AnalysisRepository;
import com.sarayeva.cybercubebetask.service.ProfileService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class AnalysisServiceTest {

  @Mock
  private AnalysisRepository analysisRepositoryMock;
  @Mock
  private AnalysisMapper analysisMapperMock;
  @Mock
  private ProfileService profileServiceMock;
  @InjectMocks
  private AnalysisServiceImpl analysisServiceUnderTest;

  @Mock
  private AnalysisBudgetConfig analysisBudgetConfigMock;
  private final Profile profile = createProfile(1L);

  @BeforeEach
  public void setup() {
    when(profileServiceMock.findProfile(any())).thenReturn(profile);
  }

  @Test
  void getAnalysis_success_returnAnalysis() {
    //setup
    Analysis analysis = createAnalysis();
    AnalysisResponseDto analysisResponseDto = createAnalysisResponseDto();
    when(analysisRepositoryMock.findAnalysisByIdAndProfile(any(), any())).thenReturn(
        Optional.of(analysis));
    when(analysisMapperMock.toAnalysisDto(any(), any())).thenReturn(analysisResponseDto);
    // run
    AnalysisResponseDto result = analysisServiceUnderTest.getAnalysis(1L, 2L);
    // verify
    Assertions.assertEquals(analysis.getOwner().getId(), result.getOwner());
    Assertions.assertEquals(analysis.getHiddenInfo(), result.getHiddenInfo());
  }

  @Test
  void getAnalysis_error_throwAnalysisNotFoundException() {
    //setup
    when(analysisRepositoryMock.findAnalysisByIdAndProfile(any(),
        any())).thenThrow(new AnalysisNotFoundException());
    // verify
    Assertions.assertThrows(AnalysisNotFoundException.class,
        () -> analysisServiceUnderTest.getAnalysis(1L, 2L));
  }

  @Test
  void getAnalyses_error_throwViewerNotFoundException() {
    // setup
    Analysis analysis = createAnalysis();
    Pageable pageable = PageRequest.of(0, 2);
    AnalysisResponseDto analysisResponseDto = createAnalysisResponseDto();
    when(analysisRepositoryMock.findAnalysesByOwnerOrViewersContainingOrderByUpdatedTimeDesc(any(),
        any(),
        any())).thenReturn(List.of(analysis));
    when(analysisMapperMock.toAnalysisDtoList(any(), any())).thenReturn(
        List.of(analysisResponseDto));
    // run
    List<AnalysisResponseDto> analyses = analysisServiceUnderTest.getAnalyses(1L, pageable);
    //verify
    Assertions.assertNotNull(analyses);
    Assertions.assertEquals(analyses.size(), 1);
    Assertions.assertEquals(analysis.getType(), analyses.get(0).getType());
  }

  @Test
  void saveAnalysis_success_returnAnalysis() {
    // setup
    Analysis analysis = createAnalysis();
    AnalysisRequestDto analysisRequestDto = createAnalysisRequestDto();
    AnalysisResponseDto analysisResponseDto = createAnalysisResponseDto();
    when(profileServiceMock.findAllProfilesByIds(anyList())).thenReturn(List.of(createProfile(3L)));
    when(analysisMapperMock.toAnalysis(any(), anyList(), any())).thenReturn(createAnalysis());
    doNothing().when(profileServiceMock).updateProfile(any());
    when(analysisBudgetConfigMock.getTypeFirst()).thenReturn(BigDecimal.valueOf(50));
    when(analysisRepositoryMock.save(any())).thenReturn(analysis);
    when(analysisMapperMock.toAnalysisDto(analysis)).thenReturn(analysisResponseDto);
    // run
    AnalysisResponseDto result = analysisServiceUnderTest.saveAnalysis(
        analysisRequestDto, 1L);
    // verify
    Assertions.assertEquals(analysisRequestDto.getType(), result.getType());
    Assertions.assertEquals(1L, result.getOwner());
    Assertions.assertEquals(analysisRequestDto.getHiddenInfo(), result.getHiddenInfo());
  }

  @Test
  void saveAnalysis_error_throwInsufficientBudgetException() {
    // setup
    AnalysisRequestDto analysisRequestDto = createAnalysisRequestDto();
    when(analysisBudgetConfigMock.getTypeFirst()).thenReturn(BigDecimal.valueOf(1200));
    // assert
    Assertions.assertThrows(InsufficientBudgetException.class,
        () -> analysisServiceUnderTest.saveAnalysis(
            analysisRequestDto, 1L));
  }

  @Test
  void saveAnalysis_error_throwViewerNotFoundException() {
    //setup
    AnalysisRequestDto analysisRequestDto = createAnalysisRequestDto();
    when(analysisBudgetConfigMock.getTypeFirst()).thenReturn(BigDecimal.valueOf(500));
    when(profileServiceMock.findAllProfilesByIds(anyList())).thenReturn(new ArrayList<>());
    // assert
    Assertions.assertThrows(ViewerNotFoundException.class,
        () -> analysisServiceUnderTest.saveAnalysis(
            analysisRequestDto, 1L));
  }
}