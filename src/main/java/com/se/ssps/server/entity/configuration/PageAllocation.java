package com.se.ssps.server.entity.configuration;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "page_allocation") // Định nghĩa tài liệu MongoDB
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageAllocation {
    @Id
    private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String

    private Integer semester;

    private Integer year;

    private LocalDate allocatedDate; // Không cần @Temporal vì MongoDB hỗ trợ kiểu ngày

    private Integer numOfPage;

    private boolean status;

    public PageAllocation(Integer semester, Integer year, Integer numOfPage, boolean status) {
        this.semester = semester;
        this.numOfPage = numOfPage;
        this.year = year;
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }
}



/*
 * ### Đánh giá và giải thích lớp `PageAllocation`
 * 
 * #### 1. **Vai trò trong hệ thống**
 * - Lớp **`PageAllocation`** đại diện cho thông tin phân bổ số trang in miễn
 * phí hoặc hạn mức in ấn trong mỗi học kỳ cho sinh viên hoặc người dùng trong
 * hệ thống.
 * - Đây là một phần trong cấu hình hệ thống, giúp quản lý hạn mức in của từng
 * học kỳ, đảm bảo tính minh bạch và quản lý hiệu quả nguồn lực in ấn.
 * 
 * ---
 * 
 * #### 2. **Các thành phần trong lớp**
 * 
 * ```java
 * 
 * @Document(collection = "page_allocation") // Định nghĩa tài liệu MongoDB
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * public class PageAllocation {
 * 
 * @Id
 * private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String
 * 
 * private Integer semester;
 * 
 * private Integer year;
 * 
 * private LocalDate allocatedDate; // Không cần @Temporal vì MongoDB hỗ trợ
 * kiểu ngày
 * 
 * private Integer numOfPage;
 * 
 * private boolean status;
 * 
 * public PageAllocation(Integer semester, Integer year, Integer numOfPage,
 * boolean status) {
 * this.semester = semester;
 * this.numOfPage = numOfPage;
 * this.year = year;
 * this.status = status;
 * }
 * 
 * public boolean getStatus() {
 * return this.status;
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 3. **Chi tiết từng thuộc tính**
 * 
 * 1. **`@Document(collection = "page_allocation")`**:
 * - Đánh dấu lớp là một tài liệu MongoDB.
 * - Các đối tượng thuộc lớp này sẽ được lưu trong collection `page_allocation`.
 * 
 * 2. **`id`**:
 * - Thuộc tính đại diện cho khóa chính của tài liệu.
 * - MongoDB sử dụng kiểu `ObjectId` mặc định, được ánh xạ thành chuỗi `String`.
 * 
 * 3. **`semester`**:
 * - Biểu thị học kỳ (1 hoặc 2, có thể thêm 3 cho học kỳ hè).
 * - Quản lý thông tin phân bổ theo từng học kỳ.
 * 
 * 4. **`year`**:
 * - Biểu thị năm học của học kỳ tương ứng (ví dụ: 2024, 2025).
 * 
 * 5. **`allocatedDate`**:
 * - Ngày hệ thống thực hiện phân bổ số trang in cho học kỳ.
 * - Sử dụng kiểu **`LocalDate`**, là một kiểu dữ liệu hiện đại từ Java 8 trở
 * lên, hỗ trợ tốt cho MongoDB.
 * 
 * 6. **`numOfPage`**:
 * - Số lượng trang được phân bổ cho học kỳ đó.
 * - Ví dụ: 200 trang miễn phí cho học kỳ 1 năm 2024.
 * 
 * 7. **`status`**:
 * - Trạng thái của thông tin phân bổ:
 * - **`true`**: Đã kích hoạt (có hiệu lực).
 * - **`false`**: Chưa kích hoạt hoặc không còn hiệu lực.
 * - Dùng để kiểm tra xem phân bổ hiện tại có đang được áp dụng hay không.
 * 
 * ---
 * 
 * #### 4. **Constructor**
 * - **Constructor đầy đủ (`@AllArgsConstructor`)**:
 * - Dùng để tạo đối tượng với tất cả các thuộc tính.
 * 
 * - **Constructor không tham số (`@NoArgsConstructor`)**:
 * - Dùng trong các tình huống yêu cầu khởi tạo đối tượng rỗng, như khi làm việc
 * với MongoDB hoặc các thư viện JSON.
 * 
 * - **Constructor tùy chỉnh**:
 * - Được sử dụng khi cần khởi tạo đối tượng với các thuộc tính cụ thể, bỏ qua
 * một số trường không cần thiết (như `allocatedDate`).
 * 
 * ---
 * 
 * #### 5. **Các phương thức**
 * 
 * 1. **`getStatus()`**:
 * - Trả về trạng thái `status`.
 * - Phương thức này được viết tay thay vì dùng Lombok, có thể để phục vụ các
 * mục đích đặc biệt (ví dụ: tùy chỉnh logic nếu cần trong tương lai).
 * 
 * 2. **`setStatus()`**:
 * - Tự động được tạo bởi `@Setter`, cho phép thay đổi trạng thái `status`.
 * 
 * ---
 * 
 * #### 6. **Mối quan hệ với các thành phần khác**
 * - **Hệ thống quản lý sinh viên và in ấn**:
 * - Mỗi sinh viên sẽ nhận được một bản ghi `PageAllocation` cho từng học kỳ,
 * thông qua một thực thể liên quan khác (có thể là `Student` hoặc `User`).
 * - **Tích hợp với `PrintingLog`**:
 * - Hệ thống sẽ kiểm tra `PageAllocation` khi sinh viên thực hiện yêu cầu in.
 * Nếu số trang in vượt quá giới hạn `numOfPage`, yêu cầu sẽ bị từ chối hoặc
 * tính phí bổ sung.
 * 
 * ---
 * 
 * #### 7. **Repository**
 * 
 * ```java
 * package com.se.ssps.server.repository.configuration;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.configuration.PageAllocation;
 * import java.util.List;
 * 
 * public interface PageAllocationRepository extends
 * MongoRepository<PageAllocation, String> {
 * List<PageAllocation> findByYearAndSemester(Integer year, Integer semester);
 * PageAllocation findFirstByYearAndSemesterAndStatusTrue(Integer year, Integer
 * semester);
 * }
 * ```
 * 
 * ---
 * 
 * #### 8. **Service**
 * 
 * ```java
 * package com.se.ssps.server.service.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.configuration.PageAllocation;
 * import com.se.ssps.server.repository.configuration.PageAllocationRepository;
 * import java.util.List;
 * 
 * @Service
 * public class PageAllocationService {
 * 
 * @Autowired
 * private PageAllocationRepository pageAllocationRepository;
 * 
 * // Lấy thông tin phân bổ theo năm và học kỳ
 * public List<PageAllocation> getPageAllocationsByYearAndSemester(Integer year,
 * Integer semester) {
 * return pageAllocationRepository.findByYearAndSemester(year, semester);
 * }
 * 
 * // Lấy phân bổ hiện tại (trạng thái kích hoạt)
 * public PageAllocation getActiveAllocation(Integer year, Integer semester) {
 * return pageAllocationRepository.findFirstByYearAndSemesterAndStatusTrue(year,
 * semester);
 * }
 * 
 * // Tạo mới hoặc cập nhật thông tin phân bổ
 * public PageAllocation saveOrUpdatePageAllocation(PageAllocation
 * pageAllocation) {
 * return pageAllocationRepository.save(pageAllocation);
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 9. **Controller**
 * 
 * ```java
 * package com.se.ssps.server.controller.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.*;
 * import com.se.ssps.server.entity.configuration.PageAllocation;
 * import com.se.ssps.server.service.configuration.PageAllocationService;
 * import java.util.List;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/configuration/page_allocation")
 * public class PageAllocationController {
 * 
 * @Autowired
 * private PageAllocationService pageAllocationService;
 * 
 * // Lấy thông tin phân bổ theo năm và học kỳ
 * 
 * @GetMapping
 * public ResponseEntity<List<PageAllocation>> getPageAllocations(
 * 
 * @RequestParam Integer year, @RequestParam Integer semester) {
 * return
 * ResponseEntity.ok(pageAllocationService.getPageAllocationsByYearAndSemester(
 * year, semester));
 * }
 * 
 * // Tạo mới hoặc cập nhật thông tin phân bổ
 * 
 * @PostMapping
 * public ResponseEntity<PageAllocation> savePageAllocation(@RequestBody
 * PageAllocation pageAllocation) {
 * return ResponseEntity.ok(pageAllocationService.saveOrUpdatePageAllocation(
 * pageAllocation));
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 10. **Tóm tắt**
 * Lớp **`PageAllocation`** và các thành phần liên quan giúp quản lý hiệu quả
 * việc phân bổ trang in miễn phí cho sinh viên:
 * - **Đảm bảo minh bạch**: Quản lý rõ ràng hạn mức từng học kỳ.
 * - **Dễ dàng mở rộng**: Có thể thêm logic quản lý cho các kỳ đặc biệt hoặc
 * thay đổi hạn mức.
 * - **Phù hợp với MongoDB**: Sử dụng kiểu dữ liệu phù hợp như `LocalDate`.
 * 
 * Nếu cần chỉnh sửa thêm hoặc tích hợp sâu hơn, hãy tiếp tục chia sẻ nhé! 😊
 */