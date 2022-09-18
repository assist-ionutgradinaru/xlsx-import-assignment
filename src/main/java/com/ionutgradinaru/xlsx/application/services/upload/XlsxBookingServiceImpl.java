package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.domain_model.*;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

@Service
public class XlsxBookingServiceImpl implements XlsxBookingService {

  @Override
  public List<XlsxBookingTransaction> fromRange(final String range) {
    return null;
  }

  private static final ToDoubleFunction<String> convertStringToDouble = str -> {
    try {
      return NumberFormat.getCurrencyInstance(Locale.US)
          .parse(str)
          .doubleValue();
    } catch (ParseException e) {
      throw new NumberFormatException(e.getMessage());
    }
  };

  private static final Function<XlsxBookingTransaction, BookingTransaction> convertFrom =
      dto -> BookingTransaction.builder()
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


