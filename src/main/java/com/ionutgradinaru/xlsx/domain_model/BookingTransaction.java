package com.ionutgradinaru.xlsx.domain_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BookingTransaction {

  @Id
  @Column(name = "id", nullable = false)
  Long id;
  @Column(name = "customer_name")
  String customerName;
  Date bookingDate;
  @Column(name = "opportunity_id", unique = true)
  String opportunityId;
  @Enumerated(EnumType.STRING)
  BookingType bookingType;
  Double total;
  String accountExecutive;
  @Enumerated(EnumType.STRING)
  SaleOrganization saleOrganization;
  @Enumerated(EnumType.STRING)
  Team team;
  @Enumerated(EnumType.STRING)
  Product product;
  Boolean renewable;
}
