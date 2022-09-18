package com.ionutgradinaru.xlsx.common.exceptions;

public class FileCannotBeOpenedException extends RuntimeException {

  public FileCannotBeOpenedException(final String worksheetName) {
    super(String.format("The given %s file cannot be opened.", worksheetName));
  }
}
