// package com.se.ssps.server.entity.user;

// import java.util.List;

// import org.springframework.data.annotation.Id;

// import org.springframework.data.mongodb.core.mapping.Document;
// import com.se.ssps.server.entity.PaymentLog;
// import com.se.ssps.server.entity.PrintingLog;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Document(collection = "student")
// @NoArgsConstructor
// @Getter
// @Setter
// public class Student {

//     @Id
//     private String id; // MongoDB tự động tạo id

//     private User user; // Nhúng trực tiếp User vào Student

//     private Long mssv; // Số sinh viên

//     private Integer balance = 0; // Số dư tài khoản, với giá trị mặc định

//     private List<PrintingLog> printingLogs; // Nhúng trực tiếp các log in tài liệu

//     private List<PaymentLog> paymentLogs; // Nhúng trực tiếp các log thanh toán

//     public Student(Long mSSV, Integer balance) {
//         this.mssv = mSSV;
//         this.balance = balance;
//     }
// }


package com.se.ssps.server.entity.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.se.ssps.server.entity.PaymentLog;
import com.se.ssps.server.entity.PrintingLog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    private String id; // MongoDB tự động tạo id

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Long studentNumber; // MSSV
    // private Integer balance = 0; // Số dư tài giấy, giá trị mặc định là 0
    private Integer numA3Pages = 0; // Số lượng giấy A3
    private Integer numA4Pages = 0; // Số lượng giấy A4

    private Double outstandingAmount = 0.0; // Số tiền cần thanh toán, khởi tạo bằng 0
    //private List<PrintingLog> printingLogs; // Nhúng trực tiếp log in tài liệu
    //private List<PaymentLog> paymentLogs; // Nhúng trực tiếp log thanh toán
    @DBRef
    private List<PrintingLog> printingLogs = new ArrayList<>();
    
    @DBRef
    private List<PaymentLog> paymentLogs = new ArrayList<>();

    public Student(String firstName, String lastName, String username, String password, Long studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.studentNumber = studentNumber;
    }
}

/*
 * Lớp `Student` trong mã bạn đã cung cấp là một tài liệu MongoDB đại diện cho
 * một sinh viên trong hệ thống. Dưới đây là phân tích chi tiết về lớp
 * `Student`.
 * 
 * ### Phân tích lớp `Student`
 * 
 * ```java
 * package com.se.ssps.server.entity.user;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * import com.se.ssps.server.entity.PaymentLog;
 * import com.se.ssps.server.entity.PrintingLog;
 * 
 * import lombok.Getter;
 * import lombok.NoArgsConstructor;
 * import lombok.Setter;
 * 
 * @Document(collection = "student") // Đặt tên collection trong MongoDB
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * public class Student {
 * 
 * @Id
 * private String id; // MongoDB tự động tạo id
 * 
 * private User user; // Nhúng trực tiếp User vào Student
 * 
 * private Long mssv; // Số sinh viên
 * 
 * private Integer balance = 0; // Số dư tài khoản, với giá trị mặc định
 * 
 * private List<PrintingLog> printingLogs; // Nhúng trực tiếp các log in tài
 * liệu
 * 
 * private List<PaymentLog> paymentLogs; // Nhúng trực tiếp các log thanh toán
 * 
 * public Student(Long mSSV, Integer balance) {
 * this.mssv = mSSV;
 * this.balance = balance;
 * }
 * }
 * ```
 * 
 * #### Các thành phần trong lớp `Student`:
 * 
 * 1. **`@Document(collection = "student")`**:
 * - Đây là annotation của Spring Data MongoDB, chỉ định rằng lớp `Student` sẽ
 * được lưu trữ trong MongoDB dưới tên collection `student`.
 * 
 * 2. **`id`**:
 * - Trường này là khóa chính của tài liệu trong MongoDB. MongoDB tự động tạo
 * giá trị `ObjectId` cho trường này nếu bạn không chỉ định giá trị cụ thể.
 * Trường này giúp xác định mỗi đối tượng `Student` một cách duy nhất trong
 * database.
 * 
 * 3. **`user`**:
 * - Trường này là một đối tượng `User` được nhúng trực tiếp vào trong tài liệu
 * `Student`. Điều này có nghĩa là thông tin chi tiết của sinh viên (như tên
 * đăng nhập, mật khẩu, email, v.v.) sẽ được lưu trực tiếp trong tài liệu
 * `Student` mà không cần phải truy vấn tài liệu `User` riêng biệt.
 * 
 * 4. **`mssv`**:
 * - Đây là trường chứa số sinh viên (`MSSV`). Trường này sẽ dùng để định danh
 * sinh viên trong hệ thống, mỗi sinh viên có một mã số sinh viên duy nhất.
 * 
 * 5. **`balance`**:
 * - Trường này lưu trữ số dư tài khoản của sinh viên (một loại ví điện tử trong
 * hệ thống). Giá trị mặc định là 0.
 * 
 * 6. **`printingLogs`**:
 * - Trường này là một danh sách các log in tài liệu, được nhúng trực tiếp vào
 * đối tượng `Student`. Một `PrintingLog` có thể chứa các thông tin về lần in
 * tài liệu của sinh viên như thời gian, số lượng bản in, v.v.
 * 
 * 7. **`paymentLogs`**:
 * - Trường này là một danh sách các log thanh toán, được nhúng vào trong đối
 * tượng `Student`. Một `PaymentLog` có thể lưu trữ thông tin về các giao dịch
 * thanh toán mà sinh viên đã thực hiện.
 * 
 * 8. **Constructor**:
 * - Constructor `Student(Long mSSV, Integer balance)` tạo ra một đối tượng
 * `Student` với số sinh viên và số dư tài khoản. Nó sẽ giúp khởi tạo các đối
 * tượng `Student` mà không cần thông tin chi tiết từ `User` hoặc các log.
 * 
 * 9. **`@Getter`, `@Setter`, `@NoArgsConstructor`**:
 * - Các annotation Lombok này tự động tạo các phương thức getter, setter và
 * constructor không tham số cho lớp.
 * 
 * ### **Mối quan hệ với các lớp khác**
 * 
 * - **`User`**: Trường `user` nhúng đối tượng `User`, có thể chứa thông tin
 * người dùng cơ bản như tên, mật khẩu, email, và vai trò. Điều này có nghĩa là
 * mỗi sinh viên trong hệ thống sẽ có một tài khoản người dùng để đăng nhập.
 * 
 * - **`PrintingLog` và `PaymentLog`**: Các trường `printingLogs` và
 * `paymentLogs` là danh sách các đối tượng lưu trữ log về các lần in và thanh
 * toán của sinh viên. Điều này giúp dễ dàng theo dõi các hoạt động liên quan
 * đến tài khoản của sinh viên mà không cần phải truy vấn từ các tài liệu khác.
 * 
 * ### **Tích hợp MongoDB**
 * 
 * MongoDB hỗ trợ việc nhúng các đối tượng khác vào trong tài liệu (document)
 * thay vì sử dụng quan hệ tham chiếu (DBRef). Nhúng các đối tượng này vào
 * `Student` giúp bạn có thể truy vấn thông tin sinh viên cùng với các log in và
 * thanh toán trong một tài liệu duy nhất mà không cần phải thực hiện các truy
 * vấn bổ sung. Điều này đơn giản hóa quá trình truy xuất dữ liệu và cải thiện
 * hiệu suất.
 * 
 * ### **Repository**
 * 
 * ```java
 * package com.se.ssps.server.repository.user;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.user.Student;
 * 
 * public interface StudentRepository extends MongoRepository<Student, String> {
 * // Các phương thức tìm kiếm sinh viên nếu cần
 * Student findByMssv(Long mssv);
 * }
 * ```
 * 
 * - **`StudentRepository`** là interface kế thừa từ `MongoRepository`, giúp bạn
 * thực hiện các thao tác cơ bản như lưu, xóa, và truy vấn sinh viên từ MongoDB.
 * Ví dụ, phương thức `findByMssv(Long mssv)` có thể được sử dụng để tìm kiếm
 * sinh viên theo mã số sinh viên.
 * 
 * ### **Service**
 * 
 * ```java
 * package com.se.ssps.server.service.user;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.user.Student;
 * import com.se.ssps.server.repository.user.StudentRepository;
 * 
 * @Service
 * public class StudentService {
 * 
 * @Autowired
 * private StudentRepository studentRepository;
 * 
 * public Student saveStudent(Student student) {
 * return studentRepository.save(student);
 * }
 * 
 * public Student getStudentByMssv(Long mssv) {
 * return studentRepository.findByMssv(mssv);
 * }
 * 
 * public void addPaymentLog(Long mssv, PaymentLog paymentLog) {
 * Student student = studentRepository.findByMssv(mssv);
 * if (student != null) {
 * student.getPaymentLogs().add(paymentLog);
 * studentRepository.save(student);
 * }
 * }
 * }
 * ```
 * 
 * - **`StudentService`** cung cấp các phương thức để lưu và truy vấn sinh viên
 * từ MongoDB, cũng như các phương thức để thao tác với các log thanh toán, log
 * in tài liệu của sinh viên.
 * 
 * ### Kết luận
 * 
 * Lớp `Student` giúp tổ chức thông tin sinh viên trong hệ thống, bao gồm cả
 * thông tin cá nhân từ `User`, số sinh viên (`mssv`), số dư tài khoản, cùng các
 * log liên quan đến việc thanh toán và in ấn. Nhờ vào việc nhúng các đối tượng
 * con vào trong `Student`, bạn có thể lưu trữ và truy vấn tất cả các thông tin
 * này trong một tài liệu MongoDB duy nhất.
 */