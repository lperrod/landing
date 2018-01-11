package com.cmpl.landing.media;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/public")
public class MediaController {

  private final MediaService mediaService;
  private final TikaConfig tikaConfig;

  @Autowired
  public MediaController(MediaService mediaService) {
    this.mediaService = mediaService;
    this.tikaConfig = TikaConfig.getDefaultConfig();
  }

  @GetMapping("/{mediaName:.+}")
  public void serve(@PathVariable("mediaName") String mediaName, HttpServletResponse res) throws SQLException,
      IOException {
    InputStream mediaContent = mediaService.read(mediaName);
    MediaType mediaType = detectMediaType(mediaContent, mediaName);

    if (mediaType != null) {
      res.setContentType(mediaType.toString());
    }
    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "Content-Disposition: inline; filename=\"" + mediaName + "\"");
    StreamUtils.copy(mediaService.read(mediaName), res.getOutputStream());
    return;

  }

  @GetMapping("/{folderName}/{mediaName:.+}")
  public void serveFromFolder(@PathVariable("mediaName") String mediaName,
      @PathVariable("folderName") String folderName, HttpServletResponse res) throws SQLException, IOException {
    InputStream mediaContent = mediaService.read(folderName + File.separator + mediaName);
    MediaType mediaType = detectMediaType(mediaContent, mediaName);

    if (mediaType != null) {
      res.setContentType(mediaType.toString());
    }

    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "Content-Disposition: inline; filename=\"" + folderName
        + File.separator + mediaName + "\"");
    StreamUtils.copy(mediaContent, res.getOutputStream());
    return;

  }

  private MediaType detectMediaType(InputStream mediaContent, String mediaName) {
    Detector detector = tikaConfig.getDetector();
    TikaInputStream stream = TikaInputStream.get(mediaContent);
    Metadata metadata = new Metadata();
    metadata.add(Metadata.RESOURCE_NAME_KEY, mediaName);
    try {
      return detector.detect(stream, metadata);
    } catch (IOException e) {
      return null;
    }
  }
}
