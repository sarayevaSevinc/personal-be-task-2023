package com.sarayeva.cybercubebetask.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "profiles")
public class Profile extends BaseEntity {

  private static final long serialVersionUID = 7156526077883281623L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String surname;
  private BigDecimal budget;
  @CreationTimestamp
  private LocalDateTime createdTime;
  @CurrentTimestamp
  private LocalDateTime updatedTime;
}
