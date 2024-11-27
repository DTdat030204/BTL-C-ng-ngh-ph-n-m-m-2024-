package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;

import com.se.ssps.server.entity.user.Student;


import org.springframework.stereotype.Repository;

//import com.se.ssps.server.entity.user.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Student findByUsername(String username);
}
// public interface StudentRepository extends MongoRepository<Student, Integer> {
//     // Truy vấn sinh viên theo ID, MongoDB sẽ tự động tìm kiếm theo trường "_id"
//     public Student findStudentById(Integer id);

//     // Cập nhật thông tin số trang, MongoDB không hỗ trợ @Modifying, nên sẽ cần thay

//     // đổi phương thức này
//     @Query("{ '_id' : ?0 }")
//     public void updateNumOfPages(Integer id, Integer numOfPages);
// }

/*
 * Đoạn mã này định nghĩa một **repository** trong Spring Data MongoDB có tên là
 * `StudentRepository`, dùng để thực hiện các thao tác với dữ liệu của sinh viên
 * trong cơ sở dữ liệu MongoDB. Repository này kế thừa từ
 * `MongoRepository<Student, Integer>`, giúp dễ dàng thực hiện các thao tác CRUD
 * (Create, Read, Update, Delete) mà không cần phải viết thêm mã cho các thao
 * tác cơ bản.
 * 
 * ### Các phương thức trong `StudentRepository`:
 * 
 * ### 1. **findStudentById(Integer id)**:
 * - Đây là phương thức tìm một sinh viên theo `id`.
 * - **MongoDB sẽ tự động tìm kiếm theo trường `_id`**: Trong MongoDB, mỗi tài
 * liệu sẽ có một trường mặc định là `_id`, và khi không chỉ định tên trường tìm
 * kiếm, MongoDB sẽ tìm theo trường này. Tuy nhiên, trong đoạn mã này, vì `id`
 * được sử dụng thay cho `_id`, Spring Data MongoDB sẽ tự động hiểu rằng `id` là
 * trường định danh của sinh viên trong cơ sở dữ liệu.
 * - Phương thức này trả về đối tượng `Student` tìm được từ cơ sở dữ liệu.
 * 
 * ### 2. **updateNumOfPages(Integer id, Integer numOfPages)**:
 * - **@Query("{ '_id' : ?0 }")**: Đây là một truy vấn MongoDB để tìm kiếm sinh
 * viên dựa trên `_id` (cái này có thể là `id` trong lớp `Student`).
 * - Phương thức này thực hiện việc **cập nhật số trang** (giả sử số trang này
 * là một thuộc tính của sinh viên), tuy nhiên trong MongoDB không hỗ trợ sử
 * dụng **@Modifying** (một annotation được sử dụng trong JPA để thực hiện các
 * thay đổi trong cơ sở dữ liệu). Do đó, phương thức này chỉ thực hiện truy vấn
 * để tìm sinh viên theo `id`, nhưng để thay đổi dữ liệu, cần một cách khác, có
 * thể là sử dụng `update` trực tiếp trong MongoDB hoặc thực hiện thay đổi bằng
 * cách sử dụng các phương thức của `MongoTemplate` trong Spring Data MongoDB.
 * 
 * ### Các điểm cần lưu ý:
 * - **MongoRepository**: Cung cấp các phương thức sẵn có như `save`, `findAll`,
 * `findById`, v.v., giúp thực hiện các thao tác cơ bản với MongoDB mà không cần
 * phải viết mã.
 * - **@Query**: Được sử dụng để viết truy vấn MongoDB trực tiếp với cú pháp
 * JSON. Trong trường hợp này, truy vấn tìm kiếm một sinh viên theo `_id`.
 * - **Không sử dụng @Modifying**: Vì MongoDB không hỗ trợ thao tác thay đổi dữ
 * liệu trực tiếp thông qua `@Modifying` như JPA, nên cần có cách khác để thực
 * hiện các thao tác cập nhật.
 * 
 * ### Tóm lại:
 * - Phương thức `findStudentById` dùng để tìm sinh viên theo `id`.
 * - Phương thức `updateNumOfPages` tìm sinh viên và có thể sẽ phải thay đổi
 * cách thực hiện vì MongoDB không hỗ trợ `@Modifying` như trong các hệ quản trị
 * cơ sở dữ liệu quan hệ.
 */