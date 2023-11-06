package com.sarayeva.cybercubebetask.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

@Data
public class BaseEntity implements Serializable {
  @CreationTimestamp
  private LocalDateTime createdTime;
  @CurrentTimestamp
  private LocalDateTime updatedTime;
}
