package com.ionutgradinaru.xlsx.application.services.upload;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

  void upload(final MultipartFile file, final String range, final String worksheetName);
}
