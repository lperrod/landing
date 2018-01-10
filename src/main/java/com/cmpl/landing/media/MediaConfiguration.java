package com.cmpl.landing.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cmpl.landing.context.ContextHolder;

@Configuration
public class MediaConfiguration {

  @Autowired
  ContextHolder contextHolder;

  @Bean
  MediaService mediaService() {
    return new MediaServiceImpl(contextHolder);
  }

}
