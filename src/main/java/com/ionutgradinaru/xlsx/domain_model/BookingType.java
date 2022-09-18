package com.ionutgradinaru.xlsx.domain_model;

public enum BookingType {

  NEW("new"),
  RENEWAL("renewal"),
  EXPANSION("expansion");

  final String name;

  BookingType(final String name) {
    this.name = name;
  }
}
