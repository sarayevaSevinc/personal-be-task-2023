package com.sarayeva.cybercubebetask.controller;

import com.sarayeva.cybercubebetask.dto.AnalysisByOwnerResponseDto;
import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.dto.CreateAnalysisRequestDto;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analyses/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;


    @PostMapping
    public ResponseEntity<AnalysisByOwnerResponseDto> createAnalysis(@RequestHeader("user-id") Long id,
                                                                     @RequestBody CreateAnalysisRequestDto analysisRequest) {
        AnalysisByOwnerResponseDto analysisDto = analysisService.saveAnalysis(analysisRequest, id);
        return ResponseEntity.ok(analysisDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalysisDto> getAnalysis(@RequestHeader("user-id") Long userId, @PathVariable("id") Long analysisId) {
        AnalysisDto analysisDto = analysisService.getAnalysis(userId, analysisId);
        return ResponseEntity.ok(analysisDto);
    }
    @GetMapping("/list")
    public ResponseEntity<List<AnalysisDto>> getAnalysesList(@RequestHeader("user-id") Long userId) {
        List<AnalysisDto> analysisDtoList = analysisService.getAnalysisList(userId);
        return ResponseEntity.ok(analysisDtoList);
    }
}
