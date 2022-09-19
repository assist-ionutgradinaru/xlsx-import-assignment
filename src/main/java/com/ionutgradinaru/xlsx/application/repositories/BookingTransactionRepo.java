package com.ionutgradinaru.xlsx.application.repositories;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingTransactionRepo extends JpaRepository<BookingTransaction, Long> {

  @Query("SELECT bt.opportunityId FROM BookingTransaction bt")
  List<String> getOpportunityIds();
}