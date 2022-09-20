/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.application.repositories.BookingTransactionRepo;
import com.ionutgradinaru.xlsx.domain_model.*;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingTransactionServiceImplTest {

  @Captor
  ArgumentCaptor<List<BookingTransaction>> transactionsArgumentCaptor;

  @Mock
  BookingTransactionRepo bookingTransactionRepo;

  @InjectMocks
  BookingTransactionServiceImpl bookingTransactionService;

  LocalDate bookingDate = LocalDate.parse("20/10/2022", DateTimeFormatter.ofPattern(XlsxHelper.DATE_FORMAT));
  BookingTransaction bookingTransaction;

  @BeforeEach
  void setUp() {
    bookingTransaction = BookingTransaction.builder()
        .id(13L)
        .customerName("customerName_test")
        .bookingDate(bookingDate)
        .opportunityId("opportunityId_test")
        .bookingType(BookingType.NEW)
        .total(100_000.89)
        .accountExecutive("accountExecutive_test")
        .saleOrganization(SaleOrganization.GLOBAL)
        .team(Team.NORTH)
        .product(Product.PLATINUM)
        .renewable(Boolean.TRUE)
        .build();
  }

  @Test
  @DisplayName("Test for saving all booking transactions.")
  void saveAll_shouldSaveAllTransactions_When_NoDuplicatesIds() {
    var existingIds = List.of("opp_id1", "opp_id2", "opp_id3");
    var transactions = Collections.nCopies(3, bookingTransaction);

    when(bookingTransactionRepo.getOpportunityIds()).thenReturn(existingIds);
    when(bookingTransactionRepo.saveAll(transactionsArgumentCaptor.capture())).thenReturn(transactions);

    bookingTransactionService.saveAll(transactions);
    assertEquals(3, transactionsArgumentCaptor.getValue().size());
    assertEquals(transactions, transactionsArgumentCaptor.getValue());
  }

  @Test
  @DisplayName("Test for saving all booking transactions except duplicates.")
  void saveAll_shouldSaveTransactions_When_Duplicates() {
    var existingIds = List.of("opportunityId_test", "opp_id2", "opp_id3");
    var transactions = Collections.nCopies(3, bookingTransaction);

    when(bookingTransactionRepo.getOpportunityIds()).thenReturn(existingIds);
    when(bookingTransactionRepo.saveAll(transactionsArgumentCaptor.capture())).thenReturn(transactions);

    bookingTransactionService.saveAll(transactions);
    assertEquals(0, transactionsArgumentCaptor.getValue().size());
  }
}