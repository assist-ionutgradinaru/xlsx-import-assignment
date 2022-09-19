package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.infrastructure.api.BookingTransactionFilter;

import java.util.List;

public interface BookingTransactionService {

  void upload(final List<BookingTransaction> bookingTransactions);

  List<BookingTransaction> findAll(final BookingTransactionFilter filters);
}
