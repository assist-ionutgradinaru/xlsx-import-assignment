/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.utils;

import org.apache.poi.ss.util.CellAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class XlsxHelperTest {

  @Nested
  @DisplayName("Tests for data range.")
  class GetDataRangeFromString {

    @Test
    @DisplayName("Test for getting a valid DataRange object.")
    void getDataRange_shouldReturnADataRangeObject_When_ValidRange() {
      var range = "A3:K3";
      var expectedResult = new XlsxDataRange(
          new CellAddress("A3"),
          new CellAddress("K3")
      );

      var result = XlsxHelper.getDataRange.apply(range);
      assertEquals(expectedResult, result);
    }
  }

  @Nested
  @DisplayName("Tests for converting from a string to double and the other way around.")
  class ConvertCurrencyDouble {

    @Test
    @DisplayName("Test for converting from a string to double a valid string.")
    void convertCurrencyStringToDouble_shouldReturnDouble_When_ValidString() {
      var currency = "$100,000.98";
      var expectedResult = 100_000.98; // A4 - A1

      var result = XlsxHelper.convertCurrencyStringToDouble.applyAsDouble(currency);
      assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Test for converting from a double to currency string.")
    void convertDoubleToCurrencyString_shouldReturnString_When_ValidNumber() {
      var number = 100_000.98;
      var expectedResult = "$100,000.98"; // A4 - A1

      var result = XlsxHelper.convertDoubleToCurrencyString.apply(number);
      assertEquals(expectedResult, result);
    }
  }

  @Test
  @DisplayName("Test for getting the skip rows number for a valid range.")
  void skipRows_shouldReturnSkipRows_When_ValidRange() {
    var range = XlsxHelper.getDataRange.apply("A4:K3");
    var expectedResult = 1; // A4 - A1

    var result = XlsxHelper.skipRows.applyAsInt(range);
    assertEquals(expectedResult, result);
  }

  @Test
  @DisplayName("Test for getting the limit rows number for a valid range.")
  void limitRows_shouldReturnLimitRows_When_ValidRange() {
    var range = XlsxHelper.getDataRange.apply("A10:K45");
    var expectedResult = 42; // K47 - K3

    var result = XlsxHelper.limitRows.applyAsInt(range);
    assertEquals(expectedResult, result);
  }

  @Test
  @DisplayName("Test for checking the content type.")
  void validateFileType_shouldThrowException_When_DifferentThanExcel() {
    var contentType = "application/json";

    assertThrows(
        IllegalArgumentException.class,
        () -> XlsxHelper.validateFileType.accept(contentType)
    );
  }

}