/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.application.repositories.FileMetadataRepo;
import com.ionutgradinaru.xlsx.domain_model.FileMetadata;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileMetadataServiceImplTest {

  @Captor
  ArgumentCaptor<FileMetadata> metadataArgumentCaptor;

  @Mock
  FileMetadataRepo fileMetadataRepo;

  @InjectMocks
  FileMetadataServiceImpl fileMetadataService;

  @Test
  @DisplayName("Test for saving file metadata")
  void save_shouldSaveData_When_IsCalled() {
    var fileMetadata = FileMetadata.builder()
        .name("name_test")
        .size(101325L)
        .build();

    when(fileMetadataRepo.save(metadataArgumentCaptor.capture())).thenReturn(fileMetadata);

    fileMetadataService.save(fileMetadata);
    assertEquals(fileMetadata.getName(), metadataArgumentCaptor.getValue().getName());
    assertEquals(fileMetadata.getSize(), metadataArgumentCaptor.getValue().getSize());
  }
}