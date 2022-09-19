package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.application.repositories.BookingTransactionRepo;
import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadServiceImpl implements UploadService {

  final BookingTransactionRepo bookingTransactionRepo;

  @Autowired
  public UploadServiceImpl(final BookingTransactionRepo bookingTransactionRepo) {
    this.bookingTransactionRepo = bookingTransactionRepo;
  }

  @SneakyThrows
  @Override
  public void upload(final List<BookingTransaction> bookingTransactions) {
    var existingIds = bookingTransactionRepo.getOpportunityIds();
    var exceptDuplicates = bookingTransactions.stream()
        .filter(transaction -> !existingIds.contains(transaction.getOpportunityId()))
        .collect(Collectors.toList());

    bookingTransactionRepo.saveAll(exceptDuplicates);
  }
}
