package com.se.ssps.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.se.ssps.server.dto.PrinterStudentDto;
import com.se.ssps.server.entity.PaymentLog;
// import com.se.ssps.server.entity.File;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.user.Student;
import com.se.ssps.server.helper.ApiResponse;
import com.se.ssps.server.service.user.StudentService;

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
}
