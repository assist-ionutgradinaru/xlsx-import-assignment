/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.domain_model;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum SaleOrganization {

  MID_MARKET("Mid-Market"),
  ENTERPRISE("Enterprise"),
  TRIALS("Trials"),
  SMB("SMB"),
  GLOBAL("Global");

  final String value;

  SaleOrganization(final String value) {
    this.value = value;
  }

  public static SaleOrganization getFromString(final String value) {
    for (var type : SaleOrganization.values()) {
      if (Objects.equals(value, type.getValue())) {
        return type;
      }
    }

    throw new IllegalArgumentException(String.format("The given sale organization doesn't exist: %s", value));
  }
}
