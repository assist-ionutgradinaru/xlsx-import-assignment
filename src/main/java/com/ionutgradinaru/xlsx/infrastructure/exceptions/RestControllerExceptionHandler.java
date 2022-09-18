package com.ionutgradinaru.xlsx.infrastructure.exceptions;

import com.ionutgradinaru.xlsx.common.exceptions.FileCannotBeOpenedException;
import com.ionutgradinaru.xlsx.common.exceptions.WorksheetNotFoundException;
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
}
