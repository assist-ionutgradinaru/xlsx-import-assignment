package com.ionutgradinaru.xlsx.domain_model;

public enum Product {

  ALLOY("ALLOY"),
  PLATINUM("PLATINUM"),
  GOLD("GOLD"),
  SILVER("SILVER"),
  BRONZE("BRONZE");

  final String name;

  Product(final String name) {
    this.name = name;
  }
}
