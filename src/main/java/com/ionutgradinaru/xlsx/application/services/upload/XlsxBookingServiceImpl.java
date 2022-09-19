package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.common.utils.XlsxDataRange;
import com.ionutgradinaru.xlsx.common.utils.XlsxHelper;
import com.ionutgradinaru.xlsx.domain_model.*;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
public class XlsxBookingServiceImpl implements XlsxBookingService {

  @SneakyThrows
  @Override
  public List<BookingTransaction> fromRange(final MultipartFile file, final String range, final String worksheetName) {
    var xlsxRange = XlsxHelper.getDataRange(range);
    var xlsxOptions = getXlsxOptions(worksheetName, xlsxRange);

    return Poiji.fromExcel(
            file.getInputStream(),
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
        .headerStart(XlsxHelper.HEADER_INDEX_START)
        .datePattern(XlsxHelper.DATE_FORMAT)
        .disableXLSXNumberCellFormat();

    var skipRows = XlsxHelper.skipRows(xlsxRange);
    if (skipRows > 0) {
      options.skip(skipRows);
    }

    var limitRows = XlsxHelper.limitRows(xlsxRange);
    if (limitRows > 0) {
      options.limit(limitRows);
    }

    return options.build();
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

  private static final Function<XlsxTransactionDto, BookingTransaction> convertToBookingTransaction =
      dto -> BookingTransaction.builder()
          .customerName(dto.getCustomerName())
          .bookingDate(dto.getBookingDate())
          .opportunityId(dto.getOpportunityId())
          .bookingType(BookingType.getFromString(dto.getBookingType()))
          .total(convertStringToDouble.applyAsDouble(dto.getTotal()))
          .accountExecutive(dto.getAccountExecutive())
          .saleOrganization(SaleOrganization.getFromString(dto.getSaleOrganization()))
          .team(Team.getFromString(dto.getTeam()))
          .product(Product.getFromString(dto.getProduct()))
          .renewable(Boolean.valueOf(dto.getRenewable().toLowerCase()))
          .build();
}
