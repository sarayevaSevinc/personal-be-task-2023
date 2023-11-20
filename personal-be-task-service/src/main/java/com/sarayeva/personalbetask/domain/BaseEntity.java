package com.sarayeva.personalbetask.domain;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
  @CreationTimestamp
  private LocalDateTime createdTime;
  @CurrentTimestamp
  private LocalDateTime updatedTime;
}
