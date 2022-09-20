/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.BookingTransactionService;
import com.ionutgradinaru.xlsx.application.services.FileMetadataService;
import com.ionutgradinaru.xlsx.application.services.XlsxBookingService;
import com.ionutgradinaru.xlsx.domain_model.*;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(XlsxController.class)
class XlsxControllerOpportunityTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  XlsxBookingService xlsxBookingService;
  @MockBean
  BookingTransactionService bookingTransactionService;
  @MockBean
  FileMetadataService fileMetadataService;

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

  @SneakyThrows
  @Test
  @DisplayName("Test for getting a list of Booking Transactions.")
  void findAll_shouldReturnAListOfBookingTransactions() {
    when(bookingTransactionService.findAll(any())).thenReturn(Collections.nCopies(3, bookingTransaction));

    mockMvc.perform(get("/api/v1/opportunity")
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].customerName", is("customerName_test")))
        .andExpect(jsonPath("$[0].customerName", is("customerName_test")))
        .andExpect(jsonPath("$[0].opportunityId", is("opportunityId_test")))
        .andExpect(jsonPath("$[0].bookingType", is("new")))
        .andExpect(jsonPath("$[0].total", is("$100,000.89")))
        .andExpect(jsonPath("$[0].accountExecutive", is("accountExecutive_test")))
        .andExpect(jsonPath("$[0].saleOrganization", is(SaleOrganization.GLOBAL.getValue())))
        .andExpect(jsonPath("$[0].team", is(Team.NORTH.getValue())))
        .andExpect(jsonPath("$[0].product", is(Product.PLATINUM.getValue())))
        .andExpect(jsonPath("$[0].renewable", is("YES")));
  }
}