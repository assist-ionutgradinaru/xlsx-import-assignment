/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.*;
import com.ionutgradinaru.xlsx.utils.XlsxDataRange;
import com.ionutgradinaru.xlsx.utils.XlsxHelper;
import com.poiji.annotation.ExcelCellName;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class XlsxBookingServiceImpl implements XlsxBookingService {

  @SneakyThrows
  @Override
  public List<BookingTransaction> fromRange(final InputStream inputStream,
                                            final String range,
                                            final String worksheetName) {

    var xlsxRange = XlsxHelper.getDataRange.apply(range);
    var xlsxOptions = getXlsxOptions(worksheetName, xlsxRange);

    return Poiji.fromExcel(
            inputStream,
            PoijiExcelType.XLSX,
            XlsxTransactionDto.class,
            xlsxOptions
        ).stream()
        .map(convertToBookingTransaction)
        .collect(Collectors.toList());
  }

  private PoijiOptions getXlsxOptions(final String worksheet, final XlsxDataRange xlsxRange) {
    var options = PoijiOptions.PoijiOptionsBuilder
        .settings()
        .sheetName(worksheet)
        .headerStart(XlsxHelper.HEADER_INDEX_START);

    var skipRows = XlsxHelper.skipRows.applyAsInt(xlsxRange);
    if (skipRows > 0) {
      options.skip(skipRows);
    }

    var limitRows = XlsxHelper.limitRows.applyAsInt(xlsxRange);
    if (limitRows > 0) {
      options.limit(limitRows);
    }

    return options.build();
  }

  private static final Function<XlsxTransactionDto, BookingTransaction> convertToBookingTransaction =
      dto -> BookingTransaction.builder()
          .customerName(dto.getCustomerName())
          .bookingDate(XlsxHelper.convertStringToDate.apply(dto.bookingDate))
          .opportunityId(dto.getOpportunityId())
          .bookingType(BookingType.getFromString(dto.getBookingType()))
          .total(XlsxHelper.convertCurrencyStringToDouble.applyAsDouble(dto.getTotal()))
          .accountExecutive(dto.getAccountExecutive())
          .saleOrganization(SaleOrganization.getFromString(dto.getSaleOrganization()))
          .team(Team.getFromString(dto.getTeam()))
          .product(Product.getFromString(dto.getProduct()))
          .renewable(Objects.equals("YES", dto.getRenewable()) ? Boolean.TRUE : Boolean.FALSE)
          .build();
}

@Data
class XlsxTransactionDto {

  @ExcelCellName("CustomerName")
  String customerName;
  @ExcelCellName("BookingDate")
  String bookingDate;
  @ExcelCellName("OpportunityID")
  String opportunityId;
  @ExcelCellName("BookingType")
  String bookingType;
  @ExcelCellName("Total")
  String total;
  @ExcelCellName("AccountExecutive")
  String accountExecutive;
  @ExcelCellName("SalesOrganization")
  String saleOrganization;
  @ExcelCellName("Team")
  String team;
  @ExcelCellName("Product")
  String product;
  @ExcelCellName("Renewable")
  String renewable;
}
