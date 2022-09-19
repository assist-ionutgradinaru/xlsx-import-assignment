/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.domain_model;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum BookingType {

  NEW("new"),
  RENEWAL("renewal"),
  EXPANSION("expansion");

  final String value;

  BookingType(final String value) {
    this.value = value;
  }

  public static BookingType getFromString(final String value) {
    for (var type : BookingType.values()) {
      if (Objects.equals(value, type.getValue())) {
        return type;
      }
    }

    throw new IllegalArgumentException(String.format("The given booking type doesn't exist: %s", value));
  }
}
