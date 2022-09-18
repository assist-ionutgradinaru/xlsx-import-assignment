package com.ionutgradinaru.xlsx.application.services.upload;

import com.ionutgradinaru.xlsx.application.repositories.ReactiveBookingTransactionRepo;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

  final ReactiveBookingTransactionRepo bookingTransactionRepo;

  @Autowired
  public UploadServiceImpl(final ReactiveBookingTransactionRepo bookingTransactionRepo) {
    this.bookingTransactionRepo = bookingTransactionRepo;
  }

  @SneakyThrows
  @Override
  public void upload(final MultipartFile file, final String range, final String worksheetName) {
    var options = PoijiOptions.PoijiOptionsBuilder
        .settings()
        .headerStart(2) // header on line 3
        .limit(10) // first 10 lines
        .datePattern("dd/MM/yyyy")
        .disableXLSXNumberCellFormat()
        .build();

    var transactions = Poiji.fromExcel(
        file.getInputStream(),
        PoijiExcelType.XLSX,
        XlsxBookingTransaction.class,
        options
    );

    transactions.forEach(System.out::println);
  }
}
