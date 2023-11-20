package com.sarayeva.personalbetask.dto;

import com.sarayeva.personalbetask.constant.AnalysisType;
import com.sarayeva.personalbetask.validation.ValidAnalysisType;
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
    private String type;
    private List<Long> viewers;
    @NotBlank
    private String hiddenInfo;
}
