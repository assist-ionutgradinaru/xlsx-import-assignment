/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.utils;

import lombok.Value;
import org.apache.poi.ss.util.CellAddress;

@Value
public class XlsxDataRange {

  CellAddress startCellAddress;
  CellAddress endCellAddress;
}
