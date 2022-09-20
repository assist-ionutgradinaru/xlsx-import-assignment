/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.application.repositories.BookingTransactionRepo;
import com.ionutgradinaru.xlsx.application.repositories.BookingTransactionSpecification;
import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionFilterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingTransactionServiceImpl implements BookingTransactionService {

  final BookingTransactionRepo bookingTransactionRepo;

  @Autowired
  public BookingTransactionServiceImpl(final BookingTransactionRepo bookingTransactionRepo) {
    this.bookingTransactionRepo = bookingTransactionRepo;
  }

  @Override
  public void saveAll(final List<BookingTransaction> bookingTransactions) {
    var existingIds = bookingTransactionRepo.getOpportunityIds();
    var transactionsExceptDuplicates = bookingTransactions.stream()
        .filter(transaction -> !existingIds.contains(transaction.getOpportunityId()))
        .collect(Collectors.toList());
    bookingTransactionRepo.saveAll(transactionsExceptDuplicates);
  }

  @Override
  public List<BookingTransaction> findAll(final BookingTransactionFilterDto filters) {
    var specification = BookingTransactionSpecification.getTransactions(filters);
    return bookingTransactionRepo.findAll(specification);
  }
}
