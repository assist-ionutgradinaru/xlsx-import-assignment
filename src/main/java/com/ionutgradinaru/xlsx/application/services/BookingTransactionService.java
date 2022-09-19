/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.infrastructure.api.dto.BookingTransactionFilterDto;

import java.util.List;

public interface BookingTransactionService {

  void saveAll(final List<BookingTransaction> bookingTransactions);

  List<BookingTransaction> findAll(final BookingTransactionFilterDto filters);
}
