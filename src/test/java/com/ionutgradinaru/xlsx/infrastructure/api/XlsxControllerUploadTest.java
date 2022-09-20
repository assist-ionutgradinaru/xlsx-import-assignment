/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(XlsxController.class)
class XlsxControllerUploadTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Test for valid parameters.")
  void upload_ShouldReturnOk_When_ValidParams() {

  }

  @Test
  @DisplayName("Test for missing the file parameter.")
  void upload_ShouldReturnBadRequest_When_NoFile() {

  }

  @Test
  @DisplayName("Test for missing the range parameter.")
  void upload_ShouldReturnBadRequest_When_NoRange() {

  }

  @Test
  @DisplayName("Test for missing the worksheet parameter.")
  void upload_ShouldReturnBadRequest_When_NoWorksheet() {

  }

  @Test
  @DisplayName("Test for invalid file format.")
  void upload_ShouldReturnBadRequest_When_InvalidFileFormat() {

  }

  @Test
  @DisplayName("Test for invalid worksheet.")
  void upload_ShouldReturnBadRequest_When_InvalidWorksheet() {

  }

  @Nested
  @DisplayName("Tests when the range is invalid.")
  class WhenInvalidRange {

    @Test
    @DisplayName("Test for invalid range format.")
    void upload_ShouldReturnBadRequest_When_InvalidRangeFormat() {

    }

    @Test
    @DisplayName("Test for invalid range: start limit.")
    void upload_ShouldReturnBadRequest_When_InvalidStartLimitRange() {

    }

    @Test
    @DisplayName("Test for invalid range: end limit.")
    void upload_ShouldReturnBadRequest_When_InvalidEndLimitRange() {

    }
  }
}