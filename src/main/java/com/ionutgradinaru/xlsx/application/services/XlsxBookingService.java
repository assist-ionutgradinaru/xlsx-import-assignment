/*
 * Copyright (c) 2022.
 * Ionut Gradinaru
 * All rights reserved
 */

package com.ionutgradinaru.xlsx.application.services;

import com.ionutgradinaru.xlsx.domain_model.BookingTransaction;

import java.io.InputStream;
import java.util.List;

public interface XlsxBookingService {

  List<BookingTransaction> fromRange(final InputStream inputStream, final String range, final String worksheetName);
}
