/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.BookingTransactionService;
import com.ionutgradinaru.xlsx.application.services.FileMetadataService;
import com.ionutgradinaru.xlsx.application.services.XlsxBookingService;
import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.domain_model.FileMetadata;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionFilterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
public class XlsxController {

  final XlsxBookingService xlsxBookingService;
  final BookingTransactionService bookingTransactionService;
  final FileMetadataService fileMetadataService;

  @Autowired
  public XlsxController(final XlsxBookingService xlsxBookingService,
                        final BookingTransactionService bookingTransactionService,
                        final FileMetadataService fileMetadataService) {

    this.xlsxBookingService = xlsxBookingService;
    this.bookingTransactionService = bookingTransactionService;
    this.fileMetadataService = fileMetadataService;
  }

  @PostMapping("/upload")
  public CompletableFuture<Void> upload(@RequestParam("file") MultipartFile file,
                                        @RequestParam("range") String range,
                                        @RequestParam("worksheet") String worksheet) {

    return CompletableFuture
        .supplyAsync(() -> xlsxBookingService.fromRange(file, range, worksheet))
        .thenAccept(bookingTransactionService::saveAll)
        .thenAccept(unused -> {
          var fileMedata = FileMetadata.builder()
              .name(worksheet)
              .size(file.getSize())
              .build();
          fileMetadataService.save(fileMedata);
        });
  }

  @GetMapping("/opportunity")
  public CompletableFuture<List<BookingTransaction>> findAll(final BookingTransactionFilterDto filters) {
    return CompletableFuture
        .supplyAsync(() -> bookingTransactionService.findAll(filters));
  }
}
