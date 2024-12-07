// package com.se.ssps.server.entity.configuration;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.*;

// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Document(collection = "maxFileSize") // Định nghĩa tài liệu MongoDB
// public class MaxFileSize {
//     @Id
//     private String id; // MongoDB sử dụng ObjectId (dạng chuỗi) làm khóa chính

//     private Double maxFileSize;
// }

// package com.se.ssps.server.entity.configuration;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.*;

// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Document(collection = "maxFileSize") // Định nghĩa tài liệu MongoDB
// public class MaxFileSize {
//     @Id
//     private String id; // MongoDB sử dụng ObjectId (dạng chuỗi) làm khóa chính

//     private Double maxFileSize;

//     // Thêm phương thức getValue
//     public Double getValue() {
//         return this.maxFileSize;
//     }
// }

package com.se.ssps.server.entity.configuration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "maxFileSize") // Định nghĩa tài liệu MongoDB
public class MaxFileSize {
    @Id
    private String id; // MongoDB sử dụng ObjectId (dạng chuỗi) làm khóa chính

    private Double maxFileSize;

    // Thêm phương thức setter
    public void setValue(Double value) {
        this.maxFileSize = value;
    }

    // Thêm phương thức getValue
    public Double getValue() {
        return this.maxFileSize;
    }
}

/*
 * ### Đánh giá và giải thích lớp `MaxFileSize`
 * 
 * #### 1. **Vai trò trong hệ thống**
 * - Lớp **`MaxFileSize`** đại diện cho một cấu hình giới hạn kích thước tối đa
 * của tệp tin được phép tải lên trong hệ thống **HCMUT_SSPS**.
 * - **SPSO (Student Printing Service Officer)** sẽ quản lý giá trị này để đảm
 * bảo rằng các tệp tin được tải lên nằm trong giới hạn cho phép, tránh gây ra
 * lỗi hoặc ảnh hưởng đến hiệu suất hệ thống.
 * 
 * ---
 * 
 * #### 2. **Các thành phần trong lớp**
 * 
 * ```java
 * 
 * @Setter
 * 
 * @Getter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Document(collection = "maxFileSize") // Định nghĩa tài liệu MongoDB
 * public class MaxFileSize {
 * 
 * @Id
 * private String id; // MongoDB sử dụng ObjectId (dạng chuỗi) làm khóa chính
 * 
 * private Double maxFileSize;
 * 
 * // Thêm phương thức setter
 * public void setValue(Double value) {
 * this.maxFileSize = value;
 * }
 * 
 * // Thêm phương thức getValue
 * public Double getValue() {
 * return this.maxFileSize;
 * }
 * }
 * ```
 * 
 * - **Annotation `@Document(collection = "maxFileSize")`:**
 * - Đánh dấu lớp là một tài liệu trong MongoDB.
 * - Tài liệu sẽ được lưu trong collection `maxFileSize`.
 * 
 * - **Annotation Lombok:**
 * - `@Setter` và `@Getter`: Tự động tạo các phương thức `set` và `get` cho tất
 * cả các thuộc tính.
 * - `@AllArgsConstructor` và `@NoArgsConstructor`: Tạo constructor có và không
 * có tham số.
 * 
 * - **Thuộc tính `id`:**
 * - Được đánh dấu bằng `@Id`, đây là khóa chính của tài liệu trong MongoDB.
 * - MongoDB sử dụng `ObjectId`, nhưng trong ứng dụng, nó được lưu dưới dạng
 * chuỗi `String`.
 * 
 * - **Thuộc tính `maxFileSize`:**
 * - Biểu diễn giá trị kích thước tối đa của tệp tin (đơn vị có thể là **MB**
 * hoặc **GB** tùy quy định hệ thống).
 * - Dùng kiểu dữ liệu `Double` để đảm bảo có thể lưu các giá trị chính xác và
 * linh hoạt hơn cho các hệ thống yêu cầu đơn vị kích thước nhỏ.
 * 
 * - **Phương thức `setValue(Double value)` và `getValue()`:**
 * - Hai phương thức này được thêm để minh họa cách làm việc trực tiếp với giá
 * trị `maxFileSize`.
 * - Mặc dù Lombok đã tự động sinh các phương thức `get` và `set`, nhưng việc
 * thêm thủ công không ảnh hưởng đến chức năng.
 * 
 * ---
 * 
 * #### 3. **Mối quan hệ với các thực thể khác**
 * - **`MaxFileSize`** là một cấu hình độc lập, không có mối quan hệ trực tiếp
 * với các thực thể khác.
 * - Tuy nhiên, nó sẽ được sử dụng trong logic của hệ thống:
 * - **Kiểm tra trước khi tải tệp:** Khi người dùng tải lên tệp, hệ thống sẽ so
 * sánh kích thước tệp với giá trị `maxFileSize`.
 * - **Cập nhật giá trị:** **SPSO** có thể thay đổi giá trị này khi cần (ví dụ:
 * tăng kích thước tối đa cho các tệp đặc biệt).
 * 
 * ---
 * 
 * #### 4. **Repository**
 * Để làm việc với MongoDB, cần tạo một repository tương ứng.
 * 
 * ```java
 * package com.se.ssps.server.repository.configuration;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import org.springframework.stereotype.Repository;
 * import com.se.ssps.server.entity.configuration.MaxFileSize;
 * 
 * @Repository
 * public interface MaxFileSizeRepository extends MongoRepository<MaxFileSize,
 * String> {
 * MaxFileSize findFirstByOrderByIdAsc(); // Lấy giá trị cấu hình đầu tiên
 * }
 * ```
 * 
 * ---
 * 
 * #### 5. **Service**
 * 
 * ```java
 * package com.se.ssps.server.service.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.configuration.MaxFileSize;
 * import com.se.ssps.server.repository.configuration.MaxFileSizeRepository;
 * 
 * @Service
 * public class MaxFileSizeService {
 * 
 * @Autowired
 * private MaxFileSizeRepository maxFileSizeRepository;
 * 
 * // Lấy giá trị MaxFileSize
 * public Double getMaxFileSize() {
 * MaxFileSize maxFileSize = maxFileSizeRepository.findFirstByOrderByIdAsc();
 * return maxFileSize != null ? maxFileSize.getValue() : null;
 * }
 * 
 * // Cập nhật giá trị MaxFileSize
 * public MaxFileSize updateMaxFileSize(Double newValue) {
 * MaxFileSize maxFileSize = maxFileSizeRepository.findFirstByOrderByIdAsc();
 * if (maxFileSize == null) {
 * maxFileSize = new MaxFileSize();
 * }
 * maxFileSize.setValue(newValue);
 * return maxFileSizeRepository.save(maxFileSize);
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 6. **Controller**
 * 
 * ```java
 * package com.se.ssps.server.controller.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.*;
 * import com.se.ssps.server.service.configuration.MaxFileSizeService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/configuration/maxfilesize")
 * public class MaxFileSizeController {
 * 
 * @Autowired
 * private MaxFileSizeService maxFileSizeService;
 * 
 * // Lấy giá trị maxFileSize hiện tại
 * 
 * @GetMapping
 * public ResponseEntity<Double> getMaxFileSize() {
 * return ResponseEntity.ok(maxFileSizeService.getMaxFileSize());
 * }
 * 
 * // Cập nhật giá trị maxFileSize
 * 
 * @PutMapping
 * public ResponseEntity<Void> updateMaxFileSize(@RequestBody Double newValue) {
 * maxFileSizeService.updateMaxFileSize(newValue);
 * return ResponseEntity.noContent().build();
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 7. **Validation và Khuyến nghị**
 * - **Validation cho `maxFileSize`:**
 * - Thêm logic kiểm tra để đảm bảo giá trị `maxFileSize` hợp lệ, không âm hoặc
 * quá lớn.
 * ```java
 * if (newValue <= 0 || newValue > 10000) { // Ví dụ: giới hạn tối đa là 10 GB
 * throw new IllegalArgumentException("Invalid max file size value.");
 * }
 * ```
 * 
 * - **Bảo mật:**
 * - Đảm bảo rằng chỉ **SPSO** hoặc người dùng có quyền quản trị mới được phép
 * cập nhật giá trị này.
 * 
 * - **Tạo giá trị mặc định:**
 * - Khi khởi tạo hệ thống, có thể lưu giá trị mặc định (ví dụ: `20 MB`) nếu
 * chưa có giá trị nào được cấu hình trong collection `maxFileSize`.
 * 
 * ---
 * 
 * #### 8. **Tóm tắt**
 * Lớp **`MaxFileSize`** và các thành phần liên quan đảm bảo việc quản lý kích
 * thước tệp tin tải lên một cách linh hoạt và dễ dàng. Với thiết kế này:
 * - **Dễ dàng bảo trì**: Giá trị có thể được thay đổi mà không cần chỉnh sửa mã
 * nguồn.
 * - **Đảm bảo tính tương thích**: Dễ dàng kiểm soát dung lượng tệp tin để phù
 * hợp với hạ tầng hệ thống.
 * - **Hỗ trợ tốt cho quản trị viên**: Tích hợp các endpoint RESTful để cấu hình
 * giá trị thông qua giao diện.
 * 
 * Nếu cần thêm tính năng hoặc cải tiến, hãy tiếp tục trao đổi nhé! 😊
 */