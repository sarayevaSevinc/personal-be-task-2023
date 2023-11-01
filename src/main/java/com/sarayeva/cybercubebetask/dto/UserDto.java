package com.sarayeva.cybercubebetask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;
    private String surname;
    private BigDecimal budget;
}
