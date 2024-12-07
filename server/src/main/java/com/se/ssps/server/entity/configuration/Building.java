package com.se.ssps.server.entity.configuration;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "building") // Tài liệu trong MongoDB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Building {

    @Id
    private String id; // MongoDB sử dụng ObjectId dạng chuỗi

    private String buildingName;

    private boolean isDel;

    @JsonManagedReference // Quản lý vòng lặp
    @DBRef // Liên kết tới một danh sách tài liệu khác
    private List<Room> rooms;

    @JsonBackReference
    @DBRef // Liên kết tới tài liệu Campus
    private Campus campus;
}

/*
 * ### Ý nghĩa của lớp `Building`
 * 
 * #### 1. **Vai trò trong hệ thống**:
 * - Lớp `Building` đại diện cho một tòa nhà trong cơ sở (campus) của trường.
 * - Mỗi tòa nhà có thể chứa nhiều phòng (rooms), được quản lý theo các thuộc
 * tính như tên và trạng thái hoạt động.
 * 
 * #### 2. **Giải thích các thành phần trong lớp**:
 * 
 * - **Annotation `@Document(collection = "building")`:**
 * - Đánh dấu lớp này là một tài liệu (`Document`) trong MongoDB.
 * - Các đối tượng của lớp này sẽ được lưu trong collection có tên là
 * `building`.
 * 
 * - **Thuộc tính:**
 * - `@Id` (`String id`):
 * - Là khóa chính của tài liệu. MongoDB sẽ sử dụng `ObjectId` để tự động sinh
 * giá trị nếu không chỉ định.
 * - `String buildingName`:
 * - Tên của tòa nhà.
 * - `boolean isDel`:
 * - Cờ để đánh dấu xem tòa nhà này đã bị xóa mềm (soft delete) hay chưa.
 * - Giá trị `true` nghĩa là tòa nhà không còn được sử dụng trong hệ thống,
 * nhưng vẫn tồn tại trong cơ sở dữ liệu để giữ lịch sử.
 * - `@DBRef List<Room> rooms`:
 * - Liên kết đến danh sách các phòng (`Room`) trong tòa nhà này.
 * - `@DBRef` cho biết rằng đây là tham chiếu đến một hoặc nhiều tài liệu khác
 * trong MongoDB.
 * - `@DBRef Campus campus`:
 * - Liên kết đến một đối tượng `Campus`, đại diện cho cơ sở mà tòa nhà này
 * thuộc về.
 * 
 * #### 3. **Mối quan hệ với các thực thể khác**:
 * - **Building - Room**:
 * - Một tòa nhà có thể chứa nhiều phòng, được đại diện bởi danh sách `rooms`.
 * - **Building - Campus**:
 * - Một tòa nhà thuộc về một campus, được đại diện bởi tham chiếu `campus`.
 * 
 * #### 4. **Ý nghĩa trong hệ thống tổng thể**:
 * - Thực thể `Building` giúp tổ chức không gian vật lý của các cơ sở trong hệ
 * thống.
 * - Thông tin về tòa nhà sẽ được sử dụng trong các chức năng như:
 * - Quản lý vị trí của máy in.
 * - Hiển thị thông tin chi tiết khi chọn máy in để in tài liệu.
 *
 */