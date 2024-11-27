package com.se.ssps.server.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// import com.se.ssps.server.entity.File;
import com.se.ssps.server.entity.PaymentLog;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.user.Student;

@Service
public interface StudentService {
    public Student getStudentInfo(String id);

    // ==============================================================================================
    // ==============================================================================================
    // Thao tác đối với việc in tài liệu
    public void addPrintingLog(ArrayList<PrintingLog> printingLog, String printerID, String id);

    public List<PrintingLog> listOfPrintingLogs(String id);

    // ==============================================================================================
    // ==============================================================================================
    // Thao tác mua trang in
    public void buyPage(PaymentLog paymentLog, String id);

    public List<PaymentLog> listOfPaymentLogs(String id);
    
    public Student registerStudent(Student student) throws Exception; // Đăng ký student mới

    public Student findStudentByUsername(String username);
    
}
