package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import reactor.core.publisher.Flux;

import java.util.List;

public interface UploadService {

  Flux<BookingTransaction> upload(final List<BookingTransaction> bookingTransactions);
}
