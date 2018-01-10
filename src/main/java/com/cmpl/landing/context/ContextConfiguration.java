package com.cmpl.landing.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/templates.properties")
public class ContextConfiguration {

  @Value("${templateBasePath}")
  String templateBasePath;

  @Value("${mediaBasePath}")
  String mediaBasePath;

  @Bean
  ContextHolder contextHolder() {
    ContextHolder contextHolder = new ContextHolder();
    contextHolder.setMediaBasePath(mediaBasePath);
    contextHolder.setTemplateBasePath(templateBasePath);
    return contextHolder;

  }

}
