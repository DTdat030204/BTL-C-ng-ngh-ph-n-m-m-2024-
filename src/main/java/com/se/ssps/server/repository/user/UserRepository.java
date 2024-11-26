package com.se.ssps.server.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.se.ssps.server.entity.user.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Truy vấn tìm User theo username
    User findByUsername(String username);

    
}
/*
 * Lớp `BuildingRepository` mà bạn đã định nghĩa là một interface để thực hiện
 * các thao tác với MongoDB đối với entity `Building`. Nó kế thừa từ
 * `MongoRepository` và sử dụng annotation `@Repository` để Spring nhận diện lớp
 * này như một Bean repository cho MongoDB.
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
 * import com.se.ssps.server.entity.configuration.Building;
 * 
 * @Repository
 * public interface BuildingRepository extends MongoRepository<Building, String>
 * {
 * 
 * // Truy vấn tìm Building bằng ID
 * 
 * @Query("{ '_id': ?0 }")
 * public Building findBuildingById(String id);
 * 
 * // Nếu cần, có thể thực hiện update bằng custom query, tuy nhiên với MongoDB,
 * // thường sử dụng template để cập nhật phức tạp hơn.
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Kế thừa `MongoRepository<Building, String>`**:
 * - `MongoRepository<Building, String>` là một interface từ Spring Data MongoDB
 * cung cấp các phương thức CRUD cơ bản. Trong đó:
 * - `Building` là kiểu đối tượng mà bạn thao tác với, đây là entity `Building`
 * của bạn.
 * - `String` là kiểu dữ liệu cho khóa chính (`_id`) của tài liệu trong MongoDB.
 * MongoDB mặc định sử dụng `ObjectId`, nhưng bạn có thể sử dụng một trường kiểu
 * `String` nếu muốn.
 * 
 * 2. **Annotation `@Repository`**:
 * - `@Repository` đánh dấu interface này là một repository bean trong Spring,
 * giúp Spring hiểu rằng đây là một lớp dùng để tương tác với MongoDB. Điều này
 * giúp Spring quản lý lifecycle của repository và tự động xử lý các exception
 * liên quan đến truy vấn cơ sở dữ liệu.
 * 
 * 3. **Custom Query với `@Query`**:
 * - Phương thức `findBuildingById` sử dụng annotation `@Query` để định nghĩa
 * một truy vấn MongoDB tùy chỉnh. Câu truy vấn `{ '_id': ?0 }` tìm tài liệu
 * `Building` dựa trên giá trị `_id`. Tham số `?0` là tham chiếu đến tham số đầu
 * tiên trong phương thức, trong trường hợp này là `id`.
 * - Cách sử dụng `@Query` như vậy rất tiện lợi, nhưng bạn cũng có thể thay thế
 * với các phương thức dựa trên tên như `findById`, vì Spring Data MongoDB sẽ tự
 * động tạo truy vấn từ tên phương thức nếu có thể.
 * 
 * ### Cách sử dụng trong dịch vụ
 * 
 * Ví dụ về cách sử dụng repository này trong một dịch vụ:
 * 
 * ```java
 * 
 * @Service
 * public class BuildingService {
 * 
 * private final BuildingRepository buildingRepository;
 * 
 * public BuildingService(BuildingRepository buildingRepository) {
 * this.buildingRepository = buildingRepository;
 * }
 * 
 * public Building getBuildingById(String id) {
 * return buildingRepository.findBuildingById(id); // Gọi truy vấn tùy chỉnh
 * }
 * }
 * ```
 * 
 * ### Lưu ý khi sử dụng MongoDB trong Spring
 * 
 * 1. **Custom Update**:
 * - MongoDB không hỗ trợ update phức tạp trực tiếp qua repository bằng tên
 * phương thức. Tuy nhiên, bạn có thể sử dụng `MongoTemplate` hoặc `@Modifying`
 * để thực hiện các cập nhật phức tạp, ví dụ như cập nhật theo nhiều điều kiện
 * hoặc thao tác với các nested documents.
 * 
 * 2. **Phương thức tự động của Spring Data**:
 * - Nếu bạn chỉ cần tìm kiếm tài liệu theo ID, Spring Data MongoDB tự động cung
 * cấp phương thức `findById` mà bạn không cần phải viết thêm `@Query` cho
 * trường hợp này.
 * 
 * ```java
 * public Optional<Building> findById(String id);
 * ```
 * 
 * ### Kết luận
 * 
 * `BuildingRepository` giúp bạn truy vấn các tài liệu `Building` trong MongoDB
 * bằng cách sử dụng phương thức `findBuildingById`, với cú pháp `@Query` cho
 * phép tạo các truy vấn MongoDB tùy chỉnh. Bạn có thể dễ dàng mở rộng thêm các
 * truy vấn khác tùy theo yêu cầu của ứng dụng.
 */