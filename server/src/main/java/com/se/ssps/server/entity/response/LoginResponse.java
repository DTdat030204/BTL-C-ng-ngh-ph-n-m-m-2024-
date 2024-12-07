package com.se.ssps.server.entity.response;

//import com.se.ssps.server.entity.user.User;
// import org.springframework.data.mongodb.core.mapping.Document;
// import org.springframework.data.annotation.Id;
//import com.se.ssps.server.entity.user.Student;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Object user; // Dùng kiểu Object để hỗ trợ cả Admin và Student
    private boolean correctPass;
}

/*
 * Lớp **`LoginResponse`** này có vai trò quan trọng trong việc lưu trữ phản hồi
 * khi người dùng đăng nhập, bao gồm thông tin về người dùng, trạng thái mật
 * khẩu và thông báo lỗi nếu có. Dưới đây là phân tích chi tiết về lớp
 * `LoginResponse`.
 * 
 * ### 1. **Chi tiết lớp `LoginResponse`**
 * 
 * ```java
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @Document(collection = "login_responses") // Đặt tên collection trong MongoDB
 * public class LoginResponse {
 * 
 * @Id
 * private String id; // Trường ID trong MongoDB (có thể sử dụng String hoặc
 * ObjectId)
 * 
 * private User user;
 * private boolean isCorrectPass;
 * private String errorMessage; // Thêm trường này để hiển thị lỗi
 * }
 * ```
 * 
 * #### 2. **Các thành phần trong lớp**
 * 
 * 1. **`@Document(collection = "login_responses")`**:
 * - Đánh dấu lớp `LoginResponse` là một tài liệu trong MongoDB, và tên
 * collection trong MongoDB sẽ là `login_responses`.
 * 
 * 2. **`id`**:
 * - Đây là khóa chính của tài liệu trong MongoDB. Trường này có thể sử dụng
 * `String` hoặc `ObjectId`, tùy thuộc vào cách MongoDB tự động tạo và ánh xạ
 * ID.
 * 
 * 3. **`user`**:
 * - Đại diện cho người dùng đã đăng nhập. Đây là một đối tượng thuộc lớp
 * `User`, có thể chứa các thông tin như tên người dùng, email, quyền hạn, v.v.
 * - **`User`** có thể là một lớp riêng biệt trong hệ thống của bạn để lưu trữ
 * thông tin về người dùng.
 * 
 * 4. **`isCorrectPass`**:
 * - Đây là một biến kiểu boolean, cho biết mật khẩu người dùng nhập có chính
 * xác hay không.
 * - Nếu `true`, mật khẩu người dùng nhập là đúng; nếu `false`, mật khẩu không
 * chính xác.
 * 
 * 5. **`errorMessage`**:
 * - Trường này chứa thông báo lỗi khi đăng nhập, ví dụ như "Sai mật khẩu" hoặc
 * "Tài khoản không tồn tại".
 * - Trường này có thể được sử dụng để cung cấp thêm thông tin phản hồi cho
 * người dùng khi đăng nhập thất bại.
 * 
 * ---
 * 
 * ### 3. **Repository**
 * 
 * ```java
 * package com.se.ssps.server.repository.response;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.response.LoginResponse;
 * 
 * public interface LoginResponseRepository extends
 * MongoRepository<LoginResponse, String> {
 * // Các phương thức truy vấn MongoDB nếu cần, ví dụ:
 * LoginResponse findById(String id);
 * }
 * ```
 * 
 * - **`LoginResponseRepository`** là một interface kế thừa từ
 * `MongoRepository`, cho phép thực hiện các thao tác cơ bản với MongoDB, ví dụ
 * như tìm kiếm, lưu, và xóa các tài liệu `LoginResponse`.
 * 
 * ---
 * 
 * ### 4. **Service**
 * 
 * ```java
 * package com.se.ssps.server.service.response;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.response.LoginResponse;
 * import com.se.ssps.server.repository.response.LoginResponseRepository;
 * 
 * @Service
 * public class LoginResponseService {
 * 
 * @Autowired
 * private LoginResponseRepository loginResponseRepository;
 * 
 * // Phương thức lưu phản hồi đăng nhập
 * public LoginResponse saveLoginResponse(LoginResponse loginResponse) {
 * return loginResponseRepository.save(loginResponse);
 * }
 * 
 * // Phương thức tìm phản hồi theo ID
 * public LoginResponse getLoginResponseById(String id) {
 * return loginResponseRepository.findById(id).orElse(null);
 * }
 * }
 * ```
 * 
 * - **`LoginResponseService`** là lớp dịch vụ thực hiện các nghiệp vụ liên quan
 * đến phản hồi đăng nhập. Ví dụ, nó có thể lưu phản hồi đăng nhập hoặc tìm phản
 * hồi theo ID.
 * 
 * ---
 * 
 * ### 5. **Controller**
 * 
 * ```java
 * package com.se.ssps.server.controller.response;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.*;
 * import com.se.ssps.server.entity.response.LoginResponse;
 * import com.se.ssps.server.service.response.LoginResponseService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/login-response")
 * public class LoginResponseController {
 * 
 * @Autowired
 * private LoginResponseService loginResponseService;
 * 
 * // Lưu phản hồi đăng nhập
 * 
 * @PostMapping
 * public ResponseEntity<LoginResponse> saveLoginResponse(@RequestBody
 * LoginResponse loginResponse) {
 * return
 * ResponseEntity.ok(loginResponseService.saveLoginResponse(loginResponse));
 * }
 * 
 * // Lấy phản hồi theo ID
 * 
 * @GetMapping("/{id}")
 * public ResponseEntity<LoginResponse> getLoginResponseById(@PathVariable
 * String id) {
 * return ResponseEntity.ok(loginResponseService.getLoginResponseById(id));
 * }
 * }
 * ```
 * 
 * - **`LoginResponseController`** là lớp controller nhận và xử lý các yêu cầu
 * HTTP đối với phản hồi đăng nhập. Ví dụ, có thể gửi yêu cầu POST để lưu phản
 * hồi đăng nhập hoặc yêu cầu GET để lấy phản hồi theo ID.
 * 
 * ---
 * 
 * ### 6. **Tích hợp với hệ thống**
 * 
 * - Lớp **`LoginResponse`** có thể được sử dụng để lưu trữ thông tin đăng nhập
 * của người dùng. Sau mỗi lần người dùng đăng nhập, bạn có thể tạo một đối
 * tượng `LoginResponse` và lưu vào MongoDB để theo dõi kết quả đăng nhập (đúng
 * mật khẩu, sai mật khẩu, thông báo lỗi).
 * - Điều này giúp hệ thống có thể theo dõi và lưu trữ trạng thái đăng nhập của
 * người dùng, phục vụ cho các mục đích như phân tích lỗi đăng nhập, kiểm tra
 * hoạt động đăng nhập của người dùng, và phản hồi chính xác cho người dùng.
 * 
 * ---
 * 
 * ### 7. **Kết luận**
 * 
 * Lớp **`LoginResponse`** giúp hệ thống phản hồi một cách rõ ràng và chi tiết
 * về kết quả đăng nhập của người dùng, bao gồm người dùng đó có đăng nhập thành
 * công hay không và nếu không thành công, lý do thất bại. Bạn có thể mở rộng
 * thêm tính năng và tùy chỉnh theo nhu cầu cụ thể của dự án.
 * 
 * Nếu bạn cần hỗ trợ thêm hoặc muốn mở rộng các chức năng, cứ cho tôi biết nhé!
 */