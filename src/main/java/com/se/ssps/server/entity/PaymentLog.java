package com.se.ssps.server.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.ssps.server.entity.user.Student;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment_logs") // MongoDB sẽ lưu đối tượng này trong collection "payment_logs"
@Setter
@Getter
@NoArgsConstructor
public class PaymentLog {

    @Id
    private String id; // MongoDB tự động tạo trường _id, nhưng bạn có thể sử dụng trường này làm id

    @DBRef
    @JsonIgnore
    private Student student; // Sử dụng @DBRef để tham chiếu đến đối tượng Student

    private int numOfPages;

    private LocalDateTime payDate; // MongoDB sẽ lưu LocalDateTime dưới dạng ISODate

    private String paymentMethod;

    private Integer unitPrice;

    public PaymentLog(Student student, int numOfPages, LocalDateTime payDate, String paymentMethod, Integer unitPrice) {
        this.student = student;
        this.numOfPages = numOfPages;
        this.payDate = payDate;
        this.paymentMethod = paymentMethod;
        this.unitPrice = unitPrice;
    }
}





/*
 * Lớp `PaymentLog` mà bạn đã định nghĩa sử dụng MongoDB để lưu trữ các thông
 * tin liên quan đến giao dịch thanh toán của sinh viên. Dưới đây là phân tích
 * chi tiết về lớp này:
 * 
 * ### Phân tích lớp `PaymentLog`
 * 
 * ```java
 * package com.se.ssps.server.entity;
 * 
 * import java.time.LocalDateTime;
 * 
 * import com.se.ssps.server.entity.user.Student;
 * 
 * import lombok.*;
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.DBRef;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * 
 * @Document(collection = "payment_logs") // MongoDB sẽ lưu đối tượng này trong
 * collection "payment_logs"
 * 
 * @Setter
 * 
 * @Getter
 * 
 * @NoArgsConstructor
 * public class PaymentLog {
 * 
 * @Id
 * private String id; // MongoDB tự động tạo trường _id, nhưng bạn có thể sử
 * dụng trường này làm id
 * 
 * @DBRef
 * private Student student; // Sử dụng @DBRef để tham chiếu đến đối tượng
 * Student
 * 
 * private int numOfPages;
 * 
 * private LocalDateTime payDate; // MongoDB sẽ lưu LocalDateTime dưới dạng
 * ISODate
 * 
 * private String paymentMethod;
 * 
 * private Integer unitPrice;
 * 
 * public PaymentLog(Student student, int numOfPages, LocalDateTime payDate,
 * String paymentMethod, Integer unitPrice) {
 * this.student = student;
 * this.numOfPages = numOfPages;
 * this.payDate = payDate;
 * this.paymentMethod = paymentMethod;
 * this.unitPrice = unitPrice;
 * }
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Annotation `@Document(collection = "payment_logs")`**:
 * - Đánh dấu lớp `PaymentLog` là một tài liệu (document) trong MongoDB.
 * - MongoDB sẽ lưu đối tượng này trong collection có tên là `payment_logs`.
 * 
 * 2. **`@Id`**:
 * - Đánh dấu trường `id` là trường đại diện cho ID của tài liệu trong MongoDB.
 * MongoDB sẽ tự động tạo ra trường `_id`, nhưng bạn có thể sử dụng trường `id`
 * này để làm ID của đối tượng nếu cần thiết.
 * 
 * 3. **`@DBRef`**:
 * - `@DBRef` được sử dụng để thiết lập quan hệ giữa tài liệu `PaymentLog` và
 * tài liệu `Student` trong MongoDB. Điều này giúp bạn tham chiếu đến đối tượng
 * `Student` thay vì lưu trữ tất cả thông tin của sinh viên trong `PaymentLog`.
 * MongoDB sẽ lưu trữ ID của đối tượng `Student` và có thể tra cứu dữ liệu của
 * sinh viên khi cần thiết.
 * 
 * 4. **Trường `numOfPages`**:
 * - Trường này lưu số trang mà sinh viên thanh toán, có kiểu dữ liệu là `int`.
 * 
 * 5. **Trường `payDate`**:
 * - Lưu ngày và giờ của giao dịch thanh toán. Kiểu dữ liệu là `LocalDateTime`,
 * giúp bạn lưu trữ ngày và giờ chi tiết cho giao dịch. MongoDB sẽ lưu nó dưới
 * dạng `ISODate`, tương thích với định dạng ISO 8601.
 * 
 * 6. **Trường `paymentMethod`**:
 * - Lưu phương thức thanh toán (ví dụ: "Credit Card", "PayPal", "Cash", etc.).
 * Kiểu dữ liệu là `String`.
 * 
 * 7. **Trường `unitPrice`**:
 * - Lưu giá trị đơn vị của trang in, kiểu dữ liệu là `Integer`. Điều này giúp
 * xác định mức giá mỗi trang khi sinh viên thực hiện thanh toán.
 * 
 * ### Constructor `PaymentLog`
 * 
 * - Constructor này giúp tạo đối tượng `PaymentLog` từ các tham số như sinh
 * viên, số trang, ngày thanh toán, phương thức thanh toán và giá trị đơn vị
 * trang.
 * 
 * ```java
 * public PaymentLog(Student student, int numOfPages, LocalDateTime payDate,
 * String paymentMethod, Integer unitPrice) {
 * this.student = student;
 * this.numOfPages = numOfPages;
 * this.payDate = payDate;
 * this.paymentMethod = paymentMethod;
 * this.unitPrice = unitPrice;
 * }
 * ```
 * 
 * ### Các điểm cần lưu ý
 * 
 * 1. **Quan hệ giữa `PaymentLog` và `Student`**:
 * - Sử dụng `@DBRef`, khi lưu một `PaymentLog`, chỉ có ID của `Student` được
 * lưu trữ trong `PaymentLog`, không phải toàn bộ đối tượng `Student`. Điều này
 * giúp tiết kiệm không gian bộ nhớ và duy trì tính nhất quán trong cơ sở dữ
 * liệu MongoDB.
 * 
 * 2. **Lưu trữ ngày giờ với `LocalDateTime`**:
 * - `LocalDateTime` là một kiểu dữ liệu của Java giúp bạn lưu trữ ngày giờ chi
 * tiết. MongoDB sẽ lưu trữ nó dưới dạng `ISODate` khi bạn sử dụng kiểu dữ liệu
 * này trong Java, tương thích với định dạng chuẩn của MongoDB.
 * 
 * 3. **Tính mở rộng**:
 * - Bạn có thể mở rộng lớp `PaymentLog` bằng cách thêm các phương thức tính
 * toán tổng số tiền thanh toán dựa trên số trang và giá trị đơn vị trang hoặc
 * các tính năng khác liên quan đến thanh toán.
 * 
 * ### Ví dụ về sử dụng
 * 
 * Giả sử bạn muốn tạo một đối tượng `PaymentLog` và lưu nó vào MongoDB:
 * 
 * ```java
 * Student student = // lấy đối tượng sinh viên từ cơ sở dữ liệu
 * int numOfPages = 50;
 * LocalDateTime payDate = LocalDateTime.now();
 * String paymentMethod = "Credit Card";
 * Integer unitPrice = 5;
 * 
 * PaymentLog paymentLog = new PaymentLog(student, numOfPages, payDate,
 * paymentMethod, unitPrice);
 * 
 * // Lưu paymentLog vào cơ sở dữ liệu MongoDB
 * paymentLogRepository.save(paymentLog);
 * ```
 * 
 * ### Kết luận
 * 
 * Lớp `PaymentLog` của bạn đã mô hình hóa thành công các giao dịch thanh toán
 * cho sinh viên, sử dụng MongoDB để lưu trữ thông tin về ngày giờ thanh toán,
 * số lượng trang, phương thức thanh toán và giá trị đơn vị trang. Sử dụng
 * `@DBRef` để tham chiếu đến đối tượng `Student` giúp dữ liệu được tổ chức rõ
 * ràng và hiệu quả trong cơ sở dữ liệu MongoDB.
 */