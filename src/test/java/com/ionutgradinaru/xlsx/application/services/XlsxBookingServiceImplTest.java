/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class XlsxBookingServiceImplTest {

  static final String EXCEL_FILE_PATH = "valid_test_data.xlsx";

  @InjectMocks
  XlsxBookingServiceImpl xlsxBookingService;

  @SneakyThrows
  @Test
  @DisplayName("Test the return data after parsing the Excel file.")
  void fromRange_shouldReturnBookingTransaction_When_ValidFile() {
    var expectedSize = 47;
    var range = "A3:K50";
    var worksheet = "MOCK_DATA_47";

    try (var inputStream = getClass().getClassLoader().getResourceAsStream(EXCEL_FILE_PATH)) {
      var result = xlsxBookingService.fromRange(inputStream, range, worksheet);
      assertEquals(expectedSize, result.size());
      assertThat(result, everyItem(
          allOf(
              isA(BookingTransaction.class)
          )
      ));
    }
  }

  @SneakyThrows
  @Test
  @DisplayName("Test parsing with invalid worksheet name.")
  void fromRange_shouldReturnBookingTransaction_When_InvalidWorksheet() {
    var expectedSize = 0;
    var range = "A3:K50";
    var worksheet = "INVALID_MOCK_DATA_47";

    try (var inputStream = getClass().getClassLoader().getResourceAsStream(EXCEL_FILE_PATH)) {
      var result = xlsxBookingService.fromRange(inputStream, range, worksheet);
      assertEquals(expectedSize, result.size());
    }
  }

  @SneakyThrows
  @Test
  @DisplayName("Test parsing with out of range limits.")
  void fromRange_shouldReturnBookingTransaction_When_OutOfRange() {
    var expectedSize = 47;
    var range = "A1:K70";
    var worksheet = "MOCK_DATA_47";

    try (var inputStream = getClass().getClassLoader().getResourceAsStream(EXCEL_FILE_PATH)) {
      var result = xlsxBookingService.fromRange(inputStream, range, worksheet);
      assertEquals(expectedSize, result.size());
    }
  }
}