package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.application.repositories.ReactiveBookingTransactionRepo;
import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

  final ReactiveBookingTransactionRepo bookingTransactionRepo;

  @Autowired
  public UploadServiceImpl(final ReactiveBookingTransactionRepo bookingTransactionRepo) {
    this.bookingTransactionRepo = bookingTransactionRepo;
  }

  @SneakyThrows
  @Override
  public Mono<Long> upload(final List<BookingTransaction> bookingTransactions) {
    return bookingTransactionRepo
        .saveAll(bookingTransactions)
        .count();
  }
}
