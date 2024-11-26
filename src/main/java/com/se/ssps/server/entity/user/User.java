package com.se.ssps.server.entity.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users") // Chuyển từ JPA Entity sang MongoDB Document
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id; // MongoDB sẽ tự động tạo id nếu không chỉ định

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isAdmin;

    // Thay quan hệ OneToOne với Student và Admin bằng cách nhúng hoặc tham chiếu
    @DBRef // DBRef để tham chiếu đối tượng Student trong MongoDB
    @JsonIgnore
    private Student student;

    @DBRef // DBRef để tham chiếu đối tượng Admin trong MongoDB
    @JsonIgnore
    private Admin admin;

    public User(String firstName, String lastName, String username, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}



/*
 * Lớp `User` trong mã của bạn là một tài liệu MongoDB được sử dụng để lưu trữ
 * thông tin người dùng trong hệ thống. Dưới đây là phân tích chi tiết về lớp
 * `User` này:
 * 
 * ### Phân tích lớp `User`
 * 
 * ```java
 * package com.se.ssps.server.entity.user;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.DBRef;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * import com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * import lombok.Getter;
 * import lombok.NoArgsConstructor;
 * import lombok.Setter;
 * 
 * @Document(collection = "users") // Chuyển từ JPA Entity sang MongoDB Document
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * public class User {
 * 
 * @Id
 * private String id; // MongoDB sẽ tự động tạo id nếu không chỉ định
 * 
 * private String firstName;
 * private String lastName;
 * private String username;
 * private String password;
 * private Boolean isAdmin;
 * 
 * // Thay quan hệ OneToOne với Student và Admin bằng cách nhúng hoặc tham chiếu
 * 
 * @DBRef // DBRef để tham chiếu đối tượng Student trong MongoDB
 * 
 * @JsonIgnore
 * private Student student;
 * 
 * @DBRef // DBRef để tham chiếu đối tượng Admin trong MongoDB
 * 
 * @JsonIgnore
 * private Admin admin;
 * 
 * public User(String firstName, String lastName, String username, String
 * password, Boolean isAdmin) {
 * this.firstName = firstName;
 * this.lastName = lastName;
 * this.username = username;
 * this.password = password;
 * this.isAdmin = isAdmin;
 * }
 * }
 * ```
 * 
 * ### Các thành phần trong lớp `User`:
 * 
 * 1. **`@Document(collection = "users")`**:
 * - Đây là annotation của Spring Data MongoDB, chỉ định rằng lớp `User` sẽ được
 * lưu trữ trong MongoDB dưới tên collection là `users`.
 * 
 * 2. **`id`**:
 * - Trường này là khóa chính của tài liệu trong MongoDB. MongoDB sẽ tự động tạo
 * giá trị `ObjectId` cho trường này nếu bạn không chỉ định giá trị cụ thể.
 * Trường này giúp xác định mỗi đối tượng `User` một cách duy nhất trong
 * database.
 * 
 * 3. **Thông tin người dùng**:
 * - **`firstName`**: Tên người dùng.
 * - **`lastName`**: Họ người dùng.
 * - **`username`**: Tên đăng nhập của người dùng.
 * - **`password`**: Mật khẩu của người dùng.
 * - **`isAdmin`**: Trường boolean xác định liệu người dùng có phải là admin hay
 * không.
 * 
 * 4. **`student` và `admin`**:
 * - **`@DBRef`**: Trường này được sử dụng để tham chiếu đến các tài liệu khác
 * trong MongoDB.
 * - Trường `student` tham chiếu đến đối tượng `Student` trong MongoDB, sử dụng
 * annotation `@DBRef`. Điều này có nghĩa là `User` có thể có một quan hệ tham
 * chiếu với một sinh viên.
 * - Trường `admin` tham chiếu đến đối tượng `Admin`, sử dụng `@DBRef` tương tự
 * để tham chiếu đến tài liệu `Admin` trong MongoDB.
 * - **`@JsonIgnore`**: Annotation này chỉ định rằng các trường này sẽ bị loại
 * trừ khi dữ liệu được chuyển đổi thành JSON, tránh việc hiển thị thông tin
 * nhạy cảm hoặc tránh tham chiếu vòng lặp không mong muốn.
 * 
 * 5. **Constructor**:
 * - Constructor `User(String firstName, String lastName, String username,
 * String password, Boolean isAdmin)` giúp khởi tạo đối tượng `User` với các
 * thông tin cơ bản về người dùng, bao gồm tên, username, password và vai trò
 * admin.
 * 
 * 6. **`@Getter`, `@Setter`, `@NoArgsConstructor`**:
 * - Các annotation của Lombok giúp tự động tạo các phương thức getter, setter
 * và constructor không tham số cho lớp.
 * 
 * ### Quan hệ với các lớp khác:
 * 
 * - **`Student` và `Admin`**: Lớp `User` có thể tham chiếu đến một đối tượng
 * `Student` hoặc một đối tượng `Admin` bằng cách sử dụng `@DBRef`. Điều này cho
 * phép mỗi người dùng có thể là một sinh viên hoặc là một quản trị viên, tùy
 * thuộc vào giá trị trường `isAdmin`.
 * 
 * - Trường `student` dùng để lưu trữ thông tin sinh viên, liên kết với đối
 * tượng `Student`. Nếu người dùng là sinh viên, thông tin của họ sẽ được tham
 * chiếu từ tài liệu `Student`.
 * - Trường `admin` lưu trữ thông tin quản trị viên, liên kết với đối tượng
 * `Admin`. Nếu người dùng là quản trị viên, thông tin của họ sẽ được tham chiếu
 * từ tài liệu `Admin`.
 * 
 * ### Tích hợp MongoDB
 * 
 * - MongoDB hỗ trợ việc lưu trữ các tài liệu tham chiếu với nhau thông qua
 * `@DBRef`. Tuy nhiên, `@DBRef` có thể dẫn đến việc truy vấn dữ liệu không hiệu
 * quả nếu bạn có nhiều tham chiếu phức tạp. Trong trường hợp này, nếu bạn chỉ
 * cần một vài thông tin về `Student` và `Admin` (ví dụ như tên hoặc email), có
 * thể bạn nên nhúng thông tin đó trực tiếp vào `User` thay vì sử dụng `@DBRef`,
 * để tránh phải thực hiện các truy vấn phụ mỗi khi lấy dữ liệu.
 * 
 * ### Repository
 * 
 * ```java
 * package com.se.ssps.server.repository.user;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.user.User;
 * 
 * public interface UserRepository extends MongoRepository<User, String> {
 * // Các phương thức tìm kiếm người dùng nếu cần
 * User findByUsername(String username);
 * }
 * ```
 * 
 * - **`UserRepository`** là interface kế thừa từ `MongoRepository`, giúp bạn
 * thực hiện các thao tác cơ bản như lưu, xóa, và truy vấn người dùng từ
 * MongoDB. Ví dụ, phương thức `findByUsername(String username)` có thể được sử
 * dụng để tìm kiếm người dùng dựa trên tên đăng nhập.
 * 
 * ### Service
 * 
 * ```java
 * package com.se.ssps.server.service.user;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.user.User;
 * import com.se.ssps.server.repository.user.UserRepository;
 * 
 * @Service
 * public class UserService {
 * 
 * @Autowired
 * private UserRepository userRepository;
 * 
 * public User saveUser(User user) {
 * return userRepository.save(user);
 * }
 * 
 * public User getUserByUsername(String username) {
 * return userRepository.findByUsername(username);
 * }
 * }
 * ```
 * 
 * - **`UserService`** cung cấp các phương thức để lưu và truy vấn người dùng từ
 * MongoDB, đồng thời cung cấp các thao tác bổ sung như xử lý đăng nhập, quản lý
 * tài khoản người dùng.
 * 
 * ### Kết luận
 * 
 * Lớp `User` là trung tâm của hệ thống người dùng, lưu trữ thông tin cơ bản của
 * người dùng như tên, mật khẩu, và vai trò (admin). Thêm vào đó, lớp này còn
 * tham chiếu đến các đối tượng `Student` và `Admin` bằng cách sử dụng `@DBRef`.
 * Với MongoDB, bạn có thể dễ dàng lưu trữ các đối tượng phức tạp và tham chiếu
 * giữa các tài liệu, giúp hệ thống linh hoạt và mở rộng.
 */