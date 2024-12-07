package com.se.ssps.server.entity.configuration;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "campus") // Tài liệu trong MongoDB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Campus {

    @Id
    private String id; // Sử dụng ObjectId dạng chuỗi

    private String campusName;

    private boolean isDel;

    @JsonManagedReference // 25/11/2024

    @DBRef // Tham chiếu tới danh sách Building
    private List<Building> buildings;

    public boolean equals(Campus newCampus) {
        if (this.id.equals(newCampus.getId()))
            return true;
        return false;
    }
}

/*
 * ### Ý nghĩa của lớp `Campus`
 * 
 * #### 1. **Vai trò trong hệ thống**:
 * - Lớp `Campus` đại diện cho một cơ sở (khuôn viên) của trường đại học, ví dụ:
 * Cơ sở Lý Thường Kiệt hoặc Cơ sở Dĩ An.
 * - Thực thể này giúp quản lý các thông tin liên quan đến các tòa nhà và tổ
 * chức hệ thống theo cơ sở.
 * 
 * #### 2. **Giải thích các thành phần trong lớp**:
 * 
 * - **Annotation `@Document(collection = "campus")`:**
 * - Đánh dấu lớp này là một tài liệu (`Document`) trong MongoDB.
 * - Các đối tượng `Campus` sẽ được lưu trong collection `campus`.
 * 
 * - **Thuộc tính:**
 * - `@Id` (`String id`):
 * - Là khóa chính của tài liệu, do MongoDB tự động sinh ra nếu không chỉ định.
 * - `String campusName`:
 * - Tên của cơ sở, ví dụ: "Lý Thường Kiệt" hoặc "Dĩ An".
 * - `boolean isDel`:
 * - Cờ dùng để đánh dấu xóa mềm (soft delete). Nếu `isDel = true`, cơ sở này
 * không còn được sử dụng trong hệ thống.
 * - `@DBRef List<Building> buildings`:
 * - Danh sách các tòa nhà thuộc về cơ sở này.
 * - `@DBRef` biểu thị rằng đây là tham chiếu tới các tài liệu `Building` trong
 * MongoDB.
 * 
 * - **Phương thức `equals(Campus newCampus)`**:
 * - Phương thức kiểm tra xem hai đối tượng `Campus` có cùng `id` hay không.
 * - Điều này hữu ích để so sánh hai cơ sở khi xử lý dữ liệu trong hệ thống.
 * 
 * #### 3. **Mối quan hệ với các thực thể khác**:
 * - **Campus - Building**:
 * - Một cơ sở chứa nhiều tòa nhà. Danh sách các tòa nhà này được lưu trong
 * thuộc tính `buildings`.
 * 
 * #### 4. **Ý nghĩa trong hệ thống tổng thể**:
 * - Thực thể `Campus` tổ chức và phân nhóm các tòa nhà thành các cơ sở, giúp
 * việc quản lý trở nên rõ ràng hơn.
 * - Dữ liệu của `Campus` sẽ được sử dụng trong các chức năng như:
 * - Hiển thị danh sách tòa nhà theo từng cơ sở.
 * - Quản lý máy in dựa trên cơ sở và tòa nhà.
 * 
 * ---
 * 
 * Nếu bạn muốn biết thêm chi tiết về cách `Campus` tương tác với các lớp khác
 * hoặc cần giải thích thêm về một phần cụ thể, cứ gửi tiếp nhé!
 */