package com.ionutgradinaru.xlsx.infrastructure.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  @ExceptionHandler(FileCannotBeOpenedException.class)
  ResponseEntity<String> fileCannotBeOpened(FileCannotBeOpenedException ex) {
    return ResponseEntity
        .internalServerError()
        .body(ex.getMessage());
  }

  @ExceptionHandler(WorksheetNotFoundException.class)
  ResponseEntity<String> worksheetNotFound(WorksheetNotFoundException ex) {
    return ResponseEntity
        .badRequest()
        .body(ex.getMessage());
  }

  @ExceptionHandler(InvalidRangeArgumentException.class)
  ResponseEntity<String> invalidRange(InvalidRangeArgumentException ex) {
    return ResponseEntity
        .badRequest()
        .body(ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  ResponseEntity<String> illegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .badRequest()
        .body(ex.getMessage());
  }
}
