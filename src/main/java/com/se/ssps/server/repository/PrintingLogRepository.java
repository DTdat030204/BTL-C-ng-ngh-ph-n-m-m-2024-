package com.se.ssps.server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.PageSize;
import com.se.ssps.server.entity.PrintingLog;

@Repository
public interface PrintingLogRepository extends MongoRepository<PrintingLog, String> {

    // Phương thức tìm các bản ghi PrintingLog dựa trên studentId
    public List<PrintingLog> findByStudentId(String studentId);
    
    // Tổng diện tích in (squarePrinting) của một máy in theo printerId
    @Query("{ 'printer.id': ?0 }")
    public Double totalSquare(String printerId);

    // Tổng diện tích in theo thời gian và printerId
    @Query("{ 'printer.id': ?0, 'startDate': { $gte: ?1 }, 'endDate': { $lte: ?2 } }")
    public Double sumPageNum(String printerId, LocalDateTime from, LocalDateTime to);

    // Tổng số yêu cầu in trong khoảng thời gian
    @Query("{ 'startDate': { $gte: ?0 }, 'endDate': { $lte: ?1 } }")
    public Integer sumOfRequest(LocalDateTime from, LocalDateTime to);

    // Đếm số yêu cầu in theo printerId và khoảng thời gian
    @Query("{ 'printer.id': ?0, 'startDate': { $gte: ?1 }, 'endDate': { $lte: ?2 } }")
    public Integer countRequestById(String printerId, LocalDateTime from, LocalDateTime to);

    // Đếm số lượng in theo kích thước trang (pageSize) và khoảng thời gian
    @Query("{ 'pageSize': ?0, 'startDate': { $gte: ?1 }, 'endDate': { $lte: ?2 } }")
    public Integer countPageSize(PageSize pageSize, LocalDateTime from, LocalDateTime to);

    @Query("{ 'startDate': { $gte: ?0 }, 'endDate': { $lte: ?1 } }")
    List<PrintingLog> findAllLogsBetween(LocalDateTime from, LocalDateTime to);

    
}







/*
 * Đây là một **repository** trong Spring Data MongoDB, có tên
 * `PrintingLogRepository`, dùng để thao tác với dữ liệu trong bảng
 * `PrintingLog` của cơ sở dữ liệu MongoDB. Repository này kế thừa từ
 * `MongoRepository<PrintingLog, String>`, giúp tự động thực hiện các thao tác
 * CRUD cơ bản mà không cần phải viết thêm mã.
 * 
 * Dưới đây là các phương thức và chức năng của repository này:
 * 
 * ### 1. **findByStudentId(Integer studentId)**:
 * - Tìm tất cả các bản ghi `PrintingLog` dựa trên `studentId`.
 * - Phương thức này không sử dụng `@Query` mà Spring Data MongoDB tự động tạo
 * truy vấn từ tên phương thức.
 * 
 * ### 2. **totalSquare(String printerId)**:
 * - Tính tổng diện tích in (tổng giá trị của trường `squarePrinting`) của một
 * máy in dựa trên `printerId`.
 * - Sử dụng **@Query** để truy vấn các bản ghi có trường `printer.id` bằng
 * `printerId` và tính tổng diện tích.
 * 
 * ### 3. **sumPageNum(String printerId, LocalDateTime from, LocalDateTime
 * to)**:
 * - Tính tổng số trang in (tổng giá trị của `pageNum`) cho một máy in trong
 * khoảng thời gian từ `from` đến `to`.
 * - Phương thức này sử dụng **@Query** để tìm các bản ghi có `printer.id` là
 * `printerId` và thời gian bắt đầu (`startDate`) và kết thúc (`endDate`) trong
 * khoảng thời gian chỉ định.
 * 
 * ### 4. **sumOfRequest(LocalDateTime from, LocalDateTime to)**:
 * - Tính tổng số yêu cầu in trong một khoảng thời gian từ `from` đến `to`.
 * - Truy vấn này không có điều kiện theo máy in mà chỉ dựa vào khoảng thời
 * gian.
 * 
 * ### 5. **countRequestById(String printerId, LocalDateTime from, LocalDateTime
 * to)**:
 * - Đếm số yêu cầu in của một máy in cụ thể trong khoảng thời gian từ `from`
 * đến `to`.
 * - Truy vấn này dựa vào `printer.id` và thời gian.
 * 
 * ### 6. **countPageSize(PageSize pageSize, LocalDateTime from, LocalDateTime
 * to)**:
 * - Đếm số lượng yêu cầu in với kích thước trang cụ thể (trường `pageSize`)
 * trong khoảng thời gian từ `from` đến `to`.
 * - Truy vấn này tìm các bản ghi có `pageSize` tương ứng và thời gian nằm trong
 * khoảng cho trước.
 * 
 * ### Các điểm cần lưu ý:
 * - **@Query** cho phép bạn viết các câu truy vấn MongoDB bằng cú pháp JSON.
 * - Các phương thức này hỗ trợ các tính năng như tìm kiếm theo `printerId`,
 * tính tổng diện tích in, đếm yêu cầu in theo các tiêu chí như kích thước trang
 * và thời gian.
 * - **LocalDateTime** được sử dụng để xác định thời gian bắt đầu và kết thúc
 * trong các truy vấn.
 * 
 * Như vậy, repository này cung cấp các phương thức hữu ích để truy vấn thông
 * tin chi tiết về các bản ghi `PrintingLog`, bao gồm các phép tính tổng diện
 * tích, số lượng trang, và yêu cầu in theo nhiều tiêu chí khác nhau.
 */