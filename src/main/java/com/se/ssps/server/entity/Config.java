package com.se.ssps.server.entity;

import java.util.List;
import lombok.*;

import com.se.ssps.server.entity.configuration.FileType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "config") // MongoDB sẽ lưu đối tượng này trong collection "config"
public class Config {

    @Id
    private String id; // Có thể sử dụng _id của MongoDB hoặc một trường id tùy chỉnh

    private List<FileType> fileTypeList; // Một danh sách các đối tượng FileType

    private Double maxFileSize; // Kích thước tối đa của file

    private Integer pageUnitPrice; // Giá trị đơn vị của trang
}




/*
 * Lớp `Config` của bạn là một tài liệu MongoDB dùng để lưu trữ cấu hình hệ
 * thống, cụ thể là cấu hình liên quan đến các loại tệp tin, kích thước tối đa
 * của tệp và giá trị đơn vị trang. Dưới đây là phân tích chi tiết về lớp
 * `Config`:
 * 
 * ### Phân tích lớp `Config`
 * 
 * ```java
 * package com.se.ssps.server.entity;
 * 
 * import java.util.List;
 * import lombok.*;
 * 
 * import com.se.ssps.server.entity.configuration.FileType;
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @Document(collection = "config") // MongoDB sẽ lưu đối tượng này trong
 * collection "config"
 * public class Config {
 * 
 * @Id
 * private String id; // Có thể sử dụng _id của MongoDB hoặc một trường id tùy
 * chỉnh
 * 
 * private List<FileType> fileTypeList; // Một danh sách các đối tượng FileType
 * 
 * private Double maxFileSize; // Kích thước tối đa của file
 * 
 * private Integer pageUnitPrice; // Giá trị đơn vị của trang
 * }
 * ```
 * 
 * ### Các thành phần trong lớp `Config`:
 * 
 * 1. **`@Document(collection = "config")`**:
 * - Đây là annotation của Spring Data MongoDB, chỉ định rằng lớp `Config` sẽ
 * được lưu trữ trong MongoDB dưới tên collection là `config`.
 * 
 * 2. **`id`**:
 * - Trường `id` là khóa chính của tài liệu trong MongoDB. Nó có thể sử dụng
 * trường `_id` mặc định của MongoDB hoặc một trường `id` tùy chỉnh như trong
 * lớp này. Trường `id` sẽ giúp xác định đối tượng `Config` một cách duy nhất
 * trong database.
 * 
 * 3. **`fileTypeList`**:
 * - Trường này lưu trữ một danh sách các đối tượng `FileType`. `FileType` có
 * thể là một lớp khác được định nghĩa để mô tả các loại tệp hỗ trợ trong hệ
 * thống, ví dụ như PDF, DOCX, JPG, v.v.
 * - Danh sách này có thể chứa nhiều đối tượng `FileType`, đại diện cho các loại
 * tệp được hệ thống chấp nhận.
 * 
 * 4. **`maxFileSize`**:
 * - Trường này lưu trữ kích thước tối đa của tệp (ví dụ: tính bằng MB hoặc GB).
 * Trường này giúp hệ thống xác định các tệp không được vượt quá kích thước tối
 * đa khi tải lên hoặc xử lý.
 * 
 * 5. **`pageUnitPrice`**:
 * - Trường này lưu trữ giá trị đơn vị cho mỗi trang (có thể là giá trị tính phí
 * khi in tài liệu, v.v.). Nó có thể là số nguyên hoặc số thập phân (Double) tùy
 * vào yêu cầu của hệ thống.
 * 
 * ### Các tính năng sử dụng Lombok:
 * 
 * - **`@Getter`, `@Setter`**: Các annotation của Lombok tự động tạo ra các
 * phương thức getter và setter cho tất cả các trường trong lớp `Config`.
 * 
 * - **`@NoArgsConstructor`**: Annotation này tạo ra một constructor không tham
 * số, rất hữu ích khi Spring Data MongoDB tạo đối tượng `Config` từ dữ liệu
 * trong MongoDB.
 * 
 * ### Lớp `FileType`
 * 
 * Trong trường `fileTypeList`, bạn tham chiếu đến lớp `FileType`. Đây là một
 * lớp khác trong dự án của bạn, có thể được sử dụng để mô tả các loại tệp khác
 * nhau mà hệ thống của bạn hỗ trợ (ví dụ: `PDF`, `TXT`, `DOCX`, ...). Dưới đây
 * là một ví dụ giả định về cách lớp `FileType` có thể được định nghĩa:
 * 
 * ```java
 * package com.se.ssps.server.entity.configuration;
 * 
 * import lombok.*;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * public class FileType {
 * private String extension; // Ví dụ: "pdf", "jpg"
 * private String description; // Mô tả về loại tệp
 * }
 * ```
 * 
 * ### Repository
 * 
 * Để thực hiện các thao tác CRUD trên MongoDB đối với lớp `Config`, bạn có thể
 * tạo một `Repository` cho lớp này. Ví dụ:
 * 
 * ```java
 * package com.se.ssps.server.repository;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.Config;
 * 
 * public interface ConfigRepository extends MongoRepository<Config, String> {
 * // Các phương thức truy vấn nếu cần, ví dụ:
 * Config findById(String id);
 * }
 * ```
 * 
 * ### Service
 * 
 * Một lớp `Service` sẽ sử dụng `ConfigRepository` để thao tác với MongoDB. Ví
 * dụ:
 * 
 * ```java
 * package com.se.ssps.server.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 * import com.se.ssps.server.entity.Config;
 * import com.se.ssps.server.repository.ConfigRepository;
 * 
 * @Service
 * public class ConfigService {
 * 
 * @Autowired
 * private ConfigRepository configRepository;
 * 
 * public Config getConfigById(String id) {
 * return configRepository.findById(id).orElse(null); // Trả về null nếu không
 * tìm thấy
 * }
 * 
 * public Config saveConfig(Config config) {
 * return configRepository.save(config);
 * }
 * }
 * ```
 * 
 * ### Tóm tắt
 * 
 * Lớp `Config` đóng vai trò quan trọng trong việc cấu hình hệ thống, bao gồm
 * việc xác định các loại tệp có sẵn, kích thước tối đa của tệp, và giá trị đơn
 * vị trang. Với MongoDB, bạn có thể lưu trữ đối tượng này trong collection
 * `config` và dễ dàng thao tác với dữ liệu thông qua các phương thức CRUD trong
 * Spring Data MongoDB.
 */