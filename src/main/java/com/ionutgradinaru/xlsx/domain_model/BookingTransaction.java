/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.domain_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking_transaction")
public class BookingTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  Long id;

  @Column(name = "customer_name")
  String customerName;

  @Column(name = "booking_date")
  LocalDate bookingDate;

  @Column(name = "opportunity_id", unique = true)
  String opportunityId;

  @Column(name = "booking_type")
  @Enumerated(EnumType.STRING)
  BookingType bookingType;
  Double total;

  @Column(name = "account_executive")
  String accountExecutive;

  @Column(name = "sale_organization")
  @Enumerated(EnumType.STRING)
  SaleOrganization saleOrganization;

  @Enumerated(EnumType.STRING)
  Team team;

  @Enumerated(EnumType.STRING)
  Product product;
  Boolean renewable;
}
