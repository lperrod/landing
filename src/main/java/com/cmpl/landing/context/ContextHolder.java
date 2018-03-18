package com.cmpl.landing.context;

public class ContextHolder {

  private String mediaBasePath;
  private String templateBasePath;
  private String acceleratedMobilePageTemplatePath;

  public String getTemplateBasePath() {
    return templateBasePath;
  }

  public void setTemplateBasePath(String templateBasePath) {
    this.templateBasePath = templateBasePath;
  }

  public String getMediaBasePath() {
    return mediaBasePath;
  }

  public void setMediaBasePath(String mediaBasePath) {
    this.mediaBasePath = mediaBasePath;
  }

  public String getAcceleratedMobilePageTemplatePath() {
    return acceleratedMobilePageTemplatePath;
  }

  public void setAcceleratedMobilePageTemplatePath(String acceleratedMobilePageTemplatePath) {
    this.acceleratedMobilePageTemplatePath = acceleratedMobilePageTemplatePath;
  }
}
