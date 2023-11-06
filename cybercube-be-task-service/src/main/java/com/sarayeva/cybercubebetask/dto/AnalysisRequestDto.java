package com.sarayeva.cybercubebetask.dto;

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
public class AnalysisRequestDto {

  private AnalysisType type;
  private List<Long> viewers;
  private String hiddenInfo;
}
