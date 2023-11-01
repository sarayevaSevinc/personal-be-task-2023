package com.sarayeva.cybercubebetask.service.impl;

import com.sarayeva.cybercubebetask.domain.Analysis;
import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.dto.AnalysisDto;
import com.sarayeva.cybercubebetask.mapper.AnalysisMapper;
import com.sarayeva.cybercubebetask.repository.AnalysisRepository;
import com.sarayeva.cybercubebetask.repository.UserRepository;
import com.sarayeva.cybercubebetask.service.AnalysisService;
import com.sarayeva.cybercubebetask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisRepository analysisRepository;
    private final UserRepository userRepository;

    @Override
    public AnalysisDto saveAnalysis(AnalysisDto analysisDto, Long id) {
        User owner = userRepository.findUserById(id);
        Analysis analysis = new Analysis();
        analysis = AnalysisMapper.INSTANCE.mapToAnalysis(analysis, analysisDto, owner);
        log.info("mapped analysis ={}", analysis);
        Analysis save = analysisRepository.save(analysis);
        return AnalysisMapper.INSTANCE.mapToDto(save);
    }
}
