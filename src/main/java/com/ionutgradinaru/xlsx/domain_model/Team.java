package com.ionutgradinaru.xlsx.domain_model;

public enum Team {

  EAST("EAST"),
  WEST("WEST"),
  NORTH("NORTH"),
  SOUTH("SOUTH");

  final String name;

  Team(final String name) {
    this.name = name;
  }
}
