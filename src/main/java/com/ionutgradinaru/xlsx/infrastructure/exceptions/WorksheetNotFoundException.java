/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.exceptions;

public class WorksheetNotFoundException extends RuntimeException {

  public WorksheetNotFoundException(final String worksheetName) {
    super(String.format("The given %s worksheet cannot be found.", worksheetName));
  }
}
