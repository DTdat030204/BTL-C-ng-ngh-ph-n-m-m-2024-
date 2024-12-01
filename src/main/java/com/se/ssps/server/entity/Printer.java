package com.se.ssps.server.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.se.ssps.server.entity.configuration.Building;
import com.se.ssps.server.entity.configuration.Campus;
import com.se.ssps.server.entity.configuration.Room;

import lombok.*;

@Document(collection = "printers") // Ánh xạ sang collection "printers" trong MongoDB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Printer {
    @Id
    private String id; // MongoDB sẽ sử dụng trường này làm khóa chính

    private String printerName;

    private Integer inkAmount;

    private Integer pageAmount;

    private String firm;

    private String description;

    private Integer efficiency;

    private boolean isDel;

    private Boolean status;

    @DBRef
    private Room room; // Tham chiếu đến tài liệu `Room`

    @DBRef(lazy = false)
    private Building building;

    @DBRef(lazy = false)
    private Campus campus;

   
    @DBRef
    private List<PrintingLog> printingLogs = new ArrayList<>(); // Khởi tạo danh sách mặc định
                                                                // Tham chiếu danh sách các `PrintingLog`
}


/*
 * Lớp `Printer` của bạn mô hình hóa thông tin về máy in trong hệ thống sử dụng
 * MongoDB. Dưới đây là phân tích chi tiết về lớp này và cách thức hoạt động của
 * nó:
 * 
 * ### Phân tích lớp `Printer`
 * 
 * ```java
 * package com.se.ssps.server.entity;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * import org.springframework.data.mongodb.core.mapping.DBRef;
 * import com.se.ssps.server.entity.configuration.Room;
 * 
 * import lombok.*;
 * 
 * @Document(collection = "printers") // Ánh xạ sang collection "printers" trong
 * MongoDB
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter
 * public class Printer {
 * 
 * @Id
 * private String id; // MongoDB sẽ sử dụng trường này làm khóa chính
 * 
 * private String printerName;
 * 
 * private Integer inkAmount;
 * 
 * private Integer pageAmount;
 * 
 * private String firm;
 * 
 * private String description;
 * 
 * private Integer efficiency;
 * 
 * private boolean isDel;
 * 
 * private Boolean status;
 * 
 * @DBRef
 * private Room room; // Tham chiếu đến tài liệu `Room`
 * 
 * @DBRef
 * private List<PrintingLog> printingLogs; // Tham chiếu danh sách các
 * `PrintingLog`
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Annotation `@Document(collection = "printers")`**:
 * - Đánh dấu lớp `Printer` là một tài liệu (document) trong MongoDB. Dữ liệu
 * của các đối tượng `Printer` sẽ được lưu trữ trong collection có tên là
 * `printers`.
 * 
 * 2. **`@Id`**:
 * - Trường `id` được đánh dấu bằng `@Id` để MongoDB sử dụng nó làm khóa chính
 * cho tài liệu. Trường này có kiểu `String`, và MongoDB sẽ tự động tạo ID nếu
 * không được cung cấp.
 * 
 * 3. **Các trường thông tin về máy in**:
 * - `printerName`: Tên của máy in, kiểu `String`.
 * - `inkAmount`: Lượng mực còn lại trong máy in, kiểu `Integer`.
 * - `pageAmount`: Số trang đã in, kiểu `Integer`.
 * - `firm`: Thương hiệu của máy in, kiểu `String`.
 * - `description`: Mô tả về máy in, kiểu `String`.
 * - `efficiency`: Hiệu suất của máy in (ví dụ: số trang in mỗi phút), kiểu
 * `Integer`.
 * - `isDel`: Trường boolean dùng để đánh dấu xem máy in có bị xóa hay không.
 * - `status`: Trường boolean dùng để lưu trạng thái hoạt động của máy in (ví
 * dụ: hoạt động hay không hoạt động).
 * 
 * 4. **`@DBRef`**:
 * - **`room`**: Dùng `@DBRef` để tham chiếu đến tài liệu `Room`. Điều này giúp
 * bạn chỉ lưu trữ ID của `Room` trong tài liệu `Printer`, thay vì lưu toàn bộ
 * thông tin của `Room`. MongoDB sẽ liên kết `Printer` với `Room` thông qua
 * trường này.
 * - **`printingLogs`**: Danh sách các log in được thực hiện bởi máy in này. Mỗi
 * `PrintingLog` sẽ được tham chiếu thông qua `@DBRef`. MongoDB chỉ lưu trữ các
 * ID của các `PrintingLog` thay vì lưu toàn bộ thông tin, giúp tiết kiệm không
 * gian lưu trữ.
 * 
 * 5. **Các phương thức getter và setter**:
 * - Các phương thức getter và setter được tự động tạo ra nhờ vào annotation
 * `@Getter` và `@Setter` của Lombok. Điều này giúp bạn dễ dàng truy cập và thay
 * đổi giá trị của các trường trong lớp `Printer`.
 * 
 * 6. **`@AllArgsConstructor` và `@NoArgsConstructor`**:
 * - `@AllArgsConstructor`: Tạo một constructor có tất cả các tham số.
 * - `@NoArgsConstructor`: Tạo một constructor không tham số (constructor mặc
 * định).
 * 
 * ### Các điểm cần lưu ý
 * 
 * 1. **Quan hệ giữa `Printer` và `Room`**:
 * - Trường `room` sử dụng `@DBRef` để tham chiếu đến một tài liệu `Room`. Điều
 * này giúp mô hình hóa quan hệ giữa máy in và phòng mà nó thuộc về. Thay vì lưu
 * trữ toàn bộ thông tin của `Room`, MongoDB chỉ lưu trữ ID của `Room`, tiết
 * kiệm bộ nhớ và dễ dàng truy xuất.
 * 
 * 2. **Quan hệ giữa `Printer` và `PrintingLog`**:
 * - Trường `printingLogs` sử dụng `@DBRef` để tham chiếu đến danh sách các log
 * in liên quan đến máy in này. Điều này giúp tránh việc lưu trữ trực tiếp dữ
 * liệu của tất cả các log in trong máy in và giữ cho tài liệu `Printer` nhẹ
 * nhàng hơn.
 * 
 * 3. **Lý do sử dụng `@DBRef`**:
 * - Khi sử dụng `@DBRef`, MongoDB sẽ tự động xử lý việc tham chiếu giữa các tài
 * liệu. Điều này giúp duy trì tính nhất quán trong cơ sở dữ liệu và giảm thiểu
 * sự trùng lặp dữ liệu. Tuy nhiên, bạn cần phải biết rằng việc sử dụng `@DBRef`
 * có thể tạo thêm chi phí trong việc truy vấn và tải dữ liệu liên quan, vì
 * MongoDB cần phải tìm các tài liệu liên kết.
 * 
 * ### Ví dụ về sử dụng
 * 
 * Giả sử bạn muốn tạo một đối tượng `Printer` và lưu nó vào MongoDB:
 * 
 * ```java
 * Room room = // lấy đối tượng phòng từ cơ sở dữ liệu
 * List<PrintingLog> logs = // lấy danh sách các log in từ cơ sở dữ liệu
 * 
 * Printer printer = new Printer();
 * printer.setPrinterName("Printer 1");
 * printer.setInkAmount(100);
 * printer.setPageAmount(200);
 * printer.setFirm("HP");
 * printer.setDescription("Máy in màu HP");
 * printer.setEfficiency(20);
 * printer.setIsDel(false);
 * printer.setStatus(true);
 * printer.setRoom(room);
 * printer.setPrintingLogs(logs);
 * 
 * // Lưu máy in vào MongoDB
 * printerRepository.save(printer);
 * ```
 * 
 * ### Kết luận
 * 
 * Lớp `Printer` của bạn đã mô hình hóa thông tin về máy in, bao gồm các thông
 * tin cơ bản như tên máy in, hiệu suất, trạng thái và các tham chiếu tới phòng
 * và các log in liên quan. Sử dụng `@DBRef` giúp giữ cho dữ liệu được tổ chức
 * hợp lý và tiết kiệm bộ nhớ, đồng thời duy trì khả năng truy xuất dữ liệu liên
 * quan khi cần thiết.
 */