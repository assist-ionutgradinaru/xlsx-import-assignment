package com.ionutgradinaru.xlsx.common.exceptions;

public class WorksheetNotFoundException extends Exception {

  public WorksheetNotFoundException(final String worksheetName) {
    super(String.format("The given %s worksheet cannot be found.", worksheetName));
  }
}
