package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UploadService {

  Mono<Long> upload(final List<BookingTransaction> bookingTransactions);
}
