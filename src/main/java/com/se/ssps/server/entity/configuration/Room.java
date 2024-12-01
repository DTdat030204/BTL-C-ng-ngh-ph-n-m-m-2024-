package com.se.ssps.server.entity.configuration;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.ssps.server.entity.Printer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.*;

@Document(collection = "room") // Định nghĩa tài liệu MongoDB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String

    private String roomName;

    @DBRef
    @JsonIgnore
    private Printer printer; // Tham chiếu đến tài liệu khác trong MongoDB

    @JsonBackReference // Bỏ qua vòng lặp
    @DBRef
    private Building building; // Tham chiếu đến tài liệu Building

    
    //@JsonBackReference // Bỏ qua vòng lặp
    @DBRef
    private Campus campus; // Thêm tham chiếu tới Campus

    private boolean isDel;

    private boolean havePrinter;

    public boolean equals(Room room) {
        if (this.id.equals(room.getId()))
            return true;
        return false;
    }
}



/*
 * ### Đánh giá và giải thích lớp `Room`
 * 
 * #### 1. **Vai trò trong hệ thống**
 * - Lớp **`Room`** đại diện cho một phòng trong hệ thống. Mỗi phòng có thể có
 * các thuộc tính như tên phòng, thông tin về máy in, tòa nhà chứa phòng, và
 * trạng thái của phòng (có thể bị xóa hay không).
 * - Thông qua lớp này, hệ thống có thể quản lý các phòng và các thông tin liên
 * quan như máy in và tòa nhà.
 * 
 * ---
 * 
 * #### 2. **Các thành phần trong lớp**
 * 
 * ```java
 * 
 * @Document(collection = "room") // Định nghĩa tài liệu MongoDB
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * public class Room {
 * 
 * @Id
 * private String id; // MongoDB sử dụng ObjectId, ánh xạ thành String
 * 
 * private String roomName;
 * 
 * @DBRef
 * 
 * @JsonIgnore
 * private Printer printer; // Tham chiếu đến tài liệu khác trong MongoDB
 * 
 * @DBRef
 * private Building building; // Tham chiếu đến tài liệu Building
 * 
 * private boolean isDel;
 * 
 * private boolean havePrinter;
 * 
 * public boolean equals(Room room) {
 * if (this.id.equals(room.getId()))
 * return true;
 * return false;
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 3. **Chi tiết từng thành phần**
 * 
 * 1. **`@Document(collection = "room")`**:
 * - Đánh dấu lớp này là một tài liệu MongoDB trong collection `room`.
 * - Dữ liệu của lớp `Room` sẽ được lưu trong MongoDB trong một collection có
 * tên `room`.
 * 
 * 2. **`id`**:
 * - Là khóa chính của tài liệu MongoDB, sử dụng `ObjectId` nhưng ánh xạ thành
 * kiểu `String` trong Java.
 * 
 * 3. **`roomName`**:
 * - Tên phòng, giúp dễ dàng nhận diện và phân loại các phòng trong hệ thống.
 * 
 * 4. **`printer`**:
 * - Thuộc tính tham chiếu đến một tài liệu `Printer` trong MongoDB.
 * - **`@DBRef`** cho biết rằng trường này liên kết đến tài liệu khác (tài liệu
 * `Printer`).
 * - **`@JsonIgnore`**: Đảm bảo rằng trường `printer` sẽ không được serialize
 * khi chuyển đối tượng thành JSON (giúp tránh vòng lặp vô hạn hoặc các dữ liệu
 * không cần thiết khi trả về API).
 * 
 * 5. **`building`**:
 * - Liên kết với một đối tượng `Building` qua `@DBRef`. Mỗi phòng thuộc một tòa
 * nhà cụ thể.
 * 
 * 6. **`isDel`**:
 * - Đại diện cho trạng thái xóa của phòng. Nếu `true`, phòng này được đánh dấu
 * là đã bị xóa.
 * 
 * 7. **`havePrinter`**:
 * - Chỉ ra liệu phòng này có máy in hay không, thuộc tính này giúp dễ dàng kiểm
 * tra trạng thái có máy in trong phòng.
 * 
 * 8. **Phương thức `equals(Room room)`**:
 * - Kiểm tra sự tương đương giữa hai phòng dựa trên `id`. Nếu `id` của hai
 * phòng giống nhau, trả về `true`, ngược lại `false`.
 * 
 * ---
 * 
 * #### 4. **Mối quan hệ với các thực thể khác**
 * - **Liên kết với `Printer`**:
 * - Mỗi phòng có thể có một máy in. Thông qua `printer`, bạn có thể biết được
 * phòng đó có máy in hay không.
 * - `@JsonIgnore` giúp tránh việc thông tin về máy in bị đưa vào trong phản hồi
 * JSON khi API trả về kết quả.
 * 
 * - **Liên kết với `Building`**:
 * - Mỗi phòng thuộc một tòa nhà (`Building`). Mối quan hệ này được thể hiện qua
 * thuộc tính `building`, cho phép truy xuất thông tin của tòa nhà mà phòng đó
 * thuộc về.
 * 
 * ---
 * 
 * #### 5. **Repository**
 * 
 * ```java
 * package com.se.ssps.server.repository.configuration;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * import com.se.ssps.server.entity.configuration.Room;
 * 
 * public interface RoomRepository extends MongoRepository<Room, String> {
 * Room findByRoomName(String roomName); // Tìm phòng theo tên
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
 * import com.se.ssps.server.entity.configuration.Room;
 * import com.se.ssps.server.repository.configuration.RoomRepository;
 * 
 * @Service
 * public class RoomService {
 * 
 * @Autowired
 * private RoomRepository roomRepository;
 * 
 * // Lấy thông tin phòng theo tên
 * public Room getRoomByName(String roomName) {
 * return roomRepository.findByRoomName(roomName);
 * }
 * 
 * // Lưu hoặc cập nhật phòng
 * public Room saveOrUpdateRoom(Room room) {
 * return roomRepository.save(room);
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
 * import com.se.ssps.server.entity.configuration.Room;
 * import com.se.ssps.server.service.configuration.RoomService;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/configuration/room")
 * public class RoomController {
 * 
 * @Autowired
 * private RoomService roomService;
 * 
 * // Lấy phòng theo tên
 * 
 * @GetMapping("/{roomName}")
 * public ResponseEntity<Room> getRoomByName(@PathVariable String roomName) {
 * return ResponseEntity.ok(roomService.getRoomByName(roomName));
 * }
 * 
 * // Lưu hoặc cập nhật phòng
 * 
 * @PostMapping
 * public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
 * return ResponseEntity.ok(roomService.saveOrUpdateRoom(room));
 * }
 * }
 * ```
 * 
 * ---
 * 
 * #### 8. **Tích hợp với các hệ thống khác**
 * 
 * - **Liên kết với quản lý máy in**:
 * - Bạn có thể dùng lớp này để kiểm tra liệu phòng có máy in hay không và quản
 * lý việc phân bổ máy in cho từng phòng.
 * 
 * - **Quản lý tòa nhà và phòng**:
 * - Sử dụng lớp này để quản lý các phòng trong mỗi tòa nhà, ví dụ như phân bổ
 * phòng theo yêu cầu hoặc tình trạng phòng có máy in.
 * 
 * ---
 * 
 * #### 9. **Tóm tắt**
 * Lớp **`Room`** là một thực thể quan trọng trong hệ thống, giúp quản lý các
 * phòng và liên kết với các thực thể khác như tòa nhà (`Building`) và máy in
 * (`Printer`). Nó hỗ trợ các tính năng như xác định phòng có máy in hay không,
 * trạng thái phòng, và khả năng liên kết với các phòng khác thông qua các tham
 * chiếu MongoDB.
 * 
 * Nếu bạn cần làm thêm với các tính năng khác hoặc muốn mở rộng logic trong hệ
 * thống, hãy cho tôi biết! 😊
 */