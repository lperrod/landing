package com.cmpl.landing.pages;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cmpl.landing.context.ContextHolder;

@Controller
@RequestMapping(value = "/pages")
public class PageController {

  private final ContextHolder contextHolder;

  @Autowired
  public PageController(ContextHolder contextHolder) {
    this.contextHolder = contextHolder;
  }

  @GetMapping(value = "/{folderName}/{pageName}")
  public ModelAndView print(@PathVariable("pageName") String pageName, @PathVariable("folderName") String folderName) {
    ModelAndView model = new ModelAndView(folderName + File.separator + pageName);
    model.addObject("mediaPath", contextHolder.getMediaBasePath());
    return model;
  }

}
