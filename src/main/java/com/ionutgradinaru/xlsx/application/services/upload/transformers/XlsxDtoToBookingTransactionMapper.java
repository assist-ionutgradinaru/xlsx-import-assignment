package com.ionutgradinaru.xlsx.application.services.upload.transformers;

import com.ionutgradinaru.xlsx.application.services.upload.XlsxBookingTransaction;
import com.ionutgradinaru.xlsx.domain_model.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.function.ToDoubleFunction;

public class XlsxDtoToBookingTransactionMapper {

  private XlsxDtoToBookingTransactionMapper() {
  }

  public static BookingTransaction from(final XlsxBookingTransaction dto) {
    return BookingTransaction.builder()
        .customerName(dto.getCustomerName())
        .bookingDate(dto.getBookingDate())
        .opportunityId(dto.getOpportunityId())
        .bookingType(BookingType.valueOf(dto.getBookingType()))
        .total(convertStringToDouble.applyAsDouble(dto.getTotal()))
        .accountExecutive(dto.getAccountExecutive())
        .saleOrganization(SaleOrganization.valueOf(dto.getSaleOrganization()))
        .team(Team.valueOf(dto.getTeam()))
        .product(Product.valueOf(dto.getProduct()))
        .renewable(Boolean.valueOf(dto.getRenewable()))
        .build();
  }

  private static final ToDoubleFunction<String> convertStringToDouble = str -> {
    try {
      return NumberFormat.getCurrencyInstance(Locale.US)
          .parse(str)
          .doubleValue();
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  };
}
