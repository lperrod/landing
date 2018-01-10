package com.cmpl.landing.media;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

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

  @Autowired
  public MediaController(MediaService mediaService) {
    this.mediaService = mediaService;
  }

  @GetMapping("/{mediaName:.+}")
  public void serve(@PathVariable("mediaName") String mediaName, HttpServletResponse res)
      throws SQLException, IOException {
    res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "Content-Disposition: inline; filename=\"" + mediaName + "\"");
    StreamUtils.copy(mediaService.read(mediaName), res.getOutputStream());
    return;

  }

  @GetMapping("/{folderName}/{mediaName:.+}")
  public void serveFromFolder(@PathVariable("mediaName") String mediaName,
      @PathVariable("folderName") String folderName, HttpServletResponse res) throws SQLException, IOException {
    res.setHeader(HttpHeaders.CONTENT_DISPOSITION,
        "Content-Disposition: inline; filename=\"" + folderName + File.separator + mediaName + "\"");
    StreamUtils.copy(mediaService.read(folderName + File.separator + mediaName), res.getOutputStream());
    return;

  }
}
