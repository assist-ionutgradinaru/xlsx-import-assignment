/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.BookingTransactionService;
import com.ionutgradinaru.xlsx.application.services.FileMetadataService;
import com.ionutgradinaru.xlsx.application.services.XlsxBookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void findAll() {
  }
}