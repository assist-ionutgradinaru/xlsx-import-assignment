package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/upload")
public class XlsxUploadController {

  final UploadService uploadService;

  @Autowired
  public XlsxUploadController(final UploadService uploadService) {
    this.uploadService = uploadService;
  }

  @PostMapping()
  public void upload(@RequestParam("file") MultipartFile file) {

    uploadService.upload(file, "range", "worksheetName");
  }
}
