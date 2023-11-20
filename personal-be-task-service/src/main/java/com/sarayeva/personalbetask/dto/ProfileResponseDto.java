package com.sarayeva.personalbetask.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileResponseDto {

  private Long id;
  private String name;
  private String surname;
  private BigDecimal budget;
}
