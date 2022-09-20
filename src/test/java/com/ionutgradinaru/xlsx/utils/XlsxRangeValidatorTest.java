/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.utils;

import com.ionutgradinaru.xlsx.infrastructure.exceptions.InvalidRangeArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class XlsxRangeValidatorTest {

  @ParameterizedTest
  @DisplayName("Tests for invalid data range format.")
  @ValueSource(strings = {"B1:", "bcB1", "B3K50", "B1:1K2", "1B3:K30", "B3:1K2"})
  void validate_shouldThrowException_WhenInvalidRange(final String range) {
    var input = Arrays.stream(range.split(":")).collect(Collectors.toList());

    assertThrows(
        InvalidRangeArgumentException.class,
        () -> XlsxRangeValidator.validate().accept(input)
    );
  }

  @ParameterizedTest
  @DisplayName("Tests for invalid data range limits.")
  @ValueSource(strings = {"A1:K50", "B3:L3"})
  void validate_shouldThrowException_WhenRangeOutOfBounds(final String range) {
    var input = Arrays.stream(range.split(":")).collect(Collectors.toList());

    assertThrows(
        InvalidRangeArgumentException.class,
        () -> XlsxRangeValidator.validate().accept(input)
    );
  }
}