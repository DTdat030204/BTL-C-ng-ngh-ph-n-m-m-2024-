// package com.se.ssps.server.repository;

// import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.se.ssps.server.entity.configuration.MaxFileSize;

// @Repository
// public interface MaxSizeRepository extends MongoRepository<MaxFileSize, String> {

//     // Truy vấn giá trị maxFileSize của tài liệu có _id = "1"
//     @Query(value = "{ '_id': '1' }", fields = "{ 'maxFileSize': 1, '_id': 0 }")
//     public Double getValue();

//     // // Cập nhật giá trị maxFileSize cho tài liệu có _id = "1"
//     // @Query(value = "{ '_id': '1' }", update = "{ '$set': { 'maxFileSize': ?0 } }")
//     // public void setMaxSize(Double size);
// }

// package com.se.ssps.server.repository;

// import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
// import org.springframework.stereotype.Repository;

// import com.se.ssps.server.entity.configuration.MaxFileSize;

// @Repository
// public interface MaxSizeRepository extends MongoRepository<MaxFileSize, String> {

//     // Truy vấn giá trị maxFileSize của tài liệu có _id = "1"
//     @Query(value = "{ '_id': '1' }", fields = "{ 'maxFileSize': 1, '_id': 0 }")
//     public MaxFileSize findMaxFileSizeById(String id);
// }

package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.se.ssps.server.entity.configuration.MaxFileSize;

import java.util.List;

@Repository
public interface MaxSizeRepository extends MongoRepository<MaxFileSize, String> {

    // Cập nhật để trả về danh sách tất cả các bản ghi
    public List<MaxFileSize> findAll();
}

/*
 * Đoạn mã cuối cùng của bạn định nghĩa một repository `MaxSizeRepository` trong
 * Spring Data MongoDB để thao tác với đối tượng `MaxFileSize`. Repository này
 * kế thừa từ `MongoRepository`, giúp bạn dễ dàng thực hiện các thao tác CRUD
 * với MongoDB.
 * 
 * ### Phân tích mã nguồn
 * 
 * ```java
 * package com.se.ssps.server.repository;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import org.springframework.stereotype.Repository;
 * import com.se.ssps.server.entity.configuration.MaxFileSize;
 * 
 * import java.util.List;
 * 
 * @Repository
 * public interface MaxSizeRepository extends MongoRepository<MaxFileSize,
 * String> {
 * 
 * // Cập nhật để trả về danh sách tất cả các bản ghi
 * public List<MaxFileSize> findAll();
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Kế thừa từ `MongoRepository<MaxFileSize, String>`**:
 * - `MaxFileSize` là entity mà bạn đang làm việc, và `String` là kiểu dữ liệu
 * của trường `_id` trong MongoDB.
 * - `MongoRepository` cung cấp các phương thức cơ bản để thao tác với MongoDB
 * như `findById()`, `save()`, `delete()`, và `findAll()`.
 * 
 * 2. **Annotation `@Repository`**:
 * - `@Repository` đánh dấu lớp này là một Spring Data repository, giúp Spring
 * nhận diện và quản lý repository này để thực hiện các thao tác với MongoDB.
 * 
 * 3. **Phương thức `findAll()`**:
 * - `findAll()` là một phương thức có sẵn trong `MongoRepository`, được sử dụng
 * để lấy tất cả các bản ghi từ collection MongoDB.
 * - Phương thức này sẽ trả về một danh sách các đối tượng `MaxFileSize`.
 * 
 * ### Cách sử dụng repository này trong dịch vụ
 * 
 * Dưới đây là một ví dụ về cách sử dụng `MaxSizeRepository` trong một dịch vụ:
 * 
 * ```java
 * 
 * @Service
 * public class MaxFileSizeService {
 * 
 * private final MaxSizeRepository maxSizeRepository;
 * 
 * public MaxFileSizeService(MaxSizeRepository maxSizeRepository) {
 * this.maxSizeRepository = maxSizeRepository;
 * }
 * 
 * // Lấy tất cả các bản ghi MaxFileSize từ MongoDB
 * public List<MaxFileSize> getAllMaxFileSizes() {
 * return maxSizeRepository.findAll(); // Trả về danh sách tất cả bản ghi
 * }
 * 
 * // Lấy giá trị MaxFileSize của tài liệu có _id = "1"
 * public MaxFileSize getMaxFileSizeById(String id) {
 * return maxSizeRepository.findById(id).orElse(null); // Tìm theo ID và trả về
 * null nếu không tìm thấy
 * }
 * }
 * ```
 * 
 * ### Một số điểm lưu ý
 * 
 * 1. **Phương thức `findAll()`**:
 * - Phương thức `findAll()` sẽ trả về tất cả các tài liệu trong collection
 * tương ứng với entity `MaxFileSize`. Điều này có thể không hiệu quả nếu số
 * lượng bản ghi quá lớn, vì vậy bạn có thể cần phải sử dụng phân trang hoặc các
 * phương thức truy vấn cụ thể hơn nếu dữ liệu lớn.
 * 
 * 2. **Truy vấn tùy chỉnh**:
 * - Nếu bạn cần thực hiện các truy vấn phức tạp hơn, bạn có thể sử dụng
 * annotation `@Query` để chỉ định các truy vấn MongoDB tùy chỉnh (ví dụ như
 * trong đoạn mã bạn đã thử với `findMaxFileSizeById`).
 * - Bạn có thể tạo các phương thức tùy chỉnh với `@Query` để thực hiện các thao
 * tác như lấy chỉ một trường nhất định (`fields`), hoặc thực hiện cập nhật với
 * MongoDB.
 * 
 * 3. **Sử dụng `Optional` với `findById()`**:
 * - Spring Data MongoDB trả về một `Optional` khi sử dụng `findById()`. Điều
 * này giúp tránh lỗi khi không tìm thấy tài liệu với ID đã cho.
 * 
 * ### Kết luận
 * 
 * Lớp `MaxSizeRepository` của bạn hiện tại chỉ sử dụng phương thức `findAll()`
 * để lấy tất cả các bản ghi từ MongoDB, và bạn có thể mở rộng thêm các phương
 * thức tùy chỉnh để thực hiện các thao tác tìm kiếm, cập nhật hoặc xóa dữ liệu
 * từ MongoDB theo nhu cầu.
 */