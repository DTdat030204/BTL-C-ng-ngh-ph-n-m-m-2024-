package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.configuration.PageUnitPrice;

@Repository
public interface PageUnitRepo extends MongoRepository<PageUnitPrice, String> {

    // Cập nhật giá trị price của PageUnitPrice có id = 1
    @Query("{ 'id': '1' }")
    public void setPrice(Integer price);

    // Lấy giá trị price của PageUnitPrice có id = 1
    @Query("{ 'id': '1' }")
    public Integer getValue();
}



















/*
 * Đoạn mã này định nghĩa một **repository** trong Spring Data MongoDB có tên là
 * `PageUnitRepo`. Repository này giúp thao tác với dữ liệu của đối tượng
 * `PageUnitPrice` trong cơ sở dữ liệu MongoDB. `PageUnitPrice` là một thực thể
 * (entity) có thể chứa thông tin về giá trị đơn vị trang (unit price).
 * 
 * ### Các phương thức trong `PageUnitRepo`:
 * 
 * ### 1. **setPrice(Integer price)**:
 * - Phương thức này thực hiện **cập nhật giá trị `price`** cho một đối tượng
 * `PageUnitPrice` có `id = '1'`.
 * - **@Query("{ 'id': '1' }")**: Truy vấn này tìm kiếm tài liệu có trường `id`
 * bằng `'1'` trong cơ sở dữ liệu. Dựa trên truy vấn này, MongoDB sẽ tìm và cập
 * nhật trường `price` cho đối tượng có `id = '1'`.
 * - Tuy nhiên, phương thức này **không thực sự cập nhật** giá trị của trường
 * `price` mà chỉ thực hiện một truy vấn tìm kiếm theo `id`. Để cập nhật, cần có
 * một phương thức sử dụng cú pháp MongoDB để thực hiện hành động `$set` để thay
 * đổi giá trị của trường `price`.
 * 
 * ### 2. **getValue()**:
 * - Phương thức này **trả về giá trị `price`** của đối tượng `PageUnitPrice` có
 * `id = '1'`.
 * - Truy vấn sử dụng **@Query("{ 'id': '1' }")** để tìm kiếm tài liệu có `id =
 * '1'` và trả về giá trị của trường `price` trong đối tượng `PageUnitPrice`.
 * - Phương thức này chỉ tìm kiếm và trả về giá trị của trường `price`, không có
 * thay đổi nào trong cơ sở dữ liệu.
 * 
 * ### Một số điểm cần lưu ý:
 * - **@Query**: Dùng để viết truy vấn MongoDB trực tiếp với cú pháp JSON. Trong
 * cả hai phương thức, truy vấn tìm kiếm tài liệu với `id = '1'` để lấy hoặc cập
 * nhật giá trị của trường `price`.
 * - **Không sử dụng phương thức cập nhật trực tiếp**: Để thực hiện cập nhật giá
 * trị cho trường `price`, thay vì chỉ truy vấn, bạn cần sử dụng một phương thức
 * cập nhật với cú pháp MongoDB như `{'$set': {'price': ?0}}`. Spring Data
 * MongoDB cung cấp các phương thức như `@Modifying` hoặc `MongoTemplate` để
 * thực hiện các thao tác thay đổi dữ liệu.
 * 
 * ### Tóm lại:
 * - Phương thức **setPrice** dùng để cập nhật giá trị của `price` cho
 * `PageUnitPrice` có `id = '1'`. Tuy nhiên, cần phải thay đổi cách thực hiện để
 * cập nhật dữ liệu thay vì chỉ truy vấn.
 * - Phương thức **getValue** trả về giá trị `price` của đối tượng
 * `PageUnitPrice` có `id = '1'`.
 */