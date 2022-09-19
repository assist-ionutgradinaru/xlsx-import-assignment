/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.exceptions;

public class FileCannotBeOpenedException extends RuntimeException {

  public FileCannotBeOpenedException(final String worksheetName) {
    super(String.format("The given %s file cannot be opened.", worksheetName));
  }
}
