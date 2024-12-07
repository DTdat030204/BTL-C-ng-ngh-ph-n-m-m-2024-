package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.configuration.Campus;

@Repository
public interface CampusRepository extends MongoRepository<Campus, String> {

    // Truy vấn Campus bằng ID
    @Query("{ '_id': ?0 }")
    public Campus findCampusById(String id);

    // Truy vấn Campus bằng campusName
    @Query("{ 'campusName': ?0 }")
    public Campus findByCampusName(String campusName);

}

/*
 * Lớp `CampusRepository` của bạn được định nghĩa để thực hiện các thao tác CRUD
 * với MongoDB đối với entity `Campus`. Đây là một repository interface sử dụng
 * Spring Data MongoDB.
 * 
 * ### Phân tích mã nguồn
 * 
 * ```java
 * package com.se.ssps.server.repository;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import org.springframework.data.mongodb.repository.Query;
 * import org.springframework.stereotype.Repository;
 * 
 * import com.se.ssps.server.entity.configuration.Campus;
 * 
 * @Repository
 * public interface CampusRepository extends MongoRepository<Campus, String> {
 * 
 * // Truy vấn Campus bằng ID
 * 
 * @Query("{ '_id': ?0 }")
 * public Campus findCampusById(String id);
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Kế thừa `MongoRepository<Campus, String>`**:
 * - `MongoRepository<Campus, String>` là một interface cung cấp các phương thức
 * cơ bản cho các thao tác CRUD với MongoDB, trong đó `Campus` là kiểu entity
 * bạn đang làm việc và `String` là kiểu dữ liệu của trường `_id` trong MongoDB.
 * - MongoDB sẽ tự động tạo một trường `_id` duy nhất cho mỗi tài liệu, và bạn
 * có thể dùng kiểu dữ liệu `String` cho trường này.
 * 
 * 2. **Annotation `@Repository`**:
 * - `@Repository` là một annotation của Spring để chỉ định rằng đây là một
 * repository bean. Spring sẽ quản lý lifecycle của đối tượng này và tự động xử
 * lý các exception liên quan đến thao tác với cơ sở dữ liệu.
 * 
 * 3. **Custom Query với `@Query`**:
 * - Phương thức `findCampusById` sử dụng annotation `@Query` để thực hiện truy
 * vấn MongoDB tùy chỉnh. Câu truy vấn `{ '_id': ?0 }` tìm kiếm tài liệu
 * `Campus` theo giá trị của `_id`. Tham số `?0` đại diện cho tham số đầu tiên
 * của phương thức, trong trường hợp này là `id`.
 * 
 * ### Cách sử dụng trong dịch vụ
 * 
 * Dưới đây là một ví dụ về cách bạn có thể sử dụng `CampusRepository` trong
 * dịch vụ:
 * 
 * ```java
 * 
 * @Service
 * public class CampusService {
 * 
 * private final CampusRepository campusRepository;
 * 
 * public CampusService(CampusRepository campusRepository) {
 * this.campusRepository = campusRepository;
 * }
 * 
 * public Campus getCampusById(String id) {
 * return campusRepository.findCampusById(id); // Gọi truy vấn tùy chỉnh để lấy
 * Campus theo ID
 * }
 * }
 * ```
 * 
 * ### Lưu ý khi sử dụng Spring Data MongoDB
 * 
 * 1. **Phương thức tự động của Spring**:
 * - Spring Data MongoDB cung cấp nhiều phương thức tự động cho việc truy vấn dữ
 * liệu, ví dụ:
 * - `findById(String id)`: Spring sẽ tự động tạo câu truy vấn tìm kiếm tài liệu
 * theo `_id` mà không cần phải sử dụng `@Query`.
 * - `findAll()`: Lấy tất cả các tài liệu trong collection.
 * 
 * Thay vì sử dụng `@Query` cho phương thức `findCampusById`, bạn có thể sử dụng
 * phương thức tự động `findById`, như sau:
 * ```java
 * Optional<Campus> findById(String id);
 * ```
 * Phương thức này sẽ trả về một `Optional<Campus>`, giúp xử lý trường hợp không
 * tìm thấy tài liệu với ID.
 * 
 * 2. **Phương thức tùy chỉnh với `@Query`**:
 * - Khi cần thực hiện các truy vấn phức tạp hơn (như tìm kiếm theo các trường
 * khác ngoài `_id`), `@Query` là một công cụ hữu ích để định nghĩa các truy vấn
 * MongoDB tùy chỉnh.
 * - Tuy nhiên, khi bạn chỉ cần truy vấn theo ID, Spring Data MongoDB đã cung
 * cấp sẵn phương thức `findById`.
 * 
 * ### Kết luận
 * 
 * Lớp `CampusRepository` cung cấp phương thức `findCampusById` để thực hiện
 * truy vấn MongoDB tìm kiếm tài liệu `Campus` theo ID. Bạn có thể sử dụng
 * phương thức tự động `findById` mà Spring Data MongoDB cung cấp để đơn giản
 * hóa mã nguồn của mình.
 */