package com.sarayeva.cybercubebetask.controller;

import static com.sarayeva.cybercubebetask.constant.Constants.PROFILE_ID_HEADER;

import com.sarayeva.cybercubebetask.dto.AnalysisRequestDto;
import com.sarayeva.cybercubebetask.dto.AnalysisResponseDto;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/analysis-management/analyses")
@RequiredArgsConstructor
@Validated
public class AnalysisController {

  private final AnalysisService analysisService;

  @PostMapping
  public ResponseEntity<AnalysisResponseDto> saveAnalysis(
      @RequestHeader(PROFILE_ID_HEADER) Long profileId,
      @RequestBody @Valid AnalysisRequestDto analysisRequest) {
    return new ResponseEntity<>(analysisService.saveAnalysis(analysisRequest, profileId),
        HttpStatus.CREATED);
  }

  @GetMapping("/{analysis-id}")
  public ResponseEntity<AnalysisResponseDto> getAnalysis(
      @RequestHeader(PROFILE_ID_HEADER) Long profileId,
      @PathVariable("analysis-id") Long analysisId) {
    return new ResponseEntity<>(analysisService.getAnalysis(profileId, analysisId), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<AnalysisResponseDto>> getAnalyses(
      @RequestHeader(PROFILE_ID_HEADER) Long profileId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "2") int sizePerPage) {
    Pageable pageable = PageRequest.of(page, sizePerPage);

    return new ResponseEntity<>(analysisService.getAnalyses(profileId, pageable), HttpStatus.OK);
  }
}
