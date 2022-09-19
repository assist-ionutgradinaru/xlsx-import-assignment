/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ionutgradinaru.xlsx.domain_model.BookingType;
import com.ionutgradinaru.xlsx.domain_model.Product;
import com.ionutgradinaru.xlsx.domain_model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingTransactionFilter {

  private Team team;
  private Product product;
  private BookingType bookingType;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date startDate;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date endDate;
}
