package com.sarayeva.cybercubebetask.controller;

import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.dto.UserDto;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import com.sarayeva.cybercubebetask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analyses/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;


    @PostMapping
    public ResponseEntity<AnalysisDto> createAnalysis(@RequestHeader("id") Long id,
                                                      @RequestBody AnalysisDto analysisRequest) {
        AnalysisDto analysisDto = analysisService.saveAnalysis(analysisRequest, id);
        return ResponseEntity.ok(analysisDto);
    }
}
