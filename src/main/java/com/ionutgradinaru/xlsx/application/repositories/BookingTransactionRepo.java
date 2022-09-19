/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.repositories;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingTransactionRepo extends JpaRepository<BookingTransaction, Long>, JpaSpecificationExecutor<BookingTransaction> {

  @Query("SELECT bt.opportunityId FROM BookingTransaction bt")
  List<String> getOpportunityIds();
}
