package com.sarayeva.cybercubebetask.dto;

import com.sarayeva.cybercubebetask.constant.AnalysisType;
import com.sarayeva.cybercubebetask.validation.ValidAnalysisType;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnalysisRequestDto {
    @ValidAnalysisType(enumClass = AnalysisType.class)
    private AnalysisType type;
    private List<Long> viewers;
    @NotBlank
    private String hiddenInfo;
}
