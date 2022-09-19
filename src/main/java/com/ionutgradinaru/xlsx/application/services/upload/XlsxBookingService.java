package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface XlsxBookingService {

  List<BookingTransaction> fromRange(final MultipartFile file, final String range, final String worksheetName);
}
