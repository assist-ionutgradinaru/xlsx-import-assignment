/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.poi.ss.util.CellAddress;

@Getter
@AllArgsConstructor
public class XlsxDataRange {

  private final CellAddress startCellAddress;
  private final CellAddress endCellAddress;
}
