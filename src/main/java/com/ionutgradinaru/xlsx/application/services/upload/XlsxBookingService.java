package com.ionutgradinaru.xlsx.application.services.upload;

import java.util.List;

public interface XlsxBookingService {

  List<XlsxBookingTransaction> fromRange(final String range);
}
