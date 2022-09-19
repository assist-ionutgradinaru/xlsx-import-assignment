package com.ionutgradinaru.xlsx.common.utils;

import com.ionutgradinaru.xlsx.common.exceptions.InvalidRangeArgumentException;
import org.apache.poi.ss.util.CellAddress;

import java.util.List;
import java.util.function.Consumer;

import static com.ionutgradinaru.xlsx.common.utils.XlsxHelper.TOP_LEFT_CORNER;
import static com.ionutgradinaru.xlsx.common.utils.XlsxHelper.TOP_RIGHT_CORNER;

interface XlsxRangeValidator extends Consumer<List<String>> {

  static XlsxRangeValidator validate() {
    return list -> isRangeFormatValid()
        .and(isRangeBoundsValid())
        .accept(list);
  }

  private static XlsxRangeValidator isRangeFormatValid() {
    return list -> {
      if (list.size() != 2) {
        throw new InvalidRangeArgumentException("The range is invalid.");
      }
    };
  }

  private static XlsxRangeValidator isRangeBoundsValid() {
    return list -> {
      var start = new CellAddress(list.get(0));
      var end = new CellAddress(list.get(0));

      if (start.getRow() < TOP_LEFT_CORNER.getRow()) {
        throw new InvalidRangeArgumentException("The start row is out of range.");
      } else if (start.getColumn() < TOP_LEFT_CORNER.getColumn()) {
        throw new InvalidRangeArgumentException("The start column is out of range.");
      } else if (end.getColumn() > TOP_RIGHT_CORNER.getColumn()) {
        throw new InvalidRangeArgumentException("The end column is out of range.");
      }
    };
  }

  default XlsxRangeValidator and(XlsxRangeValidator other) {
    return list -> {
      this.accept(list);
      other.accept(list);
    };
  }
}
