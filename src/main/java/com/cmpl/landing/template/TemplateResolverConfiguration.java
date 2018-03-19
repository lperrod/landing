package com.cmpl.landing.template;

import com.cmpl.landing.context.ContextHolder;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

@Configuration
@PropertySource("classpath:/templates.properties")
public class TemplateResolverConfiguration {

  @Autowired
  ContextHolder contextHolder;

  @Autowired
  SpringTemplateEngine templateEngine;

  @PostConstruct
  public void extension() {
    Integer order = templateEngine.getTemplateResolvers().size();
    FileTemplateResolver pagesResolver = new FileTemplateResolver();
    pagesResolver.setPrefix(contextHolder.getTemplateBasePath());
    pagesResolver.setSuffix(".html");
    pagesResolver.setTemplateMode("HTML5");
    pagesResolver.setOrder(order);
    pagesResolver.setCacheable(false);
    pagesResolver.setCheckExistence(true);
    templateEngine.addTemplateResolver(pagesResolver);

    FileTemplateResolver mobilePagesResolver = new FileTemplateResolver();
    mobilePagesResolver.setPrefix(contextHolder.getAcceleratedMobilePageTemplatePath());
    mobilePagesResolver.setSuffix(".html");
    mobilePagesResolver.setTemplateMode("Legacy HTML5");
    mobilePagesResolver.setOrder(order + 1);
    mobilePagesResolver.setCacheable(false);
    mobilePagesResolver.setCheckExistence(true);
    templateEngine.addTemplateResolver(mobilePagesResolver);
  }

}
