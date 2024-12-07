package com.se.ssps.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;

import com.se.ssps.server.entity.configuration.PageAllocation;

// @Repository
// public interface PageAllocationRepository extends MongoRepository<PageAllocation, String> {
//     // Các phương thức CRUD mặc định đã được MongoRepository cung cấp
//     PageAllocation findAllocationById(String id); // Tìm PageAllocation theo id
// }

public interface PageAllocationRepository extends MongoRepository<PageAllocation, String> {
    boolean existsBySemesterAndYear(Integer semester, Integer year);

    PageAllocation findAllocationById(String id);
}

/*
 * Đoạn mã của bạn định nghĩa một `PageAllocationRepository` trong Spring Data
 * MongoDB để thao tác với đối tượng `PageAllocation`. Repository này kế thừa từ
 * `MongoRepository`, điều này cung cấp các phương thức CRUD mặc định cho việc
 * làm việc với MongoDB. Tuy nhiên, trong đoạn mã bạn đã để lại một số phần được
 * chú thích, có thể có ý định thử nghiệm hoặc phát triển thêm các truy vấn tùy
 * chỉnh.
 * 
 * ### Phân tích mã nguồn
 * 
 * ```java
 * package com.se.ssps.server.repository;
 * 
 * import org.springframework.data.mongodb.repository.MongoRepository;
 * //import org.springframework.data.mongodb.repository.Query;
 * import org.springframework.stereotype.Repository;
 * import com.se.ssps.server.entity.configuration.PageAllocation;
 * 
 * @Repository
 * public interface PageAllocationRepository extends
 * MongoRepository<PageAllocation, String> {
 * // Các phương thức CRUD mặc định đã được MongoRepository cung cấp
 * PageAllocation findAllocationById(String id); // Tìm PageAllocation theo id
 * }
 * ```
 * 
 * ### Giải thích chi tiết
 * 
 * 1. **Kế thừa từ `MongoRepository<PageAllocation, String>`**:
 * - `MongoRepository` cung cấp các phương thức như `findById()`, `save()`,
 * `delete()`, và `findAll()`, giúp thao tác với MongoDB mà không cần phải viết
 * mã cho các truy vấn cơ bản.
 * - `PageAllocation` là entity bạn muốn thao tác với MongoDB, và `String` là
 * kiểu dữ liệu của trường `_id` trong MongoDB.
 * 
 * 2. **Annotation `@Repository`**:
 * - `@Repository` đánh dấu lớp này là một Spring Data repository, giúp Spring
 * nhận diện và quản lý repository này để thực hiện các thao tác với MongoDB.
 * 
 * 3. **Phương thức `findAllocationById(String id)`**:
 * - `findAllocationById` là một phương thức truy vấn tùy chỉnh dùng để tìm kiếm
 * `PageAllocation` dựa trên `id` (kiểu `String`).
 * - Phương thức này sẽ trả về một đối tượng `PageAllocation` hoặc `null` nếu
 * không tìm thấy bản ghi với `id` đó.
 * 
 * ### Phương thức CRUD mặc định
 * 
 * Spring Data MongoDB tự động cung cấp các phương thức CRUD cơ bản mà bạn không
 * cần phải định nghĩa lại. Ví dụ:
 * 
 * - **Tìm theo ID**: `findById(id)` trả về một `Optional<PageAllocation>`.
 * - **Lưu hoặc cập nhật**: `save(PageAllocation entity)` sẽ lưu hoặc cập nhật
 * một đối tượng `PageAllocation`.
 * - **Xóa**: `deleteById(id)` sẽ xóa đối tượng theo ID.
 * 
 * ### Tùy chỉnh truy vấn với `@Query`
 * 
 * Mặc dù trong đoạn mã bạn đã chú thích phần truy vấn tùy chỉnh với `@Query`,
 * bạn có thể dễ dàng sử dụng chúng để thực hiện các truy vấn phức tạp hơn nếu
 * cần. Ví dụ, trong mã chú thích:
 * 
 * ```java
 * // Cập nhật trạng thái của PageAllocation nếu allocatedDate trước thời điểm
 * hiện tại
 * // @Query(value = "{ 'allocatedDate': { '$lt': new Date() } }", update =
 * "{ '$set': { 'status': true } }")
 * // public void updatePageAllocationStatus();
 * ```
 * 
 * Truy vấn này sẽ tìm các bản ghi có trường `allocatedDate` nhỏ hơn thời gian
 * hiện tại và cập nhật trường `status` thành `true`. Tuy nhiên, trong MongoDB,
 * bạn thường sẽ sử dụng `MongoTemplate` để thực hiện các cập nhật phức tạp như
 * vậy.
 * 
 * ### Thực thi phương thức `findAllocationById` trong dịch vụ
 * 
 * Dưới đây là một ví dụ về cách sử dụng `PageAllocationRepository` trong một
 * dịch vụ:
 * 
 * ```java
 * 
 * @Service
 * public class PageAllocationService {
 * 
 * private final PageAllocationRepository pageAllocationRepository;
 * 
 * public PageAllocationService(PageAllocationRepository
 * pageAllocationRepository) {
 * this.pageAllocationRepository = pageAllocationRepository;
 * }
 * 
 * // Lấy PageAllocation theo id
 * public PageAllocation getPageAllocationById(String id) {
 * return pageAllocationRepository.findAllocationById(id); // Trả về
 * PageAllocation hoặc null nếu không tìm thấy
 * }
 * 
 * // Lấy tất cả các PageAllocation (nếu cần)
 * public List<PageAllocation> getAllPageAllocations() {
 * return pageAllocationRepository.findAll(); // Trả về danh sách tất cả
 * PageAllocations
 * }
 * }
 * ```
 * 
 * ### Kết luận
 * 
 * - **Phương thức `findAllocationById(String id)`** trong
 * `PageAllocationRepository` là một phương thức truy vấn đơn giản để tìm một
 * đối tượng `PageAllocation` theo `id`.
 * - Bạn có thể mở rộng thêm các truy vấn tùy chỉnh bằng cách sử dụng `@Query`
 * nếu cần. Tuy nhiên, với MongoDB, các thao tác cập nhật phức tạp thường được
 * thực hiện bằng `MongoTemplate`, thay vì thông qua các truy vấn cập nhật trong
 * repository.
 */