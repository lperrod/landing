package com.cmpl.landing.media;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.cmpl.landing.context.ContextHolder;

public class MediaServiceImpl implements MediaService {

  private final ContextHolder contextHolder;

  public MediaServiceImpl(ContextHolder contextHolder) {
    this.contextHolder = contextHolder;
  }

  @Override
  public InputStream read(String fileName) {
    try {
      return new ByteArrayInputStream(Files.readAllBytes(Paths.get(contextHolder.getMediaBasePath() + fileName)));
    } catch (Exception e) {
      return new ByteArrayInputStream(new byte[]{});
    }
  }

}
