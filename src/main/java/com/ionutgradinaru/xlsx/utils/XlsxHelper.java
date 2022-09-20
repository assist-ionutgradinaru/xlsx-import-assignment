/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.utils;

import org.apache.poi.ss.util.CellAddress;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class XlsxHelper {

  public static final String EXCEL_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  public static final int HEADER_INDEX_START = 2;
  public static final String DATE_FORMAT = "dd/MM/yyyy";
  public static final Locale LOCALE = Locale.US;

  public static final CellAddress TOP_LEFT_CORNER = new CellAddress("A3");
  public static final CellAddress TOP_RIGHT_CORNER = new CellAddress("K3");

  private XlsxHelper() {
    // empty on purpose; to unsure that nobody will create an instance of this class
  }

  public static XlsxDataRange getDataRange(final String range) {
    var bounds = Arrays.stream(range.split(":")).collect(Collectors.toList());
    return new XlsxDataRange(
        new CellAddress(bounds.get(0)),
        new CellAddress(bounds.get(1))
    );
  }

  public static int skipRows(final XlsxDataRange range) {
    return range.getStartCellAddress().getRow() - TOP_LEFT_CORNER.getRow();
  }

  public static int limitRows(final XlsxDataRange range) {
    return range.getEndCellAddress().getRow() - TOP_LEFT_CORNER.getRow();
  }

  public static void validateFileType(final String file) {
    if (!Objects.equals(file, EXCEL_CONTENT_TYPE)) {
      throw new IllegalArgumentException("The given file doesn't have the XLSX format.");
    }
  }

  public static final Consumer<String> validateRangeString = str -> {
    var bounds = Arrays.stream(str.split(":")).collect(Collectors.toList());
    XlsxRangeValidator.validate().accept(bounds);
  };

  public static final ToDoubleFunction<String> convertCurrencyStringToDouble = str -> {
    try {
      return NumberFormat.getCurrencyInstance(LOCALE)
          .parse(str)
          .doubleValue();
    } catch (ParseException e) {
      throw new NumberFormatException(e.getMessage());
    }
  };

  public static final DoubleFunction<String> convertDoubleToCurrencyString = number ->
      NumberFormat.getCurrencyInstance(LOCALE).format(number);

  public static final Function<String, LocalDate> convertStringToDate = str ->
      LocalDate.parse(str, DateTimeFormatter.ofPattern(XlsxHelper.DATE_FORMAT));
}
