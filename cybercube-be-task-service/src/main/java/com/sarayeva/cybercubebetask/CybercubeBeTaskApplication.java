package com.sarayeva.cybercubebetask;

import com.sarayeva.cybercubebetask.config.AnalysisBudgetConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AnalysisBudgetConfig.class)
public class CybercubeBeTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(CybercubeBeTaskApplication.class, args);
  }

}
