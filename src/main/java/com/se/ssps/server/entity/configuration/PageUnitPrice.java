package com.se.ssps.server.entity.configuration;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.*;

// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
// @Document(collection = "page_unit_price") // Định nghĩa tài liệu MongoDB
// public class PageUnitPrice {
//     @Id
//     private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String

//     private Integer price;
// }package com.se.ssps.server.entity.configuration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "page_unit_price") // Định nghĩa tài liệu MongoDB
public class PageUnitPrice {
    @Id
    private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String

    private Integer price;

    // Thêm phương thức getValue
    public Integer getValue() {
        return this.price;
    }
    
    // Thêm phương thức setter cho price
    public void setValue(Integer price) {
        this.price = price;
    }
}



/*
 * ### Đánh giá và giải thích lớp `PageUnitPrice`
 * 
 * #### 1. **Vai trò trong hệ thống**
 * - Lớp **`PageUnitPrice`** đại diện cho đơn giá của mỗi trang in trong hệ
 * thống.
 * - Giá trị này có thể được sử dụng để tính toán chi phí in khi người dùng vượt
 * quá số lượng trang được phân bổ miễn phí.
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
 * @Document(collection = "page_unit_price") // Định nghĩa tài liệu MongoDB
 * public class PageUnitPrice {
 * 
 * @Id
 * private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String
 * 
 * private Integer price;
 * 
 * // Thêm phương thức getValue
 * public Integer getValue() {
 * return this.price;
 * }
 * 
 * // Thêm phương thức setter cho price
 * public void setValue(Integer price) {
 * this.price = price;
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 3. **Chi tiết từng thành phần**
 * 
 * 1. **`@Document(collection = "page_unit_price")`**:
 * - Định nghĩa tài liệu MongoDB với collection tên là `page_unit_price`.
 * - Mỗi đối tượng `PageUnitPrice` sẽ được lưu dưới dạng một tài liệu trong
 * MongoDB.
 * 
 * 2. **`id`**:
 * - Là khóa chính của tài liệu, được MongoDB quản lý dưới dạng `ObjectId` và
 * ánh xạ thành kiểu `String` trong Java.
 * 
 * 3. **`price`**:
 * - Đại diện cho đơn giá mỗi trang in (tính theo đơn vị tiền tệ của hệ thống).
 * - Kiểu `Integer` phù hợp vì giá trị thường là số nguyên.
 * 
 * 4. **Phương thức `getValue()` và `setValue(Integer price)`**:
 * - **`getValue()`**: Trả về giá trị của thuộc tính `price`.
 * - **`setValue()`**: Cập nhật giá trị mới cho thuộc tính `price`.
 * 
 * 5. **Constructor**:
 * - **`@AllArgsConstructor`**: Tạo đối tượng với đầy đủ các tham số.
 * - **`@NoArgsConstructor`**: Cho phép tạo đối tượng rỗng, thuận tiện khi làm
 * việc với MongoDB hoặc các thư viện JSON.
 * 
 * ---
 * 
 * #### 4. **Mối quan hệ với các thực thể khác**
 * - **Liên quan đến `PageAllocation`**:
 * - Khi sinh viên vượt quá số lượng trang miễn phí trong một học kỳ
 * (`numOfPage` trong `PageAllocation`), đơn giá từ `PageUnitPrice` sẽ được sử
 * dụng để tính toán chi phí in thêm.
 * 
 * - **Tích hợp với hệ thống thanh toán**:
 * - Giá trị `price` sẽ được dùng trong logic tính toán tổng chi phí in và tích
 * hợp với các cổng thanh toán hoặc hóa đơn.
 * 
 * ---
 * 
 * #### 5. **Repository**
 * 
 * ```java
 * package com.se.ssps.server.repository.configuration;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.configuration.PageUnitPrice;
 * 
 * public interface PageUnitPriceRepository extends
 * MongoRepository<PageUnitPrice, String> {
 * PageUnitPrice findFirstByOrderByIdDesc(); // Lấy giá trị đơn giá mới nhất
 * }
 * ```
 * 
 * ---
 * 
 * #### 6. **Service**
 * 
 * ```java
 * package com.se.ssps.server.service.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.configuration.PageUnitPrice;
 * import com.se.ssps.server.repository.configuration.PageUnitPriceRepository;
 * 
 * @Service
 * public class PageUnitPriceService {
 * 
 * @Autowired
 * private PageUnitPriceRepository pageUnitPriceRepository;
 * 
 * // Lấy đơn giá hiện tại
 * public PageUnitPrice getCurrentUnitPrice() {
 * return pageUnitPriceRepository.findFirstByOrderByIdDesc();
 * }
 * 
 * // Lưu hoặc cập nhật đơn giá
 * public PageUnitPrice saveOrUpdateUnitPrice(PageUnitPrice unitPrice) {
 * return pageUnitPriceRepository.save(unitPrice);
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 7. **Controller**
 * 
 * ```java
 * package com.se.ssps.server.controller.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.*;
 * import com.se.ssps.server.entity.configuration.PageUnitPrice;
 * import com.se.ssps.server.service.configuration.PageUnitPriceService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/configuration/page_unit_price")
 * public class PageUnitPriceController {
 * 
 * @Autowired
 * private PageUnitPriceService pageUnitPriceService;
 * 
 * // Lấy đơn giá hiện tại
 * 
 * @GetMapping
 * public ResponseEntity<PageUnitPrice> getCurrentUnitPrice() {
 * return ResponseEntity.ok(pageUnitPriceService.getCurrentUnitPrice());
 * }
 * 
 * // Tạo mới hoặc cập nhật đơn giá
 * 
 * @PostMapping
 * public ResponseEntity<PageUnitPrice> saveUnitPrice(@RequestBody PageUnitPrice
 * unitPrice) {
 * return
 * ResponseEntity.ok(pageUnitPriceService.saveOrUpdateUnitPrice(unitPrice));
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 8. **Tích hợp với hệ thống in ấn**
 * - Khi thực hiện một yêu cầu in:
 * 1. Kiểm tra số trang đã in so với số trang miễn phí được phân bổ
 * (`numOfPage`).
 * 2. Nếu vượt quá, tính phí bổ sung bằng cách sử dụng giá trị `price` từ
 * `PageUnitPrice`.
 * 3. Cập nhật thông tin thanh toán và hóa đơn.
 ** 
 * Ví dụ logic tính phí**:
 * 
 * ```java
 * public Double calculatePrintingCost(int pagesPrinted, int freePages,
 * PageUnitPrice unitPrice) {
 * int excessPages = pagesPrinted - freePages;
 * if (excessPages > 0) {
 * return excessPages * unitPrice.getValue();
 * }
 * return 0.0;
 * }
 * ```
 * 
 * ---
 * 
 * #### 9. **Tóm tắt**
 * Lớp **`PageUnitPrice`** được thiết kế đơn giản nhưng đóng vai trò quan trọng
 * trong hệ thống:
 * - Quản lý giá trị đơn giá mỗi trang in.
 * - Dễ dàng mở rộng hoặc cập nhật giá trị khi cần thiết.
 * - Liên kết trực tiếp với các logic tính toán chi phí in.
 * 
 * Nếu cần hỗ trợ thêm hoặc mở rộng, hãy chia sẻ thêm thông tin nhé! 😊
 */