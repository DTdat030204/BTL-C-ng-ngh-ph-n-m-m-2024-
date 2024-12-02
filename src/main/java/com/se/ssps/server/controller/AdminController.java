package com.se.ssps.server.controller;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;

import com.se.ssps.server.entity.user.Admin;
import com.se.ssps.server.helper.ApiResponse;
//import com.se.ssps.server.dto.PrinterDto;
//import com.se.ssps.server.dto.AdminRegistrationRequest;
import com.se.ssps.server.entity.Config;
import com.se.ssps.server.entity.PaymentLog;
import com.se.ssps.server.entity.Printer;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.configuration.Building;
import com.se.ssps.server.entity.configuration.Campus;
import com.se.ssps.server.entity.configuration.FileType;
import com.se.ssps.server.entity.configuration.MaxFileSize;
// import com.se.ssps.server.entity.configuration.MaxFileSize;
import com.se.ssps.server.entity.configuration.PageAllocation;
import com.se.ssps.server.entity.configuration.PageUnitPrice;
import com.se.ssps.server.entity.configuration.Room;
//import com.se.ssps.server.entity.response.LoginResponse;
//import com.se.ssps.server.entity.response.LoginResponse;
import com.se.ssps.server.service.user.AdminService;
import com.se.ssps.server.stat.ChartValue;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    // @PostMapping("/login")
    // public ResponseEntity<?> loginAdmin(@RequestBody Admin admin) {
    // LoginResponse loginResponse = new LoginResponse();
    // try {
    // // Tìm Admin dựa trên username
    // Admin foundAdmin = adminService.findAdminByUsername(admin.getUsername());
    // if (foundAdmin != null) {
    // // So sánh password
    // if (foundAdmin.getPassword().equals(admin.getPassword())) {
    // loginResponse.setUser(foundAdmin);
    // loginResponse.setCorrectPass(true);
    // return ResponseEntity.ok(loginResponse); // Đăng nhập thành công
    // }
    // // Mật khẩu sai
    // loginResponse.setUser(foundAdmin);
    // loginResponse.setCorrectPass(false);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    // .body("Sai mật khẩu"); // Trả về thông báo sai mật khẩu
    // }
    // // Nếu không tìm thấy Admin
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body("Admin không tồn tại"); // Trả về thông báo không tìm thấy admin
    // } catch (Exception e) {
    // // Lỗi hệ thống
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body("Lỗi hệ thống: " + e.getMessage()); // Trả về thông báo lỗi hệ thống
    // }
    // }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody Admin admin) {
        Map<String, String> response = new HashMap<>();
        try {
            Admin foundAdmin = adminService.findAdminByUsername(admin.getUsername());
            if (foundAdmin != null) {
                if (foundAdmin.getPassword().equals(admin.getPassword())) {
                    response.put("status", "success");
                    response.put("message", "Đăng nhập thành công");
                    return ResponseEntity.ok(response);
                }
                response.put("status", "error");
                response.put("message", "Sai mật khẩu");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            response.put("status", "error");
            response.put("message", "Admin không tồn tại");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/config")
    public Config configStat() {
        return adminService.getAllConfig();
    }

    // API để đăng ký Admin mới
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        try {
            Admin newAdmin = adminService.registerAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Admin registered successfully with ID: " + newAdmin.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    // @PostMapping("/file-size")
    // public MaxFileSize setMaxFileSize(@RequestParam(name = "size") double
    // maxFileSize) {
    // return adminService.setMaxFileSize(maxFileSize);
    // }
    @PostMapping("/file-size")
    public ResponseEntity<ApiResponse> setMaxFileSize(@RequestParam(name = "size") double maxFileSize) {
        try {
            MaxFileSize updatedMaxFileSize = adminService.setMaxFileSize(maxFileSize);

            ApiResponse response = new ApiResponse(
                    "success",
                    "Kích thước file tối đa đã được cập nhật thành công",
                    updatedMaxFileSize);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Không thể cập nhật kích thước file tối đa: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // @PostMapping("/unit-price")
    // public PageUnitPrice setPageUnitPrice(@RequestParam(name = "price") Integer
    // pageUnitPrice) {
    // return adminService.setPagePrice(pageUnitPrice);
    // }
    @PostMapping("/unit-price")
    public ResponseEntity<ApiResponse> setPageUnitPrice(@RequestParam(name = "price") Integer pageUnitPrice) {
        try {
            PageUnitPrice updatedPrice = adminService.setPagePrice(pageUnitPrice);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Giá đơn vị trang đã được cập nhật thành công",
                    updatedPrice);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Không thể cập nhật giá đơn vị trang: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // @PostMapping("/add-file-type")
    // public FileType addFileType(@RequestBody FileType newFileType) {
    // return adminService.addType(newFileType);
    // }
    @PostMapping("/add-file-type")
    public ResponseEntity<ApiResponse> addFileType(@RequestBody FileType newFileType) {
        try {
            FileType addedFileType = adminService.addType(newFileType);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Loại file mới đã được thêm thành công",
                    addedFileType // Dữ liệu bổ sung
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Không thể thêm loại file mới: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/delete-file-type")
    public void deleteFileType(@RequestParam Integer id) {
        adminService.deleteType(id);
    }

    @GetMapping("/index")
    public String adminHome() {
        return "this is admin homepage";
    }

    // =====================================================================================
    // Thao tác đối với máy in:
    // Hiện thị danh sách máy in
    @GetMapping("/printer")
    public List<Printer> listOfPrinter() {
        return adminService.findAllPrinter();
    }

    // @GetMapping("/printer-stat")
    // public List<PrinterDto> listOfPrinterWithStat() {
    // return adminService.findAllPrinterStat();
    // }


    // @PostMapping("/addprinter")
    // public Printer addPrinter(@RequestParam(name = "room-name") String roomName,
    //         @RequestParam(name = "building-name") String buildingName,
    //         @RequestParam(name = "campus-name") String campusName,
    //         @RequestBody Printer newPrinter) {
    //     return adminService.addPrinter(roomName, buildingName, campusName, newPrinter);
    // }
    @PostMapping("/addprinter")
    public ApiResponse addPrinter(@RequestParam(name = "room-name") String roomName,
            @RequestParam(name = "building-name") String buildingName,
            @RequestBody Printer newPrinter) {
        try {
            Printer savedPrinter = adminService.addPrinter(roomName, buildingName, newPrinter);
            return new ApiResponse("success", "Máy in đã được thêm thành công.", savedPrinter);
        } catch (Exception e) {
            return new ApiResponse("error", e.getMessage(), null);
        }
    }

    // // Thêm một máy in mới
    // @PostMapping("/addprinter")
    // public Printer addPrinter(@RequestParam(name = "room-name") String roomName, @RequestBody Printer newPrinter) {
    //     return adminService.addPrinter(roomName, newPrinter);
    // }
    // @PostMapping("/addprinter")
    // public Printer addPrinter(@RequestParam(name = "room-id") String room_id,
    // @RequestBody Printer newPrinter) {
    // return adminService.addPrinter(room_id, newPrinter);
    // }

    // Xóa một printer ra khỏi hệ thống***
    @DeleteMapping("/deleteprinter")
    public Map<String, Boolean> deletePrinter(@RequestParam Integer id) {
        return adminService.deletePrinter(id);
    }

    // Cập nhập một máy in mới
    @PutMapping("/updateprinter")
    public Map<String, Boolean> updatePrinter(@RequestBody Printer newPrinter,
            @RequestParam(name = "room-id") String roomId) {
        return adminService.updatePrinter(newPrinter, roomId);
    }

    // Hiện thị thông tin một máy in
    @GetMapping("/printer/info")
    public Printer printerInfo(@RequestParam String id) {
        return adminService.findPrinterById(id);
    }

    // Hiện thị trạng thái một máy in
    @GetMapping("/printer/status")
    public Printer printerStatus(@RequestParam String id) {
        return adminService.findPrinterById(id);
    }

    // =====================================================================================
    // Thao tác đối với vị trí
    // Thao tác đối với cơ sở
    // Hiện thị danh sách các cơ sở
    @GetMapping("/campus")
    public List<Campus> listOfCampus() {
        return adminService.findAllCampus();
    }

    @PostMapping("/addcampus")
    public Campus addCampus(@RequestBody Campus newCampus) {
        return adminService.addCampus(newCampus);

    }

    @DeleteMapping("/deletecampus")
    public Map<String, Boolean> deleteCampus(@RequestParam Integer id) {
        return adminService.deleteCampus(id);
    }

    // Thao tác đối với tòa
    @GetMapping("/building")
    public List<Building> listOfBuildings() {
        return adminService.findAllBuilding();
    }

    // @PostMapping("/addbuilding")
    // public Building addBuilding(@RequestParam(name = "campus-id") Integer
    // campus_id, @RequestBody Building building) {
    // return adminService.addBuilding(campus_id, building);
    // }
    @PostMapping("/addbuilding")
    public Building addBuilding(@RequestParam(name = "campus-name") String campusName, @RequestBody Building building) {
        return adminService.addBuilding(campusName, building);
    }

    @DeleteMapping("/building")
    public Map<String, Boolean> deleteBuilding(@RequestParam Integer id) {
        return adminService.deleteBuilding(id);
    }

    // Thao tác đối với phòng
    @GetMapping("/room")
    public List<Room> listOfRooms() {
        return adminService.findAllRoom();
    }

    // 25/11/2024
    // @PostMapping("/addroom")
    // public Room addRoom(@RequestParam(name = "building-id") Integer building_id,
    // @RequestBody Room room) {
    // return adminService.addRoom(building_id, room);
    // }
    @PostMapping("/addroom")
    public Room addRoom(@RequestParam(name = "building-name") String buildingName, @RequestBody Room room) {
        return adminService.addRoom(buildingName, room);
    }

    @DeleteMapping("/deleteroom")
    public Map<String, Boolean> deleteRoom(@RequestParam String id) {
        return adminService.deleteRoom(id);
    }

    // =====================================================================================

    // =====================================================================================
    // Hiện thị thông tin danh sách lịch sử in
    @GetMapping("/printing-logs")
    public List<PrintingLog> listOfPritntingLogs() {
        return adminService.findAllPrintingLogs();
    }

    // Hiện thị thông tin danh sách lịch sử mua
    @GetMapping("/payment-logs")
    public List<PaymentLog> listOfPaymentLogs() {
        return adminService.findAllPaymentLog();
    }

    // =====================================================================================
    // =====================================================================================
    // Hiện thị thao tác đối với cấp phát trang in
    @GetMapping("/page-allocation")
    public List<PageAllocation> listOfPageAllocations() {
        return adminService.findAllPageAllocations();
    }

    // @PostMapping("/add-page-allocation")
    // public PageAllocation addPageAllocation(@RequestBody PageAllocation
    // newAllocation) {
    // return adminService.addPageAllocation(newAllocation);
    // }

    @PostMapping("/add-page-allocation")
    public ResponseEntity<ApiResponse> addPageAllocation(@RequestBody PageAllocation newAllocation) {
        try {
            PageAllocation allocation = adminService.addPageAllocation(newAllocation);
            ApiResponse response = new ApiResponse(
                    "success",
                    "Phân bổ trang thành công",
                    allocation);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiResponse errorResponse = new ApiResponse(
                    "error",
                    "Lỗi trong quá trình phân bổ trang: " + e.getMessage(),
                    null);
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/delete-page-allocation")
    public boolean deleteAllocation(@RequestParam Integer id) {
        return adminService.deletePageAllocation(id);
    }

    // =====================================================================================
    // =====================================================================================
    // Thống kê
    // Thống kê số trang theo từng máy in trong khoảng thời gian (from, to)
    @GetMapping("/statistics/pages-by-printer")
    public List<ChartValue> pageByPrinter(@RequestParam String from, @RequestParam String to) {
        return adminService.totalSquare(YearMonth.parse(from), YearMonth.parse(to));
    }

    // Thống kê tỉ lệ số yêu cầu theo từng máy in trong khoảng thời gian (from, to)
    @GetMapping("/statistics/request-by-printer")
    public List<ChartValue> requestByPrinter(@RequestParam String from, @RequestParam String to) {
        return adminService.printingRequest(YearMonth.parse(from), YearMonth.parse(to));
    }

    // Thống kê tỉ lệ loại kích thước trang được yêu cầu in trong khoảng thời gian
    // (from, to)
    @GetMapping("/statistics/size-by-month")
    public List<ChartValue> pageSizeByMonth(@RequestParam String from, @RequestParam String to) {
        return adminService.pageSizeByMonth(YearMonth.parse(from), YearMonth.parse(to));
    }

    // Thống kê số tiền bán trang in đối với từng tháng trong khoảng thời gian
    // (from, to)
    @GetMapping("/statistics/profit-by-mont`h")
    public List<ChartValue> profitByMonth(@RequestParam String from, @RequestParam String to) {
        return adminService.profitByMonth(YearMonth.parse(from), YearMonth.parse(to));
    }
}
