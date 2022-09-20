/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api.dto;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
public class BookingTransactionDto {

  String customerName;
  LocalDate bookingDate;
  String opportunityId;
  String bookingType;
  String total;
  String accountExecutive;
  String saleOrganization;
  String team;
  String product;
  String renewable;

  public static BookingTransactionDto of(final BookingTransaction entity) {
    return BookingTransactionDto.builder()
        .customerName(entity.getCustomerName())
        .bookingDate(entity.getBookingDate())
        .opportunityId(entity.getOpportunityId())
        .bookingType(entity.getBookingType().getValue())
        .total(XlsxHelper.convertDoubleToCurrencyString.apply(entity.getTotal()))
        .accountExecutive(entity.getAccountExecutive())
        .saleOrganization(entity.getSaleOrganization().getValue())
        .team(entity.getTeam().getValue())
        .product(entity.getProduct().getValue())
        .renewable(Boolean.TRUE.equals(entity.getRenewable()) ? "YES" : "NO")
        .build();
  }
}
