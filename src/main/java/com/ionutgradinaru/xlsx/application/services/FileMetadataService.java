/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.FileMetadata;

public interface FileMetadataService {

  void save(final FileMetadata fileMetadata);
}
