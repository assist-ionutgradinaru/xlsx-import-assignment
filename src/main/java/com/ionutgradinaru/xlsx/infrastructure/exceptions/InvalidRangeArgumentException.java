/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.exceptions;

public class InvalidRangeArgumentException extends RuntimeException {

  public InvalidRangeArgumentException(final String message) {
    super(message);
  }
}
