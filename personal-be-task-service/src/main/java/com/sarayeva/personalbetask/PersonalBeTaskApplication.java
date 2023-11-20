package com.sarayeva.personalbetask;

import com.sarayeva.personalbetask.config.AnalysisBudgetConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AnalysisBudgetConfig.class)
public class PersonalBeTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(PersonalBeTaskApplication.class, args);
  }

}
