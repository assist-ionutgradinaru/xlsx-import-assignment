/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.BookingTransactionService;
import com.ionutgradinaru.xlsx.application.services.FileMetadataService;
import com.ionutgradinaru.xlsx.application.services.XlsxBookingService;
import com.ionutgradinaru.xlsx.domain_model.FileMetadata;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionDto;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionFilterDto;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

  @PostMapping(value = "/upload")
  public void upload(@RequestParam("file") MultipartFile file,
                     @RequestParam("range") String range,
                     @RequestParam("worksheet") String worksheet) throws IOException {

    // Validate parameters
    XlsxHelper.validateFileType.accept(file.getContentType());
    XlsxHelper.validateRangeString.accept(range);

    // Transform from Excel data to BookingTransactions and save them to DB
    var bookingTransactions = xlsxBookingService.fromRange(file.getInputStream(), range, worksheet);
    bookingTransactionService.saveAll(bookingTransactions);

    // Save the filemetada
    var fileMedata = FileMetadata.builder().name(worksheet).size(file.getSize()).build();
    fileMetadataService.save(fileMedata);
  }

  @GetMapping("/opportunity")
  public List<BookingTransactionDto> findAll(final BookingTransactionFilterDto filters) {
    return bookingTransactionService
        .findAll(filters)
        .stream()
        .map(BookingTransactionDto::of)
        .collect(Collectors.toList());
  }
}
