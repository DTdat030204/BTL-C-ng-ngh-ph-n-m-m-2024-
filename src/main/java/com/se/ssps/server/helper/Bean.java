package com.se.ssps.server.helper;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import com.se.ssps.server.entity.configuration.PageAllocation;
import com.se.ssps.server.repository.PageAllocationRepository;
import com.se.ssps.server.repository.StudentRepository;
import com.se.ssps.server.entity.user.Student;

import java.util.List;

@Component
public class Bean {

    private final PageAllocationRepository pageAllocationRepository;
    private final StudentRepository studentRepository;

    public Bean(PageAllocationRepository pageAllocationRepository,
            StudentRepository studentRepository) {
        this.pageAllocationRepository = pageAllocationRepository;
        this.studentRepository = studentRepository;
    }

    // **************************************************** CÁI NÀY CÓ GHI
    // CHÚ*********************/
    // @Scheduled(cron = "0 22 9 * * ?") // Lịch chạy vào 9h sáng mỗi ngày
    // public void allocatePagesForNewSemester() {
    //     Integer defaultPages = 100; // Số trang mặc định
    //     Integer semester = LocalDate.now().getMonthValue() <= 6 ? 1 : 2; // Học kỳ 1 hoặc 2
    //     Integer year = LocalDate.now().getYear();

    //     // Lưu bản ghi cấp phát mới (không cần kiểm tra điều kiện)
    //     PageAllocation newAllocation = new PageAllocation(semester, year, LocalDate.now(), defaultPages, true);
    //     pageAllocationRepository.save(newAllocation);

    //     // Cộng số trang cho từng sinh viên
    //     List<Student> students = studentRepository.findAll();
    //     for (Student student : students) {
    //         student.setBalance(student.getBalance() + defaultPages);
    //         studentRepository.save(student);
    //     }
    // }
    @Scheduled(cron = "0 22 9 * * ?") // Lịch chạy vào 9h sáng mỗi ngày
    public void allocatePagesForNewSemester() {
        Integer defaultPagesA3 = 50; // Số trang mặc định cho A3
        Integer defaultPagesA4 = 50; // Số trang mặc định cho A4
        Integer semester = LocalDate.now().getMonthValue() <= 6 ? 1 : 2; // Học kỳ 1 hoặc 2
        Integer year = LocalDate.now().getYear();

        // Lưu bản ghi cấp phát mới (không cần kiểm tra điều kiện)
        PageAllocation newAllocation = new PageAllocation(semester, year, LocalDate.now(), defaultPagesA3, true);
        pageAllocationRepository.save(newAllocation);

        // Cộng số trang cho từng sinh viên
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setNumA3Pages(student.getNumA3Pages() + defaultPagesA3); // Cộng số trang A3
            student.setNumA4Pages(student.getNumA4Pages() + defaultPagesA4); // Cộng số trang A4
            studentRepository.save(student);
        }
    }

}
