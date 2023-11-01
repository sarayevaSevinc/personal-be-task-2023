package com.sarayeva.cybercubebetask.domain;

import com.sarayeva.cybercubebetask.enums.AnalysisType;
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
@Entity
public class Analysis implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private AnalysisType type;
    @OneToOne
    private User owner;
    @OneToMany
    private List<User> viewers;
    private String hiddenInfo;

}
