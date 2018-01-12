package com.cmpl.landing.template;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import com.cmpl.landing.context.ContextHolder;

@Configuration
@PropertySource("classpath:/templates.properties")
public class TemplateResolverConfiguration {

  @Autowired
  ContextHolder contextHolder;

  @Autowired
  SpringTemplateEngine templateEngine;

  @PostConstruct
  public void extension() {
    FileTemplateResolver resolver = new FileTemplateResolver();
    resolver.setPrefix(contextHolder.getTemplateBasePath());
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    resolver.setOrder(templateEngine.getTemplateResolvers().size());
    resolver.setCacheable(false);
    templateEngine.addTemplateResolver(resolver);
  }

}
