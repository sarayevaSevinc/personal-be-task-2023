package com.sarayeva.cybercubebetask.dto;

import com.sarayeva.cybercubebetask.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalysisDto  {
    private String type;
    private User owner;
    private List<User> viewers;
    private String hiddenInfo;

}
