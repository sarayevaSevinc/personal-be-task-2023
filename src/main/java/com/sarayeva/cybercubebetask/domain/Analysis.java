package com.sarayeva.cybercubebetask.domain;

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
    @SequenceGenerator(name = "analysis_seq", sequenceName = "analysis_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analysis_seq")
    private long id;
    private String type;
    @OneToOne
    private User owner;
    @OneToMany
    private List<User> viewers;
    private String hiddenInfo;

}
