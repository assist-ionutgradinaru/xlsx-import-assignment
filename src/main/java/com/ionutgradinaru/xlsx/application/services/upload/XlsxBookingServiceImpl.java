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
    var xlsxRange = XlsxHelper.from(range);
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
    var skipRows = XlsxHelper.skipRows(xlsxRange);
    var limitRows = XlsxHelper.limitRows(xlsxRange);

    return PoijiOptions.PoijiOptionsBuilder
        .settings()
        .sheetName(worksheet)
        .headerStart(XlsxHelper.HEADER_INDEX_START)
        .skip(skipRows)
        .limit(limitRows)
        .datePattern(XlsxHelper.DATE_FORMAT)
        .disableXLSXNumberCellFormat()
        .build();
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
          .bookingType(BookingType.valueOf(dto.getBookingType()))
          .total(convertStringToDouble.applyAsDouble(dto.getTotal()))
          .accountExecutive(dto.getAccountExecutive())
          .saleOrganization(SaleOrganization.valueOf(dto.getSaleOrganization()))
          .team(Team.valueOf(dto.getTeam()))
          .product(Product.valueOf(dto.getProduct()))
          .renewable(Boolean.valueOf(dto.getRenewable()))
          .build();
}
