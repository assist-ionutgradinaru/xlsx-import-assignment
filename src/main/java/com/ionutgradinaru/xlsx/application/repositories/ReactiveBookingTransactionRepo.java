package com.ionutgradinaru.xlsx.application.repositories;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ReactiveBookingTransactionRepo extends R2dbcRepository<BookingTransaction, Long> {
}
