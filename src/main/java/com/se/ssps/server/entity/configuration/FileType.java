package com.se.ssps.server.entity.configuration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "fileType") // Chỉ định collection MongoDB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileType {
    @Id
    private String id; // MongoDB sử dụng ObjectId dạng chuỗi làm khóa chính

    private String fileTypeName;

    // private boolean type; // Giữ nguyên nếu cần
}







/*
 * ### Ý nghĩa của lớp `FileType`
 * 
 * #### 1. **Vai trò trong hệ thống**:
 * - **`FileType`** đại diện cho các loại tệp tin được phép in trong hệ thống
 * **Student Smart Printing Service (HCMUT_SSPS)**.
 * - Đây là một phần cấu hình mà **SPSO (Student Printing Service Officer)** có
 * thể quản lý để kiểm soát và giới hạn các định dạng tệp mà sinh viên có thể
 * tải lên để in, đảm bảo tính tương thích và an toàn cho hệ thống.
 * 
 * #### 2. **Giải thích các thành phần trong lớp**
 * 
 * ```java
 * 
 * @Document(collection = "fileType") // Chỉ định collection MongoDB
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * public class FileType {
 * 
 * @Id
 * private String id; // MongoDB sử dụng ObjectId dạng chuỗi làm khóa chính
 * 
 * private String fileTypeName;
 * 
 * // private boolean type; // Giữ nguyên nếu cần
 * }
 * ```
 * 
 * - **Annotation `@Document(collection = "fileType")`:**
 * - Đánh dấu lớp này là một tài liệu (`Document`) trong MongoDB.
 * - Các đối tượng của lớp này sẽ được lưu trong collection có tên là
 * `fileType`.
 * 
 * - **Annotation Lombok:**
 * - `@AllArgsConstructor`: Tạo constructor với tất cả các tham số.
 * - `@NoArgsConstructor`: Tạo constructor không tham số.
 * - `@Getter` và `@Setter`: Tạo các phương thức getter và setter cho tất cả các
 * thuộc tính.
 * 
 * - **Thuộc tính:**
 * - `@Id private String id;`:
 * - Là khóa chính của tài liệu, do MongoDB tự động sinh ra nếu không chỉ định.
 * - Kiểu dữ liệu `String` phù hợp với `ObjectId` của MongoDB.
 * 
 * - `private String fileTypeName;`:
 * - Tên của loại tệp tin được phép in, ví dụ: `"PDF"`, `"DOCX"`, `"TXT"`,
 * `"JPEG"`, v.v.
 * 
 * - `// private boolean type;`:
 * - Dòng này hiện đang được chú thích (`commented out`).
 * - **Giữ nguyên nếu cần**: Có thể được sử dụng để biểu thị thêm thông tin về
 * loại tệp, ví dụ như:
 * - `true` cho các loại tệp văn bản.
 * - `false` cho các loại tệp hình ảnh.
 * - **Gợi ý:** Nếu không cần thêm thông tin này, có thể giữ nguyên. Nếu cần,
 * nên khai báo rõ ràng và giải thích mục đích sử dụng.
 * 
 * #### 3. **Mối quan hệ với các thực thể khác**
 * - **`FileType`** không có mối quan hệ trực tiếp với các thực thể khác trong
 * hệ thống như `Building` hay `Campus`.
 * - Tuy nhiên, nó là một phần của **cấu hình hệ thống** mà **SPSO** quản lý,
 * ảnh hưởng đến các chức năng như:
 * - **Tải lên tệp tin**: Hệ thống kiểm tra loại tệp tin được phép in dựa trên
 * các giá trị trong collection `fileType`.
 * - **Xác thực tệp tin**: Trước khi cho phép in, hệ thống sẽ xác thực rằng tệp
 * tin tải lên có định dạng hợp lệ.
 * 
 * #### 4. **Ý nghĩa trong hệ thống tổng thể**
 * - **Quản lý cấu hình tệp tin được phép in**:
 * - **SPSO** có thể thêm, sửa, hoặc xóa các loại tệp tin được phép in thông qua
 * giao diện quản trị.
 * - Điều này giúp linh hoạt trong việc điều chỉnh chính sách in ấn mà không cần
 * thay đổi mã nguồn.
 * 
 * - **Đảm bảo tính an toàn và tương thích**:
 * - Bằng cách giới hạn các loại tệp tin được phép in, hệ thống tránh được các
 * vấn đề liên quan đến bảo mật (ví dụ: tệp tin độc hại) và đảm bảo rằng các tệp
 * tin được in sẽ tương thích với các máy in và phần mềm xử lý in ấn.
 * 
 * #### 5. **Cách sử dụng trong Service và Controller**
 * - **Repository**:
 * - Tạo một repository để tương tác với collection `fileType`.
 * 
 * ```java
 * package com.se.ssps.server.repository.configuration;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import org.springframework.stereotype.Repository;
 * import com.se.ssps.server.entity.configuration.FileType;
 * 
 * @Repository
 * public interface FileTypeRepository extends MongoRepository<FileType, String>
 * {
 * FileType findByFileTypeName(String fileTypeName);
 * }
 * ```
 * 
 * - **Service**:
 * - Tạo một service để quản lý các loại tệp tin được phép in.
 * 
 * ```java
 * package com.se.ssps.server.service.configuration;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * 
 * import com.se.ssps.server.entity.configuration.FileType;
 * import com.se.ssps.server.repository.configuration.FileTypeRepository;
 * 
 * @Service
 * public class FileTypeService {
 * 
 * @Autowired
 * private FileTypeRepository fileTypeRepository;
 * 
 * public List<FileType> getAllFileTypes() {
 * return fileTypeRepository.findAll();
 * }
 * 
 * public FileType getFileTypeByName(String fileTypeName) {
 * return fileTypeRepository.findByFileTypeName(fileTypeName);
 * }
 * 
 * public FileType addFileType(FileType fileType) {
 * return fileTypeRepository.save(fileType);
 * }
 * 
 * public void deleteFileType(String id) {
 * fileTypeRepository.deleteById(id);
 * }
 * 
 * public FileType updateFileType(FileType fileType) {
 * return fileTypeRepository.save(fileType);
 * }
 * }
 * ```
 * 
 * - **Controller**:
 * - Tạo các endpoint để **SPSO** quản lý các loại tệp tin được phép in.
 * 
 * ```java
 * package com.se.ssps.server.controller.configuration;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.ResponseEntity;
 * import org.springframework.web.bind.annotation.*;
 * 
 * import com.se.ssps.server.entity.configuration.FileType;
 * import com.se.ssps.server.service.configuration.FileTypeService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/filetypes")
 * public class FileTypeController {
 * 
 * @Autowired
 * private FileTypeService fileTypeService;
 * 
 * // Lấy danh sách tất cả các loại tệp tin
 * 
 * @GetMapping
 * public ResponseEntity<List<FileType>> getAllFileTypes() {
 * return ResponseEntity.ok(fileTypeService.getAllFileTypes());
 * }
 * 
 * // Thêm một loại tệp tin mới
 * 
 * @PostMapping
 * public ResponseEntity<FileType> addFileType(@RequestBody FileType fileType) {
 * return ResponseEntity.ok(fileTypeService.addFileType(fileType));
 * }
 * 
 * // Cập nhật một loại tệp tin
 * 
 * @PutMapping("/{id}")
 * public ResponseEntity<FileType> updateFileType(@PathVariable String
 * id, @RequestBody FileType fileType) {
 * fileType.setId(id);
 * return ResponseEntity.ok(fileTypeService.updateFileType(fileType));
 * }
 * 
 * // Xóa một loại tệp tin
 * 
 * @DeleteMapping("/{id}")
 * public ResponseEntity<Void> deleteFileType(@PathVariable String id) {
 * fileTypeService.deleteFileType(id);
 * return ResponseEntity.noContent().build();
 * }
 * 
 * // Lấy thông tin một loại tệp tin theo tên
 * 
 * @GetMapping("/name/{fileTypeName}")
 * public ResponseEntity<FileType> getFileTypeByName(@PathVariable String
 * fileTypeName) {
 * return ResponseEntity.ok(fileTypeService.getFileTypeByName(fileTypeName));
 * }
 * }
 * ```
 * 
 * #### 6. **Khuyến nghị và Cải tiến**
 * - **Xác thực và Kiểm tra Dữ liệu**:
 * - **Validation**: Thêm các annotation như `@NotBlank` hoặc `@Pattern` để xác
 * thực `fileTypeName`.
 * ```java
 * import javax.validation.constraints.NotBlank;
 * 
 * @Document(collection = "fileType")
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * public class FileType {
 * 
 * @Id
 * private String id;
 * 
 * @NotBlank(message = "File type name cannot be blank")
 * private String fileTypeName;
 * 
 * // private boolean type; // Nếu cần
 * }
 * ```
 * 
 * - **Exception Handling**: Xử lý các ngoại lệ khi thêm, cập nhật hoặc xóa
 * `FileType` trong controller để trả về các phản hồi thích hợp.
 * 
 * - **Bảo mật**:
 * - **Authorization**: Đảm bảo chỉ **SPSO** mới có quyền quản lý các loại tệp
 * tin được phép in. Có thể sử dụng Spring Security để kiểm tra vai trò người
 * dùng trước khi truy cập các endpoint này.
 * 
 * ```java
 * 
 * @PreAuthorize("hasRole('SPSO')")
 * 
 * @PostMapping
 * public ResponseEntity<FileType> addFileType(@RequestBody @Valid FileType
 * fileType) {
 * return ResponseEntity.ok(fileTypeService.addFileType(fileType));
 * }
 * ```
 * 
 * - **Quản lý trạng thái xóa (Soft Delete)**:
 * - Nếu bạn muốn thực hiện **soft delete** cho `FileType`, thêm thuộc tính
 * `isDel` tương tự như trong các lớp `Building` và `Campus`.
 * 
 * ```java
 * private boolean isDel = false; // Mặc định chưa xóa
 * ```
 * 
 * - Cập nhật các phương thức trong `FileTypeService` để chỉ trả về các loại tệp
 * tin chưa bị xóa.
 * 
 * ```java
 * public List<FileType> getAllFileTypes() {
 * return fileTypeRepository.findByIsDelFalse();
 * }
 * 
 * // Trong repository
 * List<FileType> findByIsDelFalse();
 * ```
 * 
 * #### 7. **Tóm tắt**
 * - **`FileType`** là một thực thể quan trọng trong hệ thống HCMUT_SSPS, giúp
 * **SPSO** quản lý và kiểm soát các loại tệp tin được phép in.
 * - Cấu trúc lớp `FileType` rõ ràng và dễ hiểu, phù hợp với các yêu cầu của đề
 * bài.
 * - Việc quản lý các loại tệp tin thông qua entity này giúp hệ thống linh hoạt,
 * dễ bảo trì và mở rộng trong tương lai.
 * 
 * Nếu bạn có thêm các file khác hoặc cần giải thích thêm về bất kỳ phần nào
 * trong hệ thống, hãy tiếp tục gửi nhé! 😊
 */