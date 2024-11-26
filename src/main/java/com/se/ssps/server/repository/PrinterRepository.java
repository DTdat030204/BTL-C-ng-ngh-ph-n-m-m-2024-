package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.Printer;
import com.se.ssps.server.entity.configuration.Room;

@Repository
public interface PrinterRepository extends MongoRepository<Printer, String> {

    // Truy vấn tìm Printer theo trường 'id'
    @Query("{ 'id' : ?0 }")
    public Printer findPrinterById(String id); // Sửa Integer thành String

    // Cập nhật tên máy in
    @Query("{ 'id' : ?1 }, { '$set': { 'printerName' : ?0 } }")
    public void updateName(String newName, String id); // Sửa Integer thành String

    // Cập nhật lượng mực
    @Query("{ 'id' : ?1 }, { '$set': { 'inkAmount' : ?0 } }")
    public void updateInkAmount(Integer newInkAmount, String id); // Sửa Integer thành String

    // Cập nhật số lượng trang
    @Query("{ 'id' : ?1 }, { '$set': { 'pageAmount' : ?0 } }")
    public void updatePageAmount(Integer newPageAmount, String id); // Sửa Integer thành String

    // Cập nhật hãng máy in
    @Query("{ 'id' : ?1 }, { '$set': { 'firm' : ?0 } }")
    public void updateFirm(String newFirm, String id); // Sửa Integer thành String

    // Cập nhật mô tả
    @Query("{ 'id' : ?1 }, { '$set': { 'description' : ?0 } }")
    public void updateDescription(String newDescription, String id); // Sửa Integer thành String

    // Cập nhật hiệu suất
    @Query("{ 'id' : ?1 }, { '$set': { 'efficiency' : ?0 } }")
    public void updateEfficiency(Integer newEfficiency, String id); // Sửa Integer thành String

    // Cập nhật phòng máy in
    @Query("{ 'id' : ?1 }, { '$set': { 'room' : ?0 } }")
    public void updateRoom(Room newRoom, String id); // Sửa Integer thành String

    // Đánh dấu máy in là đã xóa (xóa logic)
    @Query("{ 'id' : ?0 }, { '$set': { 'isDel' : true } }")
    public void deletePrinter(String id); // Sửa Integer thành String

    // Cập nhật trạng thái máy in
    @Query("{ 'id' : ?1 }, { '$set': { 'status' : ?0 } }")
    public void updateStatus(Boolean newStatus, String id); // Sửa Integer thành String
}

// @Repository
// public interface PrinterRepository extends MongoRepository<Printer, String> {

// // Truy vấn tìm Printer theo trường 'id'
// @Query("{ 'id' : ?0 }")
// public Printer findPrinterById(String id); // Đổi kiểu tham số từ Integer
// sang String

// // Cập nhật tên máy in
// @Query("{ 'id' : ?1 }, { '$set': { 'printerName' : ?0 } }")
// public void updateName(String newName, Integer id);

// // Cập nhật lượng mực
// @Query("{ 'id' : ?1 }, { '$set': { 'inkAmount' : ?0 } }")
// public void updateInkAmount(Integer newInkAmount, Integer id);

// // Cập nhật số lượng trang
// @Query("{ 'id' : ?1 }, { '$set': { 'pageAmount' : ?0 } }")
// public void updatePageAmount(Integer newPageAmount, Integer id);

// // Cập nhật hãng máy in
// @Query("{ 'id' : ?1 }, { '$set': { 'firm' : ?0 } }")
// public void updateFirm(String newFirm, Integer id);

// // Cập nhật mô tả
// @Query("{ 'id' : ?1 }, { '$set': { 'description' : ?0 } }")
// public void updateDescription(String newDescription, Integer id);

// // Cập nhật hiệu suất
// @Query("{ 'id' : ?1 }, { '$set': { 'efficiency' : ?0 } }")
// public void updateEfficiency(Integer newEfficiency, Integer id);

// // Cập nhật phòng máy in
// @Query("{ 'id' : ?1 }, { '$set': { 'room' : ?0 } }")
// public void updateRoom(Room newRoom, Integer id);

// // Đánh dấu máy in là đã xóa (xóa logic)
// @Query("{ 'id' : ?0 }, { '$set': { 'isDel' : true } }")
// public void deletePrinter(Integer id);

// // Cập nhật trạng thái máy in
// @Query("{ 'id' : ?1 }, { '$set': { 'status' : ?0 } }")
// public void updateStatus(Boolean newStatus, Integer id);
// }

/*
 * Đoạn mã này định nghĩa một **repository** trong Spring Data MongoDB có tên là
 * `PrinterRepository`, dùng để thao tác với dữ liệu của đối tượng `Printer`
 * trong cơ sở dữ liệu MongoDB. Các phương thức trong repository này chủ yếu
 * thực hiện các thao tác tìm kiếm và cập nhật thông tin của máy in (`Printer`).
 * 
 * ### Các phương thức trong `PrinterRepository`:
 * 
 * 1. **findPrinterById(String id)**:
 * - Phương thức này tìm kiếm máy in theo trường `id`, và trả về đối tượng
 * `Printer` có `id` khớp với giá trị đã truyền vào.
 * 
 * 2. **updateName(String newName, String id)**:
 * - Phương thức này dùng để **cập nhật tên máy in**. Dữ liệu máy in với `id`
 * tương ứng sẽ được cập nhật trường `printerName` thành `newName`.
 * 
 * 3. **updateInkAmount(Integer newInkAmount, String id)**:
 * - Phương thức này dùng để **cập nhật lượng mực** của máy in. Trường
 * `inkAmount` của máy in có `id` tương ứng sẽ được cập nhật thành
 * `newInkAmount`.
 * 
 * 4. **updatePageAmount(Integer newPageAmount, String id)**:
 * - Phương thức này dùng để **cập nhật số lượng trang** của máy in. Trường
 * `pageAmount` của máy in có `id` tương ứng sẽ được cập nhật thành
 * `newPageAmount`.
 * 
 * 5. **updateFirm(String newFirm, String id)**:
 * - Phương thức này dùng để **cập nhật hãng máy in**. Trường `firm` của máy in
 * có `id` tương ứng sẽ được cập nhật thành `newFirm`.
 * 
 * 6. **updateDescription(String newDescription, String id)**:
 * - Phương thức này dùng để **cập nhật mô tả** của máy in. Trường `description`
 * của máy in có `id` tương ứng sẽ được cập nhật thành `newDescription`.
 * 
 * 7. **updateEfficiency(Integer newEfficiency, String id)**:
 * - Phương thức này dùng để **cập nhật hiệu suất** của máy in. Trường
 * `efficiency` của máy in có `id` tương ứng sẽ được cập nhật thành
 * `newEfficiency`.
 * 
 * 8. **updateRoom(Room newRoom, String id)**:
 * - Phương thức này dùng để **cập nhật phòng của máy in**. Trường `room` của
 * máy in có `id` tương ứng sẽ được cập nhật thành `newRoom`.
 * 
 * 9. **deletePrinter(String id)**:
 * - Phương thức này **đánh dấu máy in là đã xóa** (xóa logic) bằng cách thay
 * đổi giá trị trường `isDel` của máy in có `id` tương ứng thành `true`.
 * 
 * 10. **updateStatus(Boolean newStatus, String id)**:
 * - Phương thức này dùng để **cập nhật trạng thái** của máy in. Trường `status`
 * của máy in có `id` tương ứng sẽ được cập nhật thành `newStatus` (giá trị
 * `true` hoặc `false`).
 * 
 * ### Các điểm cần chú ý:
 * - Tất cả các phương thức trên sử dụng **@Query** để thực hiện các truy vấn
 * trực tiếp với MongoDB, thay vì sử dụng các phương thức của `MongoRepository`
 * mặc định.
 * - Các tham số truyền vào như `id`, `newName`, `newInkAmount`... đều là các
 * giá trị được sử dụng để tìm kiếm và cập nhật thông tin của máy in trong cơ sở
 * dữ liệu.
 * - Các phương thức này đều hoạt động với `String` là kiểu dữ liệu cho trường
 * `id` của máy in (thay vì `Integer` như trong một số đoạn mã trước đó).
 */