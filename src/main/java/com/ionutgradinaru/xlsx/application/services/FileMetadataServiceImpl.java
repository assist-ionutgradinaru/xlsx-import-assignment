/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.application.repositories.FileMetadataRepo;
import com.ionutgradinaru.xlsx.domain_model.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileMetadataServiceImpl implements FileMetadataService {

  private final FileMetadataRepo fileMetadataRepo;

  @Autowired
  public FileMetadataServiceImpl(final FileMetadataRepo fileMetadataRepo) {
    this.fileMetadataRepo = fileMetadataRepo;
  }

  @Override
  public void save(final FileMetadata fileMetadata) {
    fileMetadataRepo.save(fileMetadata);
  }
}
