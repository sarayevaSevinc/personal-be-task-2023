package com.sarayeva.personalbetask.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private Integer code;
    private String message;
}
