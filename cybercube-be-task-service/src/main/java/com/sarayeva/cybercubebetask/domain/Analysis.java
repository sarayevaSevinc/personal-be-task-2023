package com.sarayeva.cybercubebetask.domain;

import com.sarayeva.cybercubebetask.constant.AnalysisType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "analyses")
public class Analysis extends BaseEntity {

  private static final long serialVersionUID = 7156526077883281623L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private AnalysisType type;
  @OneToOne
  private Profile owner;
  @OneToMany
  private List<Profile> viewers;
  private String hiddenInfo;
  @CreationTimestamp
  private LocalDateTime createdTime;
  @CurrentTimestamp
  private LocalDateTime updatedTime;


}
