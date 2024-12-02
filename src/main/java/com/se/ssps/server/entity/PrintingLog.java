package com.se.ssps.server.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se.ssps.server.entity.user.Student;

import lombok.*;

@Document(collection = "printing_logs") // Ánh xạ đến collection "printing_logs"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrintingLog {
    @Id
    private String id; // MongoDB sử dụng String cho khóa chính

    private String fileName;

    private Double size;

    private Integer numOfPages;

    private int numOfCopies;

    private boolean isHori;

    private boolean isDoubleSided;

    private LocalDateTime startDate; // có cần ko

    private LocalDateTime endDate;

    private Double squarePrinting;

    @DBRef
    @JsonIgnore // Bỏ qua khi tuần tự hóa JSON
    private Printer printer; // Tham chiếu đến tài liệu Printer

    @DBRef
    @JsonIgnore
    private Student student; // Tham chiếu đến tài liệu Student

    // public void calculateSquare() {
    //     final double a4Square = 0.06237;
    //     if (this.pageSize == PageSize.A4)
    //         this.squarePrinting = a4Square * this.numOfPages * this.numOfCopies;
    //     else if (this.pageSize == PageSize.A3)
    //         this.squarePrinting = a4Square * 2 * this.numOfPages * this.numOfCopies;
    //     else if (this.pageSize == PageSize.A2)
    //         this.squarePrinting = a4Square * 4 * this.numOfPages * this.numOfCopies;
    //     else if (this.pageSize == PageSize.A1)
    //         this.squarePrinting = a4Square * 8 * this.numOfPages * this.numOfCopies;
    // }
    @Enumerated(EnumType.STRING)
    private PageSize pageSize;

    // Getter và Setter
    public PageSize getPageSize() {
        return pageSize;
    }

    public void setPageSize(PageSize pageSize) {
        this.pageSize = pageSize;
    }
}



/*
 * Lớp `PrintingLog` của bạn mô hình hóa thông tin về các log in trong hệ thống,
 * bao gồm chi tiết về tệp in, số lượng bản in, và các tham số khác liên quan
 * đến việc in ấn. Dưới đây là phân tích chi tiết về lớp này:
 * 
 * ### Phân tích lớp `PrintingLog`
 * 
 * ```java
 * package com.se.ssps.server.entity;
 * 
 * import java.time.LocalDateTime;
 * 
 * import org.springframework.data.annotation.Id;
 * import org.springframework.data.mongodb.core.mapping.Document;
 * import org.springframework.data.mongodb.core.mapping.DBRef;
 * import com.se.ssps.server.entity.user.Student;
 * 
 * import lombok.*;
 * 
 * @Document(collection = "printing_logs") // Ánh xạ đến collection
 * "printing_logs"
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor
 * public class PrintingLog {
 * 
 * @Id
 * private String id; // MongoDB sử dụng String cho khóa chính
 * 
 * private String fileName;
 * 
 * private double size;
 * 
 * private Integer numOfPages;
 * 
 * private int numOfCopies;
 * 
 * private boolean isHori;
 * 
 * private boolean isDoubleSided;
 * 
 * private PageSize pageSize;
 * 
 * private LocalDateTime startDate;
 * 
 * private LocalDateTime endDate;
 * 
 * private Double squarePrinting;
 * 
 * @DBRef
 * private Printer printer; // Tham chiếu đến tài liệu Printer
 * 
 * @DBRef
 * private Student student; // Tham chiếu đến tài liệu Student
 * 
 * public void calculateSquare() {
 * final double a4Square = 0.06237;
 * if (this.pageSize == PageSize.A4)
 * this.squarePrinting = a4Square * this.numOfPages * this.numOfCopies;
 * else if (this.pageSize == PageSize.A3)
 * this.squarePrinting = a4Square * 2 * this.numOfPages * this.numOfCopies;
 * else if (this.pageSize == PageSize.A2)
 * this.squarePrinting = a4Square * 4 * this.numOfPages * this.numOfCopies;
 * else if (this.pageSize == PageSize.A1)
 * this.squarePrinting = a4Square * 8 * this.numOfPages * this.numOfCopies;
 * else if (this.pageSize == PageSize.A5)
 * this.squarePrinting = a4Square / 2 * this.numOfPages * this.numOfCopies;
 * }
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Annotation `@Document(collection = "printing_logs")`**:
 * - Đánh dấu lớp `PrintingLog` là một tài liệu (document) trong MongoDB. Dữ
 * liệu của các đối tượng `PrintingLog` sẽ được lưu trữ trong collection có tên
 * là `printing_logs`.
 * 
 * 2. **Trường thông tin về log in**:
 * - `fileName`: Tên của tệp tài liệu đã được in, kiểu `String`.
 * - `size`: Kích thước của tệp tài liệu (dung lượng file), kiểu `double`.
 * - `numOfPages`: Số trang của tài liệu, kiểu `Integer`.
 * - `numOfCopies`: Số bản in được thực hiện, kiểu `int`.
 * - `isHori`: Cờ boolean cho biết tài liệu có được in theo chiều ngang hay
 * không.
 * - `isDoubleSided`: Cờ boolean cho biết tài liệu có được in hai mặt hay không.
 * - `pageSize`: Kích thước trang của tài liệu, kiểu `PageSize` (một enum với
 * các giá trị như A4, A3, A2, A1, A5).
 * - `startDate`: Thời gian bắt đầu in, kiểu `LocalDateTime`.
 * - `endDate`: Thời gian kết thúc in, kiểu `LocalDateTime`.
 * - `squarePrinting`: Diện tích in tính bằng đơn vị mét vuông, kiểu `Double`.
 * 
 * 3. **`@DBRef`**:
 * - **`printer`**: Dùng `@DBRef` để tham chiếu đến tài liệu `Printer`. Điều này
 * giúp lưu trữ ID của máy in thay vì toàn bộ thông tin về máy in trong tài liệu
 * `PrintingLog`.
 * - **`student`**: Dùng `@DBRef` để tham chiếu đến tài liệu `Student`. Tương tự
 * như với `Printer`, chỉ lưu trữ ID của sinh viên thực hiện in mà không lưu
 * toàn bộ thông tin của sinh viên.
 * 
 * 4. **Phương thức `calculateSquare`**:
 * - Phương thức này tính toán diện tích in (`squarePrinting`) dựa trên kích
 * thước trang và số lượng bản in. Diện tích được tính bằng cách sử dụng một giá
 * trị mặc định cho diện tích của một trang A4 (`a4Square = 0.06237` m²). Sau
 * đó, diện tích được điều chỉnh tùy theo kích thước trang:
 * - **A4**: Diện tích cho một trang A4.
 * - **A3**: Diện tích cho một trang A3 (gấp đôi diện tích của A4).
 * - **A2**: Diện tích cho một trang A2 (gấp bốn lần diện tích của A4).
 * - **A1**: Diện tích cho một trang A1 (gấp tám lần diện tích của A4).
 * - **A5**: Diện tích cho một trang A5 (một nửa diện tích của A4).
 * 
 * ### Các điểm cần lưu ý
 * 
 * 1. **Quan hệ giữa `PrintingLog` và các đối tượng khác**:
 * - Sử dụng `@DBRef` giúp giảm thiểu việc lưu trữ thông tin thừa trong MongoDB.
 * Bạn chỉ cần lưu ID của các đối tượng `Printer` và `Student`, và MongoDB sẽ tự
 * động giải quyết việc liên kết các tài liệu với nhau.
 * 
 * 2. **Tính toán diện tích in**:
 * - Phương thức `calculateSquare` giúp tính toán diện tích của tài liệu in dựa
 * trên số trang, số bản in, và kích thước của trang. Đây là một phương thức
 * quan trọng giúp tính toán chi phí hoặc các thông số khác liên quan đến việc
 * in ấn trong hệ thống của bạn.
 * 
 * 3. **Sử dụng `LocalDateTime` cho thời gian**:
 * - `LocalDateTime` được sử dụng để lưu trữ thời gian bắt đầu và kết thúc của
 * việc in. MongoDB sẽ lưu trữ kiểu này dưới dạng `ISODate`, cho phép dễ dàng
 * thao tác với thời gian trong cơ sở dữ liệu.
 * 
 * 4. **Các enum `PageSize`**:
 * - Lớp `PageSize` được sử dụng để định nghĩa các kích thước trang có thể có
 * trong hệ thống. Sử dụng enum giúp mã nguồn trở nên rõ ràng hơn và dễ bảo trì
 * khi có sự thay đổi về các kích thước trang.
 * 
 * ### Ví dụ về sử dụng
 * 
 * Giả sử bạn muốn tạo một đối tượng `PrintingLog` và lưu nó vào MongoDB:
 * 
 * ```java
 * Printer printer = // lấy đối tượng máy in từ cơ sở dữ liệu
 * Student student = // lấy đối tượng sinh viên từ cơ sở dữ liệu
 * 
 * PrintingLog printingLog = new PrintingLog();
 * printingLog.setFileName("document.pdf");
 * printingLog.setSize(1.5);
 * printingLog.setNumOfPages(10);
 * printingLog.setNumOfCopies(2);
 * printingLog.setIsHori(true);
 * printingLog.setIsDoubleSided(true);
 * printingLog.setPageSize(PageSize.A4);
 * printingLog.setStartDate(LocalDateTime.now());
 * printingLog.setEndDate(LocalDateTime.now().plusMinutes(15));
 * printingLog.setPrinter(printer);
 * printingLog.setStudent(student);
 * 
 * // Tính toán diện tích in
 * printingLog.calculateSquare();
 * 
 * // Lưu log in vào MongoDB
 * printingLogRepository.save(printingLog);
 * ```
 * 
 * ### Kết luận
 * 
 * Lớp `PrintingLog` của bạn đã mô hình hóa các log in rất chi tiết, bao gồm các
 * thông tin về tài liệu, số lượng bản in, và các tham chiếu đến máy in và sinh
 * viên. Phương thức `calculateSquare` giúp tính toán diện tích in dựa trên các
 * tham số đã được cung cấp. Các mối quan hệ giữa các đối tượng được quản lý
 * hiệu quả nhờ sử dụng `@DBRef`, giúp giảm thiểu sự trùng lặp dữ liệu trong
 * MongoDB.
 */