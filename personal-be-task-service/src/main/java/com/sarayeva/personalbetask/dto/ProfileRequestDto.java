package com.sarayeva.personalbetask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileRequestDto {

  @NotBlank
  private String name;
  @NotBlank
  private String surname;
  @NotNull
  private BigDecimal budget;
}
