/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.application.repositories.BookingTransactionRepo;
import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.infrastructure.api.BookingTransactionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class BookingTransactionServiceImpl implements BookingTransactionService {

  final BookingTransactionRepo bookingTransactionRepo;

  @Autowired
  public BookingTransactionServiceImpl(final BookingTransactionRepo bookingTransactionRepo) {
    this.bookingTransactionRepo = bookingTransactionRepo;
  }

  @Override
  public void upload(final List<BookingTransaction> bookingTransactions) {
    CompletableFuture
        .supplyAsync(bookingTransactionRepo::getOpportunityIds)
        .thenApply(existingIds -> bookingTransactions.stream()
            .filter(transaction -> !existingIds.contains(transaction.getOpportunityId()))
            .collect(Collectors.toList()))
        .thenAccept(bookingTransactionRepo::saveAll);
  }

  @Override
  public List<BookingTransaction> findAll(BookingTransactionFilter filters) {
    return null;
  }
}
