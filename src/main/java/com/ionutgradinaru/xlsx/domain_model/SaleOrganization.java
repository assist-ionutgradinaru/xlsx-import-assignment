package com.ionutgradinaru.xlsx.domain_model;

public enum SaleOrganization {

  MID_MARKET("Mid-Market"),
  ENTERPRISE("Enterprise"),
  TRIALS("Trials"),
  SMB("SMB"),
  GLOBAL("Global");

  final String name;

  SaleOrganization(final String name) {
    this.name = name;
  }
}
