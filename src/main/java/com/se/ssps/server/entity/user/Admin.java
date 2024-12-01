// package com.se.ssps.server.entity.user;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Document(collection = "admin")
// @Getter
// @Setter
// @NoArgsConstructor
// public class Admin {

//     @Id
//     private String id;

//     private User user; // Nhúng đối tượng User vào trong Admin, thay vì tham chiếu
// }

package com.se.ssps.server.entity.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "admins") // Tên collection trong MongoDB
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    private String id; // MongoDB sẽ tự động tạo id nếu không được gán

    private String firstName;  // Họ admin
    private String lastName;   // Tên admin
    private String username;   // Tên đăng nhập
    private String password;   // Mật khẩu
   

    public Admin(String firstName, String lastName, String username, String password, String adminLevel, Boolean isSuperAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
     
    }
}



/*
 * Lớp `Admin` trong mã bạn đã cung cấp là một đối tượng MongoDB đại diện cho
 * một người quản trị (admin) trong hệ thống. Nó có một trường `user` là đối
 * tượng của lớp `User`, điều này có thể dùng để mô tả rằng mỗi admin là một
 * người dùng hệ thống nhưng có quyền quản trị đặc biệt.
 * 
 * ### Phân tích chi tiết lớp `Admin`
 * 
 * ```java
 * package com.se.ssps.server.entity.user;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * 
 * import lombok.Getter;
 * import lombok.NoArgsConstructor;
 * import lombok.Setter;
 * 
 * @Document(collection = "admin") // Đặt tên collection trong MongoDB
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * public class Admin {
 * 
 * @Id
 * private String id; // ID MongoDB (chuỗi hoặc ObjectId)
 * 
 * private User user; // Nhúng đối tượng User vào trong Admin, thay vì tham
 * chiếu
 * }
 * ```
 * 
 * #### Các thành phần trong lớp `Admin`:
 * 
 * 1. **`@Document(collection = "admin")`**:
 * - Đây là annotation của Spring Data MongoDB, chỉ định rằng lớp `Admin` là một
 * tài liệu trong MongoDB. `admin` sẽ là tên của collection lưu trữ các đối
 * tượng này trong MongoDB.
 * 
 * 2. **`id`**:
 * - Trường này là khóa chính của tài liệu trong MongoDB. MongoDB sẽ tự động tạo
 * giá trị `ObjectId` cho trường này nếu bạn không chỉ định giá trị cụ thể.
 * 
 * 3. **`user`**:
 * - Trường này lưu trữ một đối tượng `User`. Điều này có nghĩa là mỗi admin
 * không chỉ có thông tin quản trị mà còn có thông tin người dùng (như tên,
 * email, mật khẩu, v.v.). Việc nhúng đối tượng `User` vào trong `Admin` có
 * nghĩa là bạn không cần sử dụng quan hệ tham chiếu (DBRef) trong MongoDB mà
 * thay vào đó là lưu trực tiếp đối tượng `User` vào trong tài liệu `Admin`.
 * 
 * 4. **Các getter và setter (tạo tự động)**:
 * - Các annotation `@Getter`, `@Setter` từ Lombok giúp tự động tạo ra các
 * phương thức getter và setter cho các trường trong lớp.
 * 
 * 5. **`@NoArgsConstructor`**:
 * - Annotation này yêu cầu một constructor mặc định không tham số, điều này cần
 * thiết khi bạn làm việc với MongoDB, vì MongoDB cần có cách để tạo đối tượng
 * khi lưu hoặc truy vấn từ database.
 * 
 * ### **Mối quan hệ giữa `Admin` và `User`**
 * 
 * - Trong hệ thống của bạn, có vẻ như mỗi admin là một `User` với quyền quản
 * trị. Việc nhúng `User` vào trong `Admin` giúp lưu trữ thông tin chi tiết của
 * người dùng mà không cần phải tạo quan hệ tham chiếu giữa `Admin` và `User`.
 * - **Điều này khác với DBRef**, nơi một trường `user` trong `Admin` sẽ lưu trữ
 * một ID tham chiếu đến một tài liệu `User` riêng biệt. Ở đây, việc nhúng
 * `User` giúp tránh việc thực hiện truy vấn bổ sung để lấy thông tin người dùng
 * khi bạn đã có đối tượng `User` trong `Admin`.
 * 
 * ### Ví dụ về lớp `User`
 * 
 * ```java
 * package com.se.ssps.server.entity.user;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * 
 * import lombok.Getter;
 * import lombok.NoArgsConstructor;
 * import lombok.Setter;
 * 
 * @Document(collection = "user") // Đặt tên collection trong MongoDB
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * public class User {
 * 
 * @Id
 * private String id;
 * 
 * private String username;
 * private String password;
 * private String email;
 * private String role; // Ví dụ: "ADMIN", "USER", v.v.
 * }
 * ```
 * 
 * - **`User`** có thể lưu trữ các thông tin về người dùng cơ bản như tên đăng
 * nhập, mật khẩu, email, và vai trò (role). Trong lớp `Admin`, đối tượng `User`
 * này sẽ chứa thông tin chi tiết của người dùng quản trị.
 * 
 * ### Tích hợp với MongoDB
 * 
 * Với mô hình này, khi bạn lưu một đối tượng `Admin`, MongoDB sẽ lưu trữ thông
 * tin về admin cùng với thông tin người dùng trong một tài liệu duy nhất. Điều
 * này đơn giản hóa việc quản lý dữ liệu và truy vấn vì không cần phải kết hợp
 * dữ liệu từ hai tài liệu khác nhau (admin và user).
 * 
 * ### Repository
 * 
 * ```java
 * package com.se.ssps.server.repository.user;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.user.Admin;
 * 
 * public interface AdminRepository extends MongoRepository<Admin, String> {
 * // Các phương thức tìm kiếm admin nếu cần
 * Admin findById(String id);
 * }
 * ```
 * 
 * - **`AdminRepository`** là repository cho `Admin`, giúp truy vấn và thao tác
 * với MongoDB. Bạn có thể tạo thêm các phương thức tìm kiếm theo yêu cầu, ví
 * dụ: tìm `Admin` theo `username` hoặc các trường khác.
 * 
 * ### Service
 * 
 * ```java
 * package com.se.ssps.server.service.user;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.user.Admin;
 * import com.se.ssps.server.repository.user.AdminRepository;
 * 
 * @Service
 * public class AdminService {
 * 
 * @Autowired
 * private AdminRepository adminRepository;
 * 
 * public Admin saveAdmin(Admin admin) {
 * return adminRepository.save(admin);
 * }
 * 
 * public Admin getAdminById(String id) {
 * return adminRepository.findById(id).orElse(null);
 * }
 * }
 * ```
 * 
 * - **`AdminService`** cung cấp các phương thức để lưu và lấy thông tin admin
 * từ MongoDB. Bạn có thể mở rộng các chức năng này tùy theo yêu cầu của dự án.
 * 
 * ---
 * 
 * ### Kết luận
 * 
 * Lớp `Admin` được thiết kế để kết hợp giữa thông tin người dùng (`User`) và
 * quyền quản trị. Việc nhúng đối tượng `User` vào `Admin` giúp đơn giản hóa cấu
 * trúc dữ liệu và tránh việc phải thực hiện các truy vấn bổ sung cho dữ liệu
 * người dùng. Nếu bạn có thêm yêu cầu hoặc câu hỏi về mô hình này, cứ thoải mái
 * yêu cầu nhé!
 */