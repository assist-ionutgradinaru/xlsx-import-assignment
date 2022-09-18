package com.ionutgradinaru.xlsx.application.services.upload;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import java.util.Date;

@Data
public class XlsxBookingTransaction {

  @ExcelCellName("CustomerName")
  String customerName;
  @ExcelCellName("BookingDate")
  Date bookingDate;
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
