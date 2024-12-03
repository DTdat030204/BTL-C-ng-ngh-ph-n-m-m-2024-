package com.se.ssps.server.controller;

//import java.io.IOException;
// import java.io.File;
// import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.gridfs.GridFsResource;
// import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.mongodb.client.gridfs.GridFSBucket;
// import com.mongodb.client.gridfs.model.GridFSFile;
import com.se.ssps.server.dto.PrinterStudentDto;
import com.se.ssps.server.entity.PaymentLog;
// import com.se.ssps.server.entity.File;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.user.Student;
import com.se.ssps.server.helper.ApiResponse;
import com.se.ssps.server.service.user.StudentService;
//import org.springframework.data.mongodb.repository.Query;
// import jakarta.servlet.http.HttpServletResponse;
//import org.apache.commons.io.IOUtils;
// import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Criteria;


@RequestMapping("/student/{id}")
@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentService studentService;

    // public void printDoc(@RequestBody ArrayList<PrintingLog> printingLog,
    // @RequestParam(name = "printer-id") String printerID,
    // @PathVariable String id) {
    // studentService.addPrintingLog(printingLog, printerID, id);

    // }
    // @PostMapping("/print")
    // public ResponseEntity<?> printDoc(
    // @RequestBody ArrayList<PrintingLog> printingLog,
    // @RequestParam(name = "printer-id") String printerID,
    // @PathVariable String id) {
    // try {
    // studentService.addPrintingLog(printingLog, printerID, id);
    // return ResponseEntity.ok().body("Documents printed successfully.");
    // } catch (RuntimeException e) {
    // return ResponseEntity
    // .badRequest()
    // .body(e.getMessage());
    // }
    // }

    @PostMapping("/print")
    public ResponseEntity<ApiResponse> printDoc(
            @RequestBody ArrayList<PrintingLog> printingLog,
            @RequestParam(name = "printer-id") String printerID,
            @PathVariable String id) {
        try {

            studentService.addPrintingLog(printingLog, printerID, id);
            ApiResponse response = new ApiResponse(
                    "success",
                    "In thành công.",
                    printingLog);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Lỗi: " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }
    }

    @GetMapping("/printing-logs")
    public List<PrintingLog> listOfPrintingLogs(@PathVariable String id) {
        return studentService.listOfPrintingLogs(id);
    }

    // @PostMapping("/buy-pages")
    // public void buyPages(@RequestBody PaymentLog payment, @PathVariable String
    // id) {
    // studentService.buyPage(payment, id);
    // }
    // @PostMapping("/buy-pages")
    // public ResponseEntity<String> buyPages(@RequestBody PaymentLog payment,
    // @PathVariable String id) {
    // studentService.buyPage(payment, id); // Thực hiện logic mua trang
    // return ResponseEntity.ok("Mua thành công"); // Luôn trả về thông báo thành
    // công
    // }

    // @PostMapping("/buy-pages")
    // public ResponseEntity<ApiResponse> buyPages(@RequestBody PaymentLog payment, @PathVariable String id) {
    //     try {
    //         studentService.buyPage(payment, id);
    //         ApiResponse response = new ApiResponse(
    //                 "success",
    //                 "Mua trang thành công",
    //                 payment);
    //         return ResponseEntity.ok(response);
    //     } catch (RuntimeException e) {
    //         ApiResponse errorResponse = new ApiResponse(
    //                 "error",
    //                 "Lỗi trong quá trình mua trang: " + e.getMessage(),
    //                 null);
    //         return ResponseEntity
    //                 .badRequest()
    //                 .body(errorResponse);
    //     }
    // }
    @PostMapping("/buy-pages")
    public ResponseEntity<ApiResponse> buyPages(@RequestBody PaymentLog payment, @PathVariable String id) {
        try {
            studentService.buyPage(payment, id);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Mua trang thành công",
                    payment);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Lỗi trong quá trình mua trang: " + e.getMessage(),
                    null);
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }
    }

    @GetMapping("/payment-logs")
    public List<PaymentLog> listOfPaymentLogs(@PathVariable String id) {
        return studentService.listOfPaymentLogs(id);
    }

    @GetMapping("/info")
    public Student studentInfo(@PathVariable String id) {
        return studentService.getStudentInfo(id);
    }

    @GetMapping("/printers")
    public List<PrinterStudentDto> getPrintersForStudents() {
        return studentService.findAllPrinterForStudents();
    }

    //     @Autowired
    //     private GridFsTemplate gridFsTemplate;

    //     // @Autowired
    //     // private GridFSBucket gridFSBucket;

    //     @PostMapping("/print")
    // public ResponseEntity<ApiResponse> printDoc(
    //         @RequestPart("file") MultipartFile file,
    //         @RequestPart("printingLog") ArrayList<PrintingLog> printingLog,
    //         @RequestParam(name = "printer-id") String printerID,
    //         @PathVariable String id) {
    //     try {
    //         // 1. Kiểm tra tính hợp lệ của file
    //         validateFile(file);

    //         // 2. Lưu file vào GridFS
    //         ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
    //                 file.getContentType());

    //         // 3. Ghi log in ấn vào cơ sở dữ liệu
    //         studentService.addPrintingLog(printingLog, printerID, id);

    //         // 4. Tạo phản hồi
    //         ApiResponse response = new ApiResponse(
    //                 "success",
    //                 "File uploaded successfully and print log saved. File ID: " + fileId.toString(),
    //                 printingLog);

    //         return ResponseEntity.ok(response);

    //     } catch (RuntimeException e) {
    //         ApiResponse errorResponse = new ApiResponse(
    //                 "error",
    //                 "Error: " + e.getMessage());
    //         return ResponseEntity
    //                 .badRequest()
    //                 .body(errorResponse);
    //     } catch (IOException e) {
    //         ApiResponse errorResponse = new ApiResponse(
    //                 "error",
    //                 "File processing error: " + e.getMessage());
    //         return ResponseEntity
    //                 .badRequest()
    //                 .body(errorResponse);
    //     }
    // }

    // private void validateFile(MultipartFile file) throws RuntimeException {
    //     if (file.isEmpty()) {
    //         throw new RuntimeException("File is empty");
    //     }
    //     if (file.getSize() > 10 * 1024 * 1024) { // Giới hạn 10MB
    //         throw new RuntimeException("File size exceeds 10MB");
    //     }
    //     String contentType = file.getContentType();
    //     if (!List.of("image/png", "image/jpeg", "application/pdf").contains(contentType)) {
    //         throw new RuntimeException("Unsupported file type: " + contentType);
    //     }
    // }

}
