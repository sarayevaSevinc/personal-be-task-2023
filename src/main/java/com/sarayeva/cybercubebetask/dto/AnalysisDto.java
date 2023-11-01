package com.sarayeva.cybercubebetask.dto;

import com.sarayeva.cybercubebetask.domain.User;
import com.sarayeva.cybercubebetask.enums.AnalysisType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalysisDto  {
    private AnalysisType type;
    private List<User> viewers;
    private String hiddenInfo;
}
