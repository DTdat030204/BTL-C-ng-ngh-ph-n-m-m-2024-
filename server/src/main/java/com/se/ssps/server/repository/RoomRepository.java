package com.se.ssps.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.ssps.server.entity.Printer;
import com.se.ssps.server.entity.configuration.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

    @Transactional
    @Query("{'id': ?0}")
    public void savePrinter(Printer newPrinter, Integer id);

    @Query("{'id': ?0}")
    public Room findRoomById(Integer id);

    @Transactional
    @Query("{'id': ?0}")
    public void roomHavePrinter(Integer roomId);

    @Query("{'roomName': ?0}")
    Room findByRoomName(String roomName);

    @Query("{ 'building.$id': ?0 }") // Tìm Room theo ID của Building
    List<Room> findByBuildingId(String buildingId);
}

/*
 * Đây là một **repository** trong Spring Data MongoDB, có tên là
 * `RoomRepository`, dùng để thao tác với dữ liệu trong bảng `Room` của cơ sở dữ
 * liệu MongoDB. Repository này kế thừa từ `MongoRepository<Room, Integer>`,
 * giúp tự động thực hiện các thao tác CRUD cơ bản mà không cần phải viết thêm
 * mã.
 * 
 * ### Các phương thức và chức năng của `RoomRepository`:
 * 
 * ### 1. **savePrinter(Printer newPrinter, Integer id)**:
 * - **@Transactional**: Đánh dấu phương thức này là một giao dịch
 * (transaction), đảm bảo rằng tất cả các thao tác trong phương thức sẽ được
 * thực hiện thành công hoặc không thực hiện gì cả nếu có lỗi xảy ra.
 * - **@Query("{'id': ?0}")**: Đây là một truy vấn MongoDB, tìm kiếm đối tượng
 * `Room` theo trường `id` và sau đó thực hiện thao tác lưu (`save`) một đối
 * tượng `Printer` vào `Room` dựa trên `id` của phòng.
 * - Phương thức này có thể được sử dụng để lưu thông tin máy in vào một phòng
 * cụ thể.
 * 
 * ### 2. **findRoomById(Integer id)**:
 * - Phương thức này tìm kiếm một đối tượng `Room` theo `id` của phòng.
 * - Không sử dụng **@Transactional** vì đây chỉ là một truy vấn đọc dữ liệu từ
 * cơ sở dữ liệu.
 * 
 * ### 3. **roomHavePrinter(Integer roomId)**:
 * - **@Transactional**: Phương thức này cũng được đánh dấu là một giao dịch, có
 * thể sẽ thực hiện các thao tác liên quan đến việc cập nhật dữ liệu trong cơ sở
 * dữ liệu.
 * - **@Query("{'id': ?0}")**: Truy vấn tìm kiếm phòng theo `roomId`, và có thể
 * là để kiểm tra hoặc thực hiện một thao tác với phòng có `roomId` đó, ví dụ
 * như xác định xem phòng đó có chứa máy in hay không.
 * 
 * ### Các điểm cần lưu ý:
 * - **@Transactional**: Được sử dụng cho những phương thức có thao tác thay đổi
 * dữ liệu trong cơ sở dữ liệu để đảm bảo tính toàn vẹn của giao dịch.
 * - **@Query**: Sử dụng để viết truy vấn MongoDB trực tiếp với cú pháp JSON,
 * giúp tìm kiếm hoặc thay đổi dữ liệu dựa trên các điều kiện cụ thể.
 * - Repository này thực hiện các thao tác liên quan đến phòng (`Room`), chẳng
 * hạn như tìm kiếm phòng theo `id` và thực hiện thao tác liên quan đến máy in
 * trong phòng đó.
 * 
 * Như vậy, `RoomRepository` cung cấp các phương thức để thao tác với dữ liệu
 * phòng trong cơ sở dữ liệu MongoDB, đặc biệt là các thao tác liên quan đến máy
 * in và việc kiểm tra, lưu trữ thông tin máy in trong các phòng cụ thể.
 */