package com.ionutgradinaru.xlsx.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.util.CellAddress;

@Getter
@AllArgsConstructor
public class XlsxDataRange {

  private final CellAddress startCellAddress;
  private final CellAddress endCellAddress;
}
