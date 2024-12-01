package com.se.ssps.server.service.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ssps.server.dto.PrinterStudentDto;
// import com.se.ssps.server.entity.File;
import com.se.ssps.server.entity.PaymentLog;
import com.se.ssps.server.entity.Printer;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.configuration.FileType;
import com.se.ssps.server.entity.configuration.MaxFileSize;
import com.se.ssps.server.entity.configuration.PageUnitPrice;
import com.se.ssps.server.entity.user.Student;
import com.se.ssps.server.helper.PrinterHelper;
import com.se.ssps.server.repository.FileTypeRepository;
import com.se.ssps.server.repository.PageUnitRepo;
import com.se.ssps.server.repository.PaymentLogRepository;
import com.se.ssps.server.repository.PrinterRepository;
import com.se.ssps.server.repository.PrintingLogRepository;
import com.se.ssps.server.repository.StudentRepository;
import com.se.ssps.server.repository.MaxSizeRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    PageUnitRepo pageUnitRepo;

    @Autowired
    PrinterRepository printerRepository;

    @Autowired
    PrintingLogRepository printingLogRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PaymentLogRepository paymentLogRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private MaxSizeRepository maxSizeRepository;
    
    @Autowired
    private final PrinterHelper printerHelper; // Inject PrinterHelper
    @Autowired
    public StudentServiceImpl(PrinterRepository printerRepository, PrinterHelper printerHelper) {
        this.printerRepository = printerRepository;
        this.printerHelper = printerHelper;
    }

    public Student findStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    // @Override
    // public void addPrintingLog(ArrayList<PrintingLog> printingLog, String printerID, String id) {
    //     for (PrintingLog log : printingLog) {
    //         // Tìm Student
    //         Student student = studentRepository.findById(id)
    //                 .orElseThrow(() -> new RuntimeException("Student not found"));

    //         // Tìm Printer
    //         Printer printer = printerRepository.findById(printerID)
    //                 .orElseThrow(() -> new RuntimeException("Printer not found"));

    //         // Lấy danh sách các file type hợp lệ
    //         List<FileType> allowedFileTypes = fileTypeRepository.findAll();
    //         List<String> allowedExtensions = allowedFileTypes.stream()
    //                 .map(FileType::getFileTypeName)
    //                 .toList();

    //         // Kiểm tra tên file
    //         String fileName = log.getFileName();
    //         if (fileName == null || fileName.isEmpty()) {
    //             throw new RuntimeException("File name cannot be empty.");
    //         }

    //         // Xử lý phần mở rộng file
    //         int lastDotIndex = fileName.lastIndexOf('.');
    //         if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
    //             throw new RuntimeException("File '" + fileName + "' has no valid extension.");
    //         }

    //         // Lấy phần mở rộng file
    //         String fileExtension = fileName.substring(lastDotIndex + 1);

    //         // Kiểm tra phần mở rộng có hợp lệ không
    //         if (!allowedExtensions.contains(fileExtension)) {
    //             throw new RuntimeException("File type '" + fileExtension + "' is not allowed for printing.");
    //         }

    //         // Tính toán số trang và kiểm tra balance
    //         int requiredPages = log.getNumOfPages() * log.getNumOfCopies();
    //         if (student.getBalance() < requiredPages) {
    //             throw new RuntimeException("Insufficient balance. Please purchase more pages.");
    //         }

    //         // Cập nhật balance của Student
    //         int remainingBalance = student.getBalance() - requiredPages;
    //         student.setBalance(remainingBalance);

    //         // Lưu log vào database
    //         printingLogRepository.save(log);

    //         // Lưu lại thông tin Student để cập nhật danh sách printingLogs
    //         student.getPrintingLogs().add(log);
    //         studentRepository.save(student);

    //         // Cập nhật thông tin log
    //         log.setStartDate(LocalDateTime.now());
    //         log.setEndDate(LocalDateTime.now().plusSeconds(requiredPages * 5));
    //         log.setStudent(student);
    //         log.setPrinter(printer);

    //         // Lưu log vào database
    //         printingLogRepository.save(log);

    //         // Cập nhật thông tin Printer
    //         printer.setStatus(false); // Chuyển trạng thái sang "đang sử dụng"
    //         printer.setInkAmount(printer.getInkAmount() - requiredPages / 20);
    //         printer.setPageAmount(printer.getPageAmount() - requiredPages);

    //         printerHelper.schedulePrinterStatusReset(printerID);

    //         if (printer.getInkAmount() <= 0)
    //             printer.setInkAmount(100);
    //         if (printer.getPageAmount() <= 0)
    //             printer.setPageAmount(10000);

    //         // Thêm log vào danh sách
    //         printer.getPrintingLogs().add(log);

    //         // Lưu lại thông tin Printer
    //         printerRepository.save(printer);
    //     }
    // }
    @Override
    public void addPrintingLog(ArrayList<PrintingLog> printingLog, String printerID, String id) {
        for (PrintingLog log : printingLog) {
            // Tìm Student
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh"));

            // Tìm Printer
            Printer printer = printerRepository.findById(printerID)
                    .orElseThrow(() -> new RuntimeException("không tìm thấy máy in"));

            // Lấy danh sách các file type hợp lệ
            List<FileType> allowedFileTypes = fileTypeRepository.findAll();
            List<String> allowedExtensions = allowedFileTypes.stream()
                    .map(FileType::getFileTypeName)
                    .toList();

            // Kiểm tra tên file
            String fileName = log.getFileName();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("File name không được để trống.");
            }

            // Xử lý phần mở rộng file
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
                throw new RuntimeException("File ' " + fileName + "' sai định dạng.");
            }

            // Lấy phần mở rộng file
            String fileExtension = fileName.substring(lastDotIndex + 1);

            // Kiểm tra phần mở rộng có hợp lệ không
            if (!allowedExtensions.contains(fileExtension)) {
                throw new RuntimeException("File type '" + fileExtension + "' không được cho phép in.");
            }

            // **Kiểm tra kích thước file**
            double fileSize = log.getSize();
            MaxFileSize maxFileSize = maxSizeRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new RuntimeException("Max file size configuration not found."));

            if (fileSize > maxFileSize.getValue()) {
                throw new RuntimeException("File size exceeds the maximum allowed limit (" + maxFileSize.getValue() + " MB).");
            }

            // Tính toán số trang và kiểm tra balance
            int requiredPages = log.getNumOfPages() * log.getNumOfCopies();
            if (student.getBalance() < requiredPages) {
                throw new RuntimeException("Insufficient balance. Please purchase more pages.");
            }

            // Cập nhật balance của Student
            int remainingBalance = student.getBalance() - requiredPages;
            student.setBalance(remainingBalance);

            // Lưu log vào database
            printingLogRepository.save(log);

            // Lưu lại thông tin Student để cập nhật danh sách printingLogs
            student.getPrintingLogs().add(log);
            studentRepository.save(student);

            // Cập nhật thông tin log
            log.setStartDate(LocalDateTime.now());
            log.setEndDate(LocalDateTime.now().plusSeconds(requiredPages * 5));
            log.setStudent(student);
            log.setPrinter(printer);

            // Lưu log vào database
            printingLogRepository.save(log);

            // Cập nhật thông tin Printer
            printer.setStatus(false); // Chuyển trạng thái sang "đang sử dụng"
            printer.setInkAmount(printer.getInkAmount() - requiredPages / 20);
            printer.setPageAmount(printer.getPageAmount() - requiredPages);

            printerHelper.schedulePrinterStatusReset(printerID);

            if (printer.getInkAmount() <= 0)
                printer.setInkAmount(100);
            if (printer.getPageAmount() <= 0)
                printer.setPageAmount(10000);

            // Thêm log vào danh sách
            printer.getPrintingLogs().add(log);

            // Lưu lại thông tin Printer
            printerRepository.save(printer);
        }
    }


    // @Override
    // public void addPrintingLog(ArrayList<PrintingLog> printingLog, String printerID, String id) {
    //     for (PrintingLog log : printingLog) {
    //         // Tìm Student
    //         Student student = studentRepository.findById(id)
    //                 .orElseThrow(() -> new RuntimeException("Student not found"));

    //         // Tìm Printer
    //         Printer printer = printerRepository.findById(printerID)
    //                 .orElseThrow(() -> new RuntimeException("Printer not found"));

    //         // Cập nhật balance của Student
    //         int remainingPages = student.getBalance() - log.getNumOfPages() * log.getNumOfCopies();
    //         student.setBalance(remainingPages);

    //         // Lưu log vào database
    //         printingLogRepository.save(log);

    //         // Lưu lại thông tin Student để cập nhật danh sách printingLogs
    //         student.getPrintingLogs().add(log);


    //         studentRepository.save(student);

    //         // Cập nhật thông tin log
    //         log.setStartDate(LocalDateTime.now());
    //         log.setEndDate(LocalDateTime.now().plusSeconds(log.getNumOfPages() * log.getNumOfCopies() * 5));
    //         log.setStudent(student);
    //         log.setPrinter(printer);

    //         // Lưu log vào database
    //         printingLogRepository.save(log);

    //         // Cập nhật thông tin Printer
    //         printer.setInkAmount(printer.getInkAmount() - log.getNumOfPages() * log.getNumOfCopies() / 20);
    //         printer.setPageAmount(printer.getPageAmount() - log.getNumOfPages() * log.getNumOfCopies());

    //         if (printer.getInkAmount() <= 0)
    //             printer.setInkAmount(100);
    //         if (printer.getPageAmount() <= 0)
    //             printer.setPageAmount(10000);

    //         // Thêm log vào danh sách
    //         printer.getPrintingLogs().add(log);
    //         // Lưu lại thông tin Printer
    //         printerRepository.save(printer);
    //     }

    // }

    // @Override
    // public void addPrintingLog(ArrayList<PrintingLog> printingLog, String printerID, String id) {
    //     for (PrintingLog log : printingLog) {
    //         Student student = studentRepository.findById(id)
    //                 .orElseThrow(() -> new RuntimeException("Student not found"));
    //         Printer printer = printerRepository.findById(printerID.toString())
    //                 .orElseThrow(() -> new RuntimeException("Printer not found"));

    //         int remainingPages = student.getBalance() - log.getNumOfPages() * log.getNumOfCopies();
    //         student.setBalance(remainingPages);
    //         studentRepository.save(student);

    //         log.setStartDate(LocalDateTime.now());
    //         log.setEndDate(LocalDateTime.now().plusSeconds(log.getNumOfPages() * log.getNumOfCopies() * 5));
    //         log.setStudent(student);
    //         log.setPrinter(printer);
    //         printingLogRepository.save(log);

    //         printer.setInkAmount(printer.getInkAmount() - log.getNumOfPages() * log.getNumOfCopies() / 20);
    //         printer.setPageAmount(printer.getPageAmount() - log.getNumOfPages() * log.getNumOfCopies());

    //         if (printer.getInkAmount() <= 0)
    //             printer.setInkAmount(100);
    //         if (printer.getPageAmount() <= 0)
    //             printer.setPageAmount(10000);


    //             // Thêm log mới vào danh sách
    //         printer.getPrintingLogs().addAll(printingLog);

    //         // Lưu log vào database
    //         printingLogRepository.saveAll(printingLog);

    //         printerRepository.save(printer);
    //     }
    // }

    @Override
    public List<PrintingLog> listOfPrintingLogs(String studentId) {
        return printingLogRepository.findByStudentId(studentId);
    }

    // @Override
    // public void buyPage(PaymentLog paymentLog, String id) {
    //     Student student = studentRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Student not found"));

    //     student.setBalance(student.getBalance() + paymentLog.getNumOfPages());
    //     studentRepository.save(student);

    //     paymentLog.setStudent(student);
    //     paymentLog.setUnitPrice(pageUnitRepo.getValue());
    //     paymentLog.setPayDate(LocalDateTime.now());
    //     paymentLogRepository.save(paymentLog);
    // }

    @Override
    public List<PaymentLog> listOfPaymentLogs(String id) {
        return paymentLogRepository.findAll();
    }

    @Override
    public Student getStudentInfo(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }




    @Override
    public Student registerStudent(Student student) throws Exception {
        // Kiểm tra nếu username đã tồn tại
        if (studentRepository.findByUsername(student.getUsername()) != null) {
            throw new Exception("Username already exists!");
        }

        // Thêm student mới vào cơ sở dữ liệu
        return studentRepository.save(student);
    }


    @Override
    public void buyPage(PaymentLog paymentLog, String id) {
        // Tìm kiếm thông tin student
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Cập nhật số giấy dư của student
        student.setBalance(student.getBalance() + paymentLog.getNumOfPages());

        // Lấy giá trị price từ PageUnitPrice với id là "1"
        PageUnitPrice pageUnitPrice = pageUnitRepo.findById("1")
                .orElseThrow(() -> new RuntimeException("PageUnitPrice not found"));

          // Tính toán số tiền cần thanh toán
        int amountToAdd = paymentLog.getNumOfPages() * pageUnitPrice.getValue();
        student.setOutstandingAmount(student.getOutstandingAmount() + amountToAdd);

        // Thiết lập giá trị unitPrice cho paymentLog
        paymentLog.setStudent(student);
        paymentLog.setUnitPrice(pageUnitPrice.getValue());

        // Lưu paymentLog
        paymentLog.setPayDate(LocalDateTime.now());
        paymentLogRepository.save(paymentLog);

        // Thêm PaymentLog vào danh sách paymentLogs của Student
        student.getPaymentLogs().add(paymentLog);

        // Lưu lại thông tin Student để cập nhật danh sách paymentLogs
        studentRepository.save(student);
    }

//     @Override
// public void buyPage(PaymentLog paymentLog, String id) {
//     // Tìm kiếm thông tin student
//     Student student = studentRepository.findById(id)
//             .orElseThrow(() -> new RuntimeException("Student not found"));

//     // Cập nhật số dư của student
//     student.setBalance(student.getBalance() + paymentLog.getNumOfPages());
//     studentRepository.save(student);

//     // Lấy giá trị price từ PageUnitPrice với id là "1"
//     PageUnitPrice pageUnitPrice = pageUnitRepo.findById("1")
//             .orElseThrow(() -> new RuntimeException("PageUnitPrice not found"));

//     // Thiết lập giá trị unitPrice cho paymentLog
//     paymentLog.setStudent(student);
//     paymentLog.setUnitPrice(pageUnitPrice.getValue());
    
//     // Lưu paymentLog
//     paymentLog.setPayDate(LocalDateTime.now());
//     paymentLogRepository.save(paymentLog);
// }

// Phương thức để cập nhật giá trị price của PageUnitPrice
public void updatePrice(String id, Integer newPrice) {
    // Lấy đối tượng PageUnitPrice theo id
    PageUnitPrice pageUnitPrice = pageUnitRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("PageUnitPrice not found"));

    // Cập nhật giá trị price
    pageUnitPrice.setPrice(newPrice);

    // Lưu lại vào MongoDB
    pageUnitRepo.save(pageUnitPrice);
}

    @Override
    public List<PrinterStudentDto> findAllPrinterForStudents() {
        // Lấy tất cả máy in
        List<Printer> printers = printerRepository.findAll();
        // Chuyển đổi sang DTO chỉ chứa thông tin cần thiết
        return printers.stream()
                .map(printer -> new PrinterStudentDto(
                        printer.getStatus(),
                        printer.getId(),
                        printer.getRoom() != null ? printer.getRoom().getRoomName() : "Unknown" // Kiểm tra null để tránh lỗi

                ))
                .collect(Collectors.toList());
    }

    
    @Override
    public boolean isUsernameTaken(String username) {
        return studentRepository.existsByUsername(username);
    }

    @Override
    public boolean isStudentNumberTaken(Long studentNumber) {
        return studentRepository.existsByStudentNumber(studentNumber);
    }

}
