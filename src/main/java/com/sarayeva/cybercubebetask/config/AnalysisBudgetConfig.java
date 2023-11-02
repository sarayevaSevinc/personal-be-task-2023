package com.sarayeva.cybercubebetask.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration @Data
@ConfigurationProperties("analysis.budget")
public class AnalysisBudgetConfig {
    private BigDecimal typeFirst;
    private BigDecimal typeSecond;
}
