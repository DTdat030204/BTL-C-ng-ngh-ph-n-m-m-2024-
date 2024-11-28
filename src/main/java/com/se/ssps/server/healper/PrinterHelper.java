package com.se.ssps.server.healper;

import com.se.ssps.server.repository.PrinterRepository;
import com.se.ssps.server.entity.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component // Đánh dấu là Spring bean
public class PrinterHelper {

    private final PrinterRepository printerRepository;

    @Autowired // Inject PrinterRepository thông qua constructor
    public PrinterHelper(PrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
    }

    // Phương thức để lên lịch reset trạng thái máy in
    public void schedulePrinterStatusReset(String printerId) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            // Tìm lại Printer theo ID và reset trạng thái về true
            Printer printer = printerRepository.findById(printerId)
                    .orElseThrow(() -> new RuntimeException("Printer not found for reset."));
            printer.setStatus(true); // Reset trạng thái về true
            printerRepository.save(printer);
        }, 1, TimeUnit.MINUTES); // Thực hiện sau 1 phút (hoặc tùy chỉnh)
    }
}
