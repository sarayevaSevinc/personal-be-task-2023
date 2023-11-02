package com.sarayeva.cybercubebetask.dto;

import com.sarayeva.cybercubebetask.enums.AnalysisType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnalysisByOwnerResponseDto extends AnalysisDto{
    private AnalysisType type;
    private Long owner;
    private List<Long> viewers;
    private String hiddenInfo;
}
