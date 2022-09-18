package com.ionutgradinaru.xlsx.application.services.upload;

import java.util.List;

public interface XlsxBookingTransactionService {

  List<XlsxBookingTransaction> fromRange(final String range);
}
