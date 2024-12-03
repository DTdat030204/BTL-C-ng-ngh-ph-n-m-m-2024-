
package com.se.ssps.server.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.configuration.FileType;

@Repository
public interface FileTypeRepository extends MongoRepository<FileType, String> {

    // Truy vấn FileType bằng ID
    @Query("{ '_id': ?0 }")
    public FileType findTypeById(String id);

    @Aggregation(pipeline = {
    "{ $match: { startDate: { $gte: ?0, $lte: ?1 } } }",
    "{ $group: { _id: '$pageSize', totalPages: { $sum: { $multiply: ['$numOfPages', '$numOfCopies'] } } } }"
    })
    List<FileType> calculateTotalPagesByType(LocalDateTime fromDate, LocalDateTime toDate);

}



/*
 * Lớp `FileTypeRepository` của bạn là một repository interface trong Spring
 * Data MongoDB, cho phép thực hiện các thao tác CRUD với MongoDB đối với entity
 * `FileType`. Đây là cách bạn có thể truy vấn các đối tượng `FileType` từ
 * MongoDB.
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
 * import com.se.ssps.server.entity.configuration.FileType;
 * 
 * @Repository
 * public interface FileTypeRepository extends MongoRepository<FileType, String>
 * {
 * 
 * // Truy vấn FileType bằng ID
 * 
 * @Query("{ '_id': ?0 }")
 * public FileType findTypeById(String id);
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Kế thừa `MongoRepository<FileType, String>`**:
 * - `MongoRepository<FileType, String>` là interface được Spring Data MongoDB
 * cung cấp để làm việc với các tài liệu trong MongoDB. `FileType` là kiểu
 * entity mà bạn đang làm việc, và `String` là kiểu dữ liệu của trường `_id`
 * trong MongoDB.
 * - `MongoRepository` đã cung cấp một số phương thức cơ bản như `findById()`,
 * `save()`, `delete()`, v.v., giúp bạn dễ dàng thao tác với MongoDB mà không
 * cần phải viết các phương thức cơ bản.
 * 
 * 2. **Annotation `@Repository`**:
 * - `@Repository` là một annotation của Spring để đánh dấu lớp này là một
 * repository, từ đó Spring sẽ tự động quản lý lifecycle của nó và xử lý các
 * exception liên quan đến MongoDB.
 * 
 * 3. **Custom Query với `@Query`**:
 * - Phương thức `findTypeById` sử dụng annotation `@Query` để thực hiện truy
 * vấn MongoDB tùy chỉnh. Truy vấn `{ '_id': ?0 }` tìm kiếm tài liệu `FileType`
 * theo trường `_id`. Tham số `?0` là tham số đầu tiên của phương thức, trong
 * trường hợp này là `id`.
 * 
 * ### Cách sử dụng trong dịch vụ
 * 
 * Dưới đây là một ví dụ về cách bạn có thể sử dụng `FileTypeRepository` trong
 * dịch vụ:
 * 
 * ```java
 * 
 * @Service
 * public class FileTypeService {
 * 
 * private final FileTypeRepository fileTypeRepository;
 * 
 * public FileTypeService(FileTypeRepository fileTypeRepository) {
 * this.fileTypeRepository = fileTypeRepository;
 * }
 * 
 * public FileType getFileTypeById(String id) {
 * return fileTypeRepository.findTypeById(id); // Gọi truy vấn tùy chỉnh để lấy
 * FileType theo ID
 * }
 * }
 * ```
 * 
 * ### Lưu ý khi sử dụng Spring Data MongoDB
 * 
 * 1. **Phương thức tự động của Spring**:
 * - Spring Data MongoDB cung cấp phương thức tự động cho việc truy vấn tài liệu
 * theo ID, ví dụ:
 * ```java
 * Optional<FileType> findById(String id);
 * ```
 * Phương thức này sẽ trả về một `Optional<FileType>`, giúp xử lý trường hợp
 * không tìm thấy tài liệu với ID.
 * 
 * 2. **Phương thức tùy chỉnh với `@Query`**:
 * - `@Query` cho phép bạn định nghĩa các truy vấn MongoDB phức tạp hơn, chẳng
 * hạn như truy vấn với các điều kiện khác ngoài `_id`. Tuy nhiên, nếu bạn chỉ
 * cần tìm kiếm theo `_id`, bạn có thể sử dụng phương thức tự động `findById`.
 * 
 * ### Kết luận
 * 
 * Lớp `FileTypeRepository` cung cấp phương thức `findTypeById` để tìm kiếm đối
 * tượng `FileType` theo ID bằng cách sử dụng truy vấn MongoDB tùy chỉnh. Bạn
 * cũng có thể sử dụng phương thức tự động `findById` mà Spring Data MongoDB
 * cung cấp, giúp mã nguồn ngắn gọn và dễ hiểu hơn.
 */