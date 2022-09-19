package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.upload.UploadService;
import com.ionutgradinaru.xlsx.application.services.upload.XlsxBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/upload")
public class XlsxUploadController {

  final XlsxBookingService xlsxBookingService;
  final UploadService uploadService;

  @Autowired
  public XlsxUploadController(final XlsxBookingService xlsxBookingService,
                              final UploadService uploadService) {

    this.xlsxBookingService = xlsxBookingService;
    this.uploadService = uploadService;
  }

  @PostMapping()
  public void upload(@RequestParam("file") MultipartFile file,
                     @RequestParam("range") String range,
                     @RequestParam("worksheet") String worksheet) {

    var list = xlsxBookingService.fromRange(file,range, worksheet);
    uploadService.upload(list);
//    CompletableFuture
//        .supplyAsync(() -> xlsxBookingService.fromRange(file,range, worksheet))
//        .thenApply(transactions -> uploadService.upload(transactions).subscribe())
//        .thenAccept(System.out::println);
  }
}
