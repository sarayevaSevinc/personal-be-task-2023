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
    public ResponseEntity<UserDto> createAnalysis(@RequestBody AnalysisDto analysisRequest) {
        UserDto user = analysisService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }
}
