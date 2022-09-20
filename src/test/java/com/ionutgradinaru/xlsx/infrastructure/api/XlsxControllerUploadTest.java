/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.ionutgradinaru.xlsx.application.services.BookingTransactionService;
import com.ionutgradinaru.xlsx.application.services.FileMetadataService;
import com.ionutgradinaru.xlsx.application.services.XlsxBookingService;
import com.ionutgradinaru.xlsx.infrastructure.exceptions.InvalidRangeArgumentException;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(XlsxController.class)
class XlsxControllerUploadTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  XlsxBookingService xlsxBookingService;
  @MockBean
  BookingTransactionService bookingTransactionService;
  @MockBean
  FileMetadataService fileMetadataService;

  MockMultipartFile validFile = new MockMultipartFile("file", "valid_test_data.xlsx", XlsxHelper.EXCEL_CONTENT_TYPE, "valid-file".getBytes());

  @SneakyThrows
  @Test
  @DisplayName("Test for valid parameters.")
  void upload_ShouldReturnOk_When_ValidParams() {
    var request = multipart("/api/v1/upload")
        .file("file", validFile.getBytes())
        .contentType(XlsxHelper.EXCEL_CONTENT_TYPE)
        .param("range", "A3:K50")
        .param("worksheet", "worksheet_test");

    mockMvc.perform(request)
        .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  @DisplayName("Test for missing the file parameter.")
  void upload_ShouldReturnBadRequest_When_NoFile() {
    var request = multipart("/api/v1/upload")
        .param("range", "A3:K50")
        .param("worksheet", "worksheet_test");

    mockMvc.perform(request)
        .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  @DisplayName("Test for missing the range parameter.")
  void upload_ShouldReturnBadRequest_When_NoRange() {
    var request = multipart("/api/v1/upload")
        .file("file", validFile.getBytes())
        .contentType(XlsxHelper.EXCEL_CONTENT_TYPE)
        .param("worksheet", "worksheet_test");

    mockMvc.perform(request)
        .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  @DisplayName("Test for missing the worksheet parameter.")
  void upload_ShouldReturnBadRequest_When_NoWorksheet() {
    var request = multipart("/api/v1/upload")
        .file("file", validFile.getBytes())
        .contentType(XlsxHelper.EXCEL_CONTENT_TYPE)
        .param("range", "A3:K50");

    mockMvc.perform(request)
        .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @Test
  @DisplayName("Test for invalid file format.")
  void upload_ShouldReturnBadRequest_When_InvalidFileFormat() {
    var request = multipart("/api/v1/upload")
        .file("file", validFile.getBytes())
        .contentType("application/json")
        .param("range", "A3:K50")
        .param("worksheet", "worksheet_test");

    var mvcResult = mockMvc.perform(request)
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().isBadRequest());
  }

  @SneakyThrows
  @ParameterizedTest
  @DisplayName("Test for invalid range format.")
  @ValueSource(strings = {"B1:", "bcB1", "B3K50", "B1:1K2", "1B3:K30", "B3:1K2"})
  void upload_ShouldReturnBadRequest_When_InvalidRangeFormat(final String range) {
    var request = multipart("/api/v1/upload")
        .file(validFile)
        .contentType(XlsxHelper.EXCEL_CONTENT_TYPE)
        .param("range", range)
        .param("worksheet", "worksheet_test");

    var mvcResult = mockMvc.perform(request)
        .andExpect(request().asyncStarted())
        .andReturn();

    mockMvc.perform(asyncDispatch(mvcResult))
        .andExpect(status().isBadRequest())
        .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidRangeArgumentException));
  }
}