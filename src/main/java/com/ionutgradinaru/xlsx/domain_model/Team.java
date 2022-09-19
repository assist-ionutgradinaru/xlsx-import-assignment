package com.ionutgradinaru.xlsx.domain_model;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum Team {

  EAST("EAST"),
  WEST("WEST"),
  NORTH("NORTH"),
  SOUTH("SOUTH");

  final String value;

  Team(final String value) {
    this.value = value;
  }

  public static Team getFromString(final String value) {
    for (var type : Team.values()) {
      if (Objects.equals(value, type.getValue())) {
        return type;
      }
    }

    throw new IllegalArgumentException(String.format("The given team doesn't exist: %s", value));
  }
}
