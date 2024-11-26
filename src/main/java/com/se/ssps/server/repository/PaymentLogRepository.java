package com.se.ssps.server.repository;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.PaymentLog;

@Repository
public interface PaymentLogRepository extends MongoRepository<PaymentLog, Integer> {

    // Truy vấn để tính tổng số trang trong khoảng thời gian từ 'from' đến 'to'
    @Query("{ 'payDate': { $gte: ?0, $lte: ?1 } }")
    public Integer countPageNums(LocalDateTime from, LocalDateTime to);
}


/*
 * Đoạn mã này định nghĩa một **repository** trong Spring Data MongoDB, có tên
 * `PaymentLogRepository`, dùng để thao tác với các dữ liệu của đối tượng
 * `PaymentLog` trong cơ sở dữ liệu MongoDB.
 * 
 * ### Phương thức trong `PaymentLogRepository`:
 * 
 * ### 1. **countPageNums(LocalDateTime from, LocalDateTime to)**:
 * - Phương thức này dùng để **tính tổng số trang** (hoặc số lượng trang) trong
 * một khoảng thời gian nhất định, được xác định bởi hai tham số đầu vào: `from`
 * và `to` (kiểu `LocalDateTime`).
 * 
 * - **@Query("{ 'payDate': { $gte: ?0, $lte: ?1 } }")**:
 * - Truy vấn MongoDB này tìm tất cả các bản ghi trong cơ sở dữ liệu có trường
 * `payDate` nằm trong khoảng thời gian từ `from` đến `to`.
 * - `?0` và `?1` là tham số truyền vào phương thức, tương ứng với `from` và
 * `to`. Cụ thể:
 * - `$gte: ?0`: Điều kiện tìm kiếm các bản ghi có trường `payDate` lớn hơn hoặc
 * bằng giá trị `from`.
 * - `$lte: ?1`: Điều kiện tìm kiếm các bản ghi có trường `payDate` nhỏ hơn hoặc
 * bằng giá trị `to`.
 * 
 * - Phương thức này trả về một giá trị kiểu `Integer`, có thể là tổng số trang
 * (hoặc số lượng trang) từ các bản ghi `PaymentLog` trong khoảng thời gian đã
 * cho.
 * 
 * ### Tóm lại:
 * Phương thức **countPageNums** được sử dụng để tính tổng số trang (hoặc số
 * lượng trang) dựa trên trường `payDate` trong đối tượng `PaymentLog`, với điều
 * kiện là thời gian của `payDate` nằm trong khoảng từ `from` đến `to`.
 */