<a id="readme-top"></a>





<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/DTdat030204/Student_smart_printing_service">
    <img src="hcmut.png" alt="Logo" width="160" height="160">
  </a>

<h3 align="center">Student Smart Printing Service</h3>

  <p align="center">
    Ứng dụng cung cấp dịch vụ in ấn tiện lợi cho sinh viên
    <br />
    <a href="#getting-started"><strong>Xem hướng dẫn »</strong></a>
    <br />
  </p>
</div>







<!-- ABOUT THE PROJECT -->
<a id="about-the-project"></a>

## Về dự án này

Ứng dụng này được hiện thực để phục vụ cho môn học Công nghệ phần mềm, thuộc về Trường Đại học Bách khoa, ĐHQG TP.HCM. Dự án hướng đến mục tiêu xây dựng nên một trang web cung cấp dịch vụ in ấn tiện lợi cho sinh viên của trường.

Thành viên phát triển dự án:

- Trương Minh Thông - Nguyễn Văn SơnSơn - phát triển frontend
- Đỗ Tuấn Đạt - Lê Quang NguyênNguyên - phát triển backend

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Công nghệ sử dụng
<a id="built-with"></a>

- [React](https://react.dev/)
- [React Bootstrap](https://react-bootstrap.github.io/)
- [React Router](https://reactrouter.com/en/main)
- [Font Awesome](https://fontawesome.com/)
- [Spring](https://spring.io/projects/spring-framework)
- [Spring Boot](https://spring.io/projects/spring-boot/)
- [Spring JPA](https://spring.io/projects/spring-data-jpa)
- [MongoDB](https://www.mongodb.com/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>





<!-- GETTING STARTED -->
<a id="getting-started"></a>

## Bắt đầu

<a id="prerequisites"></a>
### Điều kiện

Trước khi sử dụng ứng dụng, bạn cần cài đặt trước một số phần mềm sau:

- [Node.js](https://nodejs.org/en) (phiên bản LTS)
- [Java](https://www.oracle.com/java/technologies/downloads/) (phiên bản JDK 21)
- [Apache Maven](https://maven.apache.org/download.cgi) (Link - binary zip archive)

<a id="installation"></a>
### Cài đặt

Vì ứng dụng chưa được triển khai tên miền, bạn có thể sử dụng nó bằng localhost với các bước sau:

1. Truy cập đường dẫn [https://github.com/DTdat030204/Student_smart_printing_service].

2. Tải về source code và giải nén, lưu vào nơi bạn muốn

3. Mở một chương trình shell (Command Prompt, Powershell, Bash...) 

4. Thay đổi đường dẫn tới thư mục của ứng dụng (hcmut-ssps) bằng lệnh `cd <dir>`

5. Tại thư mục gốc, chạy các lệnh sau để khởi động chương trình phía client (giao diện ứng dụng sẽ tự động hiển thị sau bước này)

```bash
cd frontend_SSPS
npm install
npm start
```

6. Tại vị trí thư mục gốc của ứng dụng (`hcmut-ssps`), mở file cấu hình ứng dụng tại đường dẫn `server/src/main/resources/application.properties`, thay đổi các dòng sau với username và password của tài khoản MySQL mà bạn muốn sử dụng:

```properties
spring.datasource.username=...
spring.datasource.password=...
```

7. Tại thư mục gốc, chạy các lệnh sau để khởi động chương trình phía server

```bash
cd server
mvn clean install
java -jar target/server-0.0.1-SNAPSHOT.jar
```


<!-- USAGE -->
<a id="usage"></a>

## Hướng dẫn sử dụng

<p align="right">(<a href="#readme-top">back to top</a>)</p>

__Lưu ý__: vì ứng dụng vẫn đang trong giai đoạn phát triển nên một số chức năng sẽ không được đầy đủ như mong muốn

### Tài khoản để sử dụng ứng dụng

Sinh viên có thể đăng kí tài khoản và đăng nhập.

### Nhóm chức năng của sinh viên

#### In tài liệu

Ở giao diện __In tài liệu__, bạn có thể gửi một yêu cầu in tới server với các bước sau:

1. Đăng tải file (có thể đăng nhiều file một lúc)

    - Các ràng buộc về định dạng và kích thước được hiển thị trên giao diện (có thể cấu hình phía nhân viên), nếu vi phạm thì file sẽ bị xóa

    - Có thể xóa file khi cần


2. Nhấn __Xác nhận yêu cầu__

__Chú ý__: Tính năng tự động đếm số trang, chọn trang cần in hay bản in xem trước chưa được hiện thực.

#### Mua trang in

Ở giao diện __Mua trang in__, bạn có thể gửi một yêu cầu mua trang tới server với các bước sau:

1. Chọn số trang cần mua

    Hệ thống sẽ tự động tính toán tổng tiền dựa trên đơn giá (có thể được cấu hình phía nhân viên)

2. Tiền cần phải chi trả
  
    Hệ thống sẽ lưu số tiền sinh viên cần thanh toán vào 1 biến và sinh viên sẽ phải thanh toán số tiền đó vào cuối kì.

__Chú ý__: Tính năng liên kết tới hệ thống thanh toán trực tuyến chưa được hiện thực.

#### Lịch sử in

Ở giao diện __Lịch sử in__, bạn có thể xem lại toàn bộ các yêu cầu in đã gửi từ giao diện __In tài liệu__.

### Nhóm chức năng của nhân viên quản lý (SPSO)

__Chú ý__: Đăng nhập với username `test` và password `123` để sử dụng nhóm chức năng này

#### Quản lý máy in

Ở giao diện __Quản lý máy in__, bạn có thể sử dụng các chức năng như:

- Thêm máy in mới

- Xóa máy in

- Chỉnh sửa máy in

- Tìm kiếm máy in

#### Thông số máy in

Ở giao diện __Thông số máy in__, bạn có thể sử dụng các chức năng như:

- Xem các thông số hoạt động của máy in (lượt in, diện tích giấy in, hiệu suất)

- Bật/tắt máy in

#### Cấu hình cấp phát

Ở giao diện __Cấp phát__, bạn có thể sử dụng các chức năng như:

- Thêm lịch cấp phát trang in

- Xóa lịch cấp phát trang in

#### Cấu hình liên quan tới file

Ở giao diện __Cấu hình file__, bạn có thể sử dụng các chức năng như:

- Thêm/xóa định dạng file cho phép

- Chỉnh sửa kích thước file lớn nhất cho phép

- Chỉnh sửa đơn giá trang in


#### Lịch sử in & thanh toán

Ở giao diện __Vị trí__, bạn có thể xem lại toàn bộ các yêu cầu in được thực hiện tại giao diện __In tài liệu__ và yêu cầu thanh toán được thực hiện tại giao diện __Mua trang in__.

