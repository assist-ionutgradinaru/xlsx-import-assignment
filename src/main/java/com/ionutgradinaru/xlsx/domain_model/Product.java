package com.ionutgradinaru.xlsx.domain_model;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Product {

  ALLOY("ALLOY"),
  PLATINUM("PLATINUM"),
  GOLD("GOLD"),
  SILVER("SILVER"),
  BRONZE("BRONZE");

  final String value;

  Product(final String value) {
    this.value = value;
  }

  public static Product getFromString(final String value) {
    for (var type : Product.values()) {
      if (Objects.equals(value, type.getValue())) {
        return type;
      }
    }

    throw new IllegalArgumentException(String.format("The given product doesn't exist: %s", value));
  }
}
