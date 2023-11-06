package com.sarayeva.cybercubebetask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sarayeva.cybercubebetask.constant.AnalysisType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalysisResponseDto {

  private AnalysisType type;
  private Long owner;
  private List<Long> viewers;
  private String hiddenInfo;
}
