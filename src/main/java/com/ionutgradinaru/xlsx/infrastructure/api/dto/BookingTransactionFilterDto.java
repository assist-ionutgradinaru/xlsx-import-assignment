/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.infrastructure.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ionutgradinaru.xlsx.domain_model.BookingType;
import com.ionutgradinaru.xlsx.domain_model.Product;
import com.ionutgradinaru.xlsx.domain_model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingTransactionFilterDto {

  private Team team;
  private Product product;
  private BookingType bookingType;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate startDate;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate endDate;
}
