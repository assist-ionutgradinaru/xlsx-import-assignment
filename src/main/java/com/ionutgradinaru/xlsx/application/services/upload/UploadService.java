package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;

import java.util.List;

public interface UploadService {

  int upload(final List<BookingTransaction> bookingTransactions);
}
