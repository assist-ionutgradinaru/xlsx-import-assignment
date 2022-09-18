package com.ionutgradinaru.xlsx.common.utils;

import org.apache.poi.ss.util.CellAddress;

import java.util.Arrays;
import java.util.stream.Collectors;

public class XlsxHelper {

  public static final CellAddress TOP_LEFT_CORNER = new CellAddress("A3");
  public static final CellAddress TOP_RIGHT_CORNER = new CellAddress("K3");

  private XlsxHelper() {
    // empty on purpose; to unsure that nobody will create an instance of this class
  }

  public static XlsxDataRange from(final String range) {
    var bounds = Arrays.stream(range.split(":")).collect(Collectors.toList());
    XlsxRangeValidator.validate().accept(bounds);

    return new XlsxDataRange(
        new CellAddress(bounds.get(0)),
        new CellAddress(bounds.get(1))
    );
  }

  public int skipRows(final XlsxDataRange range) {
    return range.getStartCellAddress().getRow() - TOP_LEFT_CORNER.getRow();
  }

  public int limitRows(final XlsxDataRange range) {
    return range.getEndCellAddress().getRow() - TOP_LEFT_CORNER.getRow();
  }
}
