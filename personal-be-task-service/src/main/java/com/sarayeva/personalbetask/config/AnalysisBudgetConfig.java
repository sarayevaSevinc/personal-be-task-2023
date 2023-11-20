package com.sarayeva.personalbetask.config;

import java.math.BigDecimal;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("analysis.budget")
public class AnalysisBudgetConfig {
  private BigDecimal typeFirst;
  private BigDecimal typeSecond;
}
