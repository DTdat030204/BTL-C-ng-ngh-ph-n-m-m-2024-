package com.se.ssps.server.entity;

public enum PageSize {
    A3,
    A4,
    A2
}


/*
 * Lớp `PageSize` bạn vừa định nghĩa là một **enum** trong Java, đại diện cho
 * các kích thước giấy khác nhau. Đây là cách bạn có thể khai báo các giá trị cố
 * định mà một đối tượng có thể nhận trong một loại dữ liệu nhất định.
 * 
 * ### Phân tích enum `PageSize`:
 * 
 * ```java
 * package com.se.ssps.server.entity;
 * 
 * public enum PageSize {
 * A1, A2, A3, A4, A5
 * }
 * ```
 * 
 * ### Giải thích về enum:
 * 
 * 1. **`enum`** (viết tắt của **enumeration**) là một kiểu dữ liệu đặc biệt
 * trong Java cho phép bạn định nghĩa một tập hợp các giá trị cố định (thường là
 * các hằng số).
 * 
 * 2. **Các giá trị trong enum**:
 * - Các giá trị `A1`, `A2`, `A3`, `A4`, và `A5` là các hằng số đại diện cho các
 * kích thước giấy phổ biến trong hệ thống ISO (ví dụ: A4 là kích thước giấy phổ
 * biến trong văn phòng).
 * 
 * 3. **Mục đích sử dụng**:
 * - `PageSize` có thể được sử dụng trong hệ thống để xác định kích thước giấy
 * của tài liệu cần in, ví dụ: khi người dùng yêu cầu in một tài liệu với kích
 * thước giấy cụ thể.
 * 
 * ### Ưu điểm khi sử dụng enum:
 * 
 * - **Tính an toàn**: Enum giúp đảm bảo rằng chỉ các giá trị đã được định nghĩa
 * trong enum mới có thể được sử dụng. Điều này giúp tránh các lỗi khi so sánh
 * hoặc xử lý dữ liệu.
 * - **Tính dễ dàng sử dụng**: Bạn có thể dễ dàng sử dụng các giá trị trong enum
 * mà không cần phải nhớ chuỗi ký tự hoặc giá trị số.
 * 
 * ### Cách sử dụng enum `PageSize` trong mã:
 * 
 * #### 1. **Khởi tạo và sử dụng enum**:
 * ```java
 * PageSize pageSize = PageSize.A4;
 * System.out.println("Selected page size: " + pageSize);
 * ```
 * 
 * #### 2. **Sử dụng enum trong switch-case**:
 * Bạn có thể sử dụng enum trong các biểu thức `switch` để thực hiện các hành
 * động khác nhau tùy theo giá trị của enum:
 * ```java
 * switch (pageSize) {
 * case A1:
 * System.out.println("Page size is A1");
 * break;
 * case A4:
 * System.out.println("Page size is A4");
 * break;
 * default:
 * System.out.println("Unknown page size");
 * }
 * ```
 * 
 * #### 3. **So sánh enum**:
 * Các giá trị trong enum có thể được so sánh dễ dàng với `==` vì chúng là các
 * đối tượng duy nhất.
 * ```java
 * if (pageSize == PageSize.A4) {
 * System.out.println("It's A4 size.");
 * }
 * ```
 * 
 * ### Tích hợp enum `PageSize` vào hệ thống
 * 
 * Enum này có thể được tích hợp vào các lớp khác, chẳng hạn như:
 * 
 * - **Lớp `Config`**: Bạn có thể thêm trường `PageSize` vào lớp `Config` để xác
 * định kích thước giấy được sử dụng trong các cài đặt hệ thống.
 * 
 * - **Lớp `PrintingLog` hoặc `Student`**: Bạn có thể lưu trữ thông tin về kích
 * thước giấy đã chọn trong các đối tượng log in hoặc thông tin sinh viên.
 * 
 * Ví dụ, trong lớp `PrintingLog`, bạn có thể lưu trữ thông tin về kích thước
 * giấy được sử dụng khi in:
 * 
 * ```java
 * public class PrintingLog {
 * private PageSize pageSize;
 * 
 * public PrintingLog(PageSize pageSize) {
 * this.pageSize = pageSize;
 * }
 * 
 * public PageSize getPageSize() {
 * return pageSize;
 * }
 * 
 * public void setPageSize(PageSize pageSize) {
 * this.pageSize = pageSize;
 * }
 * }
 * ```
 * 
 * ### Tóm tắt
 * 
 * Enum `PageSize` giúp bạn định nghĩa và sử dụng các kích thước giấy phổ biến
 * trong hệ thống một cách rõ ràng và an toàn. Việc sử dụng enum trong Java giúp
 * tổ chức mã tốt hơn và tránh được các lỗi khi làm việc với các giá trị cố định
 * như kích thước giấy.
 */