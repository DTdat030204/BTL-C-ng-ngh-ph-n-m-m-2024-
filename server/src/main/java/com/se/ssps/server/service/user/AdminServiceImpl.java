package com.se.ssps.server.service.user;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.se.ssps.server.entity.user.Admin;
import com.se.ssps.server.helper.PaperTypeUsage;
// import com.se.ssps.server.entity.user.User;
// import com.se.ssps.server.dto.AdminRegistrationRequest;
//import com.se.ssps.server.dto.PrinterDto;
import com.se.ssps.server.entity.Config;
import com.se.ssps.server.entity.PageSize;
import com.se.ssps.server.entity.PaymentLog;
import com.se.ssps.server.entity.Printer;
import com.se.ssps.server.entity.PrintingLog;
import com.se.ssps.server.entity.configuration.Building;
import com.se.ssps.server.entity.configuration.Campus;
import com.se.ssps.server.entity.configuration.FileType;
import com.se.ssps.server.entity.configuration.MaxFileSize;
import com.se.ssps.server.entity.configuration.PageAllocation;
import com.se.ssps.server.entity.configuration.PageUnitPrice;
import com.se.ssps.server.entity.configuration.Room;
import com.se.ssps.server.repository.AdminRepository;
import com.se.ssps.server.repository.BuildingRepository;
import com.se.ssps.server.repository.CampusRepository;
import com.se.ssps.server.repository.FileTypeRepository;
import com.se.ssps.server.repository.MaxSizeRepository;
import com.se.ssps.server.repository.PageAllocationRepository;
import com.se.ssps.server.repository.PageUnitRepo;
import com.se.ssps.server.repository.PaymentLogRepository;
import com.se.ssps.server.repository.PrinterRepository;

//import com.se.ssps.server.repository.AdminRepository;

import com.se.ssps.server.repository.PrintingLogRepository;

import com.se.ssps.server.repository.RoomRepository;

//import com.se.ssps.server.repository.user.UserRepository;

import com.se.ssps.server.stat.ChartValue;
//import java.util.Optional;
import com.se.ssps.server.stat.ChartValueByType;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
// import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
// import org.springframework.stereotype.Service;
// import java.util.List;
import java.util.Date;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.aggregation.Aggregation;
// import org.springframework.data.mongodb.core.aggregation.AggregationResults;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.bson.Document;
// import java.time.YearMonth;
// import java.time.LocalDateTime;
// import java.util.*;
// import com.se.ssps.server.entity.Printer;
// import com.se.ssps.server.entity.PrintingLog;
// import com.se.ssps.server.dto.ChartValue; // Điều chỉnh theo đúng package của bạn
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.bson.Document;
import org.bson.types.ObjectId;

@Service
public class AdminServiceImpl implements AdminService {
    // Khai báo các repository
    // @Autowired
    @Autowired
    PageUnitRepo pageUnitRepo;

    @Autowired
    MaxSizeRepository maxFileSizeRepo;

    @Autowired
    PageUnitRepo pageUnitPriceRepo;

    @Autowired
    PrinterRepository printerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    CampusRepository campusRepository;

    @Autowired
    PrintingLogRepository printingLogRepository;

    @Autowired
    PaymentLogRepository paymentLogRepository;

    @Autowired
    PageAllocationRepository pageAllocationRepository;

    @Autowired
    FileTypeRepository fileTypeRepository;

    // =====================================================================================
    // =====================================================================================

    public Admin findAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // Thao tác đối với máy in
    @Override
    public List<Printer> findAllPrinter() {
        return printerRepository.findAll();
    }

    @Override
    public Printer addPrinter(String roomName, String buildingName, Printer newPrinter) {
        Room room = roomRepository.findByRoomName(roomName);
        if (room == null) {
            room = new Room();
            room.setRoomName(roomName);
            roomRepository.save(room);
        }
        if (room.isHavePrinter()) {
            Building roomBuilding = room.getBuilding();
            if (roomBuilding != null && roomBuilding.getBuildingName().equals(buildingName)) {
                throw new RuntimeException("Phòng này đã có máy in, không thể thêm máy in mới.");
            }
        }
        Building building = buildingRepository.findByBuildingName(buildingName);
        if (building == null) {
            building = new Building();
            building.setBuildingName(buildingName);
            buildingRepository.save(building);
        }
        newPrinter.setRoom(room);
        newPrinter.setBuilding(building);
        Printer savedPrinter = printerRepository.save(newPrinter);
        room.setPrinter(savedPrinter);
        room.setHavePrinter(true);
        room.setBuilding(building);
        roomRepository.save(room);

        return savedPrinter;
    }

    @Override
    public Printer findPrinterById(String id) {
        // Chuyển id kiểu Integer thành String
        return printerRepository.findById(id.toString()) // Chuyển Integer thành String
                .orElseThrow(() -> new RuntimeException("Printer not found"));
    }

    @Override
    public Map<String, Boolean> deletePrinter(Integer id) { // Nhận Integer theo định nghĩa lớp cha
        // Chuyển đổi Integer sang String
        String stringId = id.toString();

        // Thực hiện logic xóa
        HashMap<String, Boolean> response = new HashMap<>();
        Printer printer = printerRepository.findById(stringId)
                .orElseThrow(() -> new RuntimeException("Printer not found"));
        printerRepository.delete(printer);
        response.put("accepted", true);
        return response;
    }

    @Override
    public Map<String, Boolean> updatePrinter(Printer newPrinter, String roomId) {
        HashMap<String, Boolean> response = new HashMap<>();
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        if (room.getPrinter() != null && room.getPrinter().getId().equals(newPrinter.getId())) {
            updatePrinterDetails(newPrinter);
            response.put("accepted", true);
            return response;
        }

        room.setPrinter(newPrinter);
        roomRepository.save(room);
        updatePrinterDetails(newPrinter);
        response.put("accepted", true);
        return response;
    }

    private void updatePrinterDetails(Printer printer) {
        Printer existingPrinter = printerRepository.findById(printer.getId())
                .orElseThrow(() -> new RuntimeException("Printer not found"));
        existingPrinter.setDescription(printer.getDescription());
        existingPrinter.setEfficiency(printer.getEfficiency());
        existingPrinter.setFirm(printer.getFirm());
        existingPrinter.setInkAmount(printer.getInkAmount());
        existingPrinter.setPrinterName(printer.getPrinterName());
        existingPrinter.setPageAmount(printer.getPageAmount());
        existingPrinter.setStatus(printer.getStatus());
        printerRepository.save(existingPrinter);
    }

    // =====================================================================================
    // =====================================================================================
    // Thao tác đối với cấu hình vị trí
    @Override
    public List<Campus> findAllCampus() {
        return campusRepository.findAll();
    }

    @Override
    public Campus addCampus(Campus newCampus) {
        List<Campus> campusCheckList = campusRepository.findAll();
        for (Campus campus : campusCheckList) {
            if (campus.equals(newCampus)) {
                return null;
            }
        }
        return campusRepository.save(newCampus);
    }

    @Override
    public Map<String, Boolean> deleteCampus(Integer id) {
        HashMap<String, Boolean> response = new HashMap<>();

        // Chuyển đổi Integer sang String
        String stringId = String.valueOf(id);

        // Tìm kiếm campus bằng id (kiểu String)
        Campus findCampus = campusRepository.findById(stringId)
                .orElseThrow(() -> new RuntimeException("Campus not found"));

        // Kiểm tra nếu không có tòa nhà thì xóa, nếu không trả về false
        if (findCampus.getBuildings().isEmpty()) {
            campusRepository.delete(findCampus);
            response.put("accepted", true);
        } else {
            response.put("accepted", false);
        }
        return response;
    }

    // =====================================================================================
    // @Override
    // public List<Building> findAllBuilding() {
    // return buildingRepository.findAll();
    // }
    @Override
    public List<Building> findAllBuilding() {
        List<Building> buildings = buildingRepository.findAll();

        // Lấy danh sách Room tương ứng và gán thủ công
        for (Building building : buildings) {
            List<Room> rooms = roomRepository.findByBuildingId(building.getId());
            building.setRooms(rooms);
        }

        return buildings;
    }

    @Override
   
    public Building addBuilding(String campusName, Building building) {
        // Tìm Campus theo tên
        Campus campus = campusRepository.findByCampusName(campusName);

        // Kiểm tra nếu campus không tồn tại
        if (campus == null) {
            throw new RuntimeException("Campus không tồn tại!");
        }

        // Gán Building vào Campus
        building.setCampus(campus);

        // // Lưu Building vào database
        // return buildingRepository.save(building);
        // Lưu Building vào database
        Building savedBuilding = buildingRepository.save(building);

        // Cập nhật lại danh sách tòa nhà trong Campus sau khi lưu Building
        if (campus.getBuildings() == null) {
            campus.setBuildings(new ArrayList<>());
        }
        campus.getBuildings().add(savedBuilding);

        // Lưu lại Campus để cập nhật danh sách tòa nhà
        campusRepository.save(campus);

        return savedBuilding;
    }

    // *************************************************************** */

    @Override
    public Map<String, Boolean> deleteBuilding(Integer id) {
        HashMap<String, Boolean> response = new HashMap<>();

        // Chuyển đổi id từ Integer sang String
        String stringId = String.valueOf(id);

        // Tìm Building bằng id đã chuyển đổi
        Building findBuilding = buildingRepository.findById(stringId)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        // Kiểm tra điều kiện xóa
        if (findBuilding.getRooms().isEmpty()) {
            buildingRepository.delete(findBuilding);
            response.put("accepted", true);
        } else {
            response.put("accepted", false);
        }
        return response;
    }

    // =====================================================================================
    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    // 25/11/2024
    @Override
   

    public Room addRoom(String buildingName, Room room) {
        // Tìm Building theo tên
        Building building = buildingRepository.findByBuildingName(buildingName);

        // Kiểm tra nếu không tìm thấy Building
        if (building == null) {
            throw new RuntimeException("Tòa nhà không tồn tại!");
        }

        // Gán Building cho Room
        room.setBuilding(building);

        // Lưu Room vào database
        Room savedRoom = roomRepository.save(room);

        // Cập nhật danh sách Room trong Building
        if (building.getRooms() == null) {
            building.setRooms(new ArrayList<>());
        }
        building.getRooms().add(savedRoom);

        // Lưu lại Building để cập nhật danh sách Room
        buildingRepository.save(building);

        return savedRoom;
    }

    @Override
    public Map<String, Boolean> deleteRoom(String id) {
        HashMap<String, Boolean> response = new HashMap<>();
        Room findRoom = roomRepository.findById(id).orElse(null);
        if (findRoom == null) {
            response.put("accepted", false);
            return response;
        }
        if (findRoom.getPrinter() == null) {
            roomRepository.delete(findRoom);
            response.put("accepted", true);
        } else {
            response.put("accepted", false);
        }
        return response;
    }

    // =====================================================================================
    // =====================================================================================
    // thao tác đối với xem thông tin lịch sử
    @Override
    public List<PrintingLog> findAllPrintingLogs() {
        return printingLogRepository.findAll();
    }

    // =====================================================================================
    @Override
    public List<PaymentLog> findAllPaymentLog() {
        return paymentLogRepository.findAll();
    }

    // =====================================================================================
    // =====================================================================================

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updatePageAllocationStatus() {
        Query query = new Query(Criteria.where("allocatedDate").lt(new Date()));
        Update update = new Update().set("status", true); // Cập nhật trạng thái
        mongoTemplate.updateMulti(query, update, PageAllocation.class);
    }

    @Override
    public List<PageAllocation> findAllPageAllocations() {
        updatePageAllocationStatus(); // Cập nhật trạng thái trước khi lấy tất cả PageAllocation
        return pageAllocationRepository.findAll(); // Trả về tất cả các PageAllocation
    }
    // CÁI TRÊN LÀ THAO TÁC ĐỐI VỚI CẤP PHÁT TRANG

    @Override
    public PageAllocation addPageAllocation(PageAllocation newPageAllocation) {
        return pageAllocationRepository.save(newPageAllocation); // Lưu mới
    }

    @Override
    public boolean deletePageAllocation(Integer id) {
        PageAllocation allocation = pageAllocationRepository.findAllocationById(id.toString()); // Chuyển id thành
                                                                                                // String

        if (allocation.getStatus()) { // Nếu trạng thái là true (đang hoạt động)
            return false;
        }

        pageAllocationRepository.delete(allocation); // Xóa phân bổ trang
        return true;
    }
    // ************************************************************************** */

    // =====================================================================================
    // =====================================================================================

    @Override
    public List<FileType> findAllType() {
        return fileTypeRepository.findAll();
    }

    @Override
    public FileType addType(FileType fileType) {
        return fileTypeRepository.save(fileType);
    }

    // ********************************************** */
    // @Override
    // public void deleteType(Integer fileTypeId) {
    // fileTypeRepository.deleteById(fileTypeId);
    // }
    @Override
    public void deleteType(Integer fileTypeId) {
        fileTypeRepository.deleteById(fileTypeId.toString()); // Chuyển Integer thành String
    }
   

    @Override
    public Config getAllConfig() {
        Config returnConfig = new Config();
        returnConfig.setFileTypeList(fileTypeRepository.findAll());

        // Lấy MaxFileSize đầu tiên từ danh sách
        MaxFileSize maxFileSize = maxFileSizeRepo.findAll().stream().findFirst().orElse(new MaxFileSize());
        returnConfig.setMaxFileSize(maxFileSize.getValue());

        // Lấy PageUnitPrice đầu tiên từ danh sách
        PageUnitPrice pageUnitPrice = pageUnitPriceRepo.findAll().stream().findFirst().orElse(new PageUnitPrice());
        returnConfig.setPageUnitPrice(pageUnitPrice.getValue());

        return returnConfig;
    }
    

    @Autowired
    private MaxSizeRepository maxSizeRepository;

    @Override
    public MaxFileSize setMaxFileSize(double maxFileSize) {
        // Tìm bản ghi đầu tiên trong cơ sở dữ liệu
        MaxFileSize existingRecord = maxSizeRepository.findAll().stream().findFirst().orElse(null);

        if (existingRecord == null) {
            // Nếu không có bản ghi, tạo mới
            existingRecord = new MaxFileSize();
        }

        // Cập nhật giá trị maxFileSize
        existingRecord.setValue(maxFileSize);

        // Lưu lại vào MongoDB
        return maxSizeRepository.save(existingRecord);
    }

  
    @Override
    public PageUnitPrice setPagePrice(Integer pagePrice, String pageSize) {
        // Kiểm tra xem đã có loại giấy đó chưa
        PageUnitPrice existing = pageUnitPriceRepo.findByPageSize(pageSize); // Bạn cần thêm phương thức này trong
                                                                             // repository

        if (existing == null) {
            // Tạo đối tượng mới với loại giấy và giá tiền
            PageUnitPrice newPagePrice = new PageUnitPrice(UUID.randomUUID().toString(), pagePrice, pageSize); // Sử
                                                                                                               // dụng
                                                                                                               // UUID
                                                                                                               // để tạo
                                                                                                               // id duy
                                                                                                               // nhất
            return pageUnitPriceRepo.save(newPagePrice);
        } else {
            // Nếu đã có loại giấy đó, cập nhật giá tiền
            existing.setValue(pagePrice);
            return pageUnitPriceRepo.save(existing);
        }
    }

   
    private Double pagesNum(Integer printerId, LocalDateTime from, LocalDateTime to) {
        return printingLogRepository.sumPageNum(printerId.toString(), from, to); // Chuyển Integer thành String
    }
   
    @Override
    public List<ChartValue> totalSquare(YearMonth from, YearMonth to) {
        ArrayList<ChartValue> returnList = new ArrayList<>();
        LocalDateTime fromDate = from.atDay(1).atStartOfDay();
        LocalDateTime toDate = to.atEndOfMonth().atTime(23, 59, 59);
        List<Printer> printerList = printerRepository.findAll();

        for (Printer printer : printerList) {
            ChartValue newValue = new ChartValue();
            newValue.setName(printer.getRoom().getRoomName() + printer.getRoom().getBuilding().getBuildingName());

            // Chuyển ID của Printer từ String sang Integer
            Integer printerId = Integer.parseInt(printer.getId()); // Nếu id là String nhưng cần Integer
            newValue.setStat(pagesNum(printerId, fromDate, toDate));

            returnList.add(newValue);
        }
        return returnList;
    }
    
    @Override
    public List<ChartValue> printingRequest(YearMonth from, YearMonth to) {
        // Khởi tạo danh sách trả về
        List<ChartValue> returnList = new ArrayList<>();

        // Xác định khoảng thời gian
        LocalDateTime fromDate = from.atDay(1).atStartOfDay();
        LocalDateTime toDate = to.atEndOfMonth().atTime(23, 59, 59);

        // Lấy danh sách máy in
        List<Printer> printerList = printerRepository.findAll();

        // Tạo aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("startDate").gte(fromDate).lte(toDate)),
                Aggregation.group("printer.id")
                        .count().as("requestCount")
                        .first("printer.id").as("_id") // Đảm bảo `_id` có giá trị
        );

        // Thực hiện aggregation
        AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, PrintingLog.class, Document.class);

        // Chuyển kết quả aggregation thành map
        Map<String, Integer> requestCounts = new HashMap<>();
        for (Document doc : result) {
            // Chuyển đổi `_id` thành chuỗi
            Object idObj = doc.get("_id");
            String printerId = idObj != null ? idObj.toString() : null;

            if (printerId == null) {
                System.out.println("Skip null printerId");
                continue; // Bỏ qua bản ghi nếu `_id` null
            }

            Object countObj = doc.get("requestCount");
            System.out.println("countObj: " + countObj + ", printerId: " + printerId);

            Integer count = 0;

            if (countObj instanceof Integer) {
                count = (Integer) countObj;
            }

            requestCounts.put(printerId, count);
        }

        // Tạo danh sách kết quả
        for (Printer printer : printerList) {
            ChartValue newValue = new ChartValue();

            // Lấy thông tin room, building, và campus
            String roomName = printer.getRoom() != null ? printer.getRoom().getRoomName() : "Room không xác định";
            String buildingName = printer.getBuilding() != null ? printer.getBuilding().getBuildingName()
                    : "Building không xác định";

            // Ghép thông tin
            newValue.setName(buildingName + "-" + roomName);

            // Lấy giá trị từ requestCounts
            String printerId = printer.getId();
            boolean containsKey = requestCounts.containsKey(printerId);
            System.out.println("printerId: " + printerId + ", containsKey: " + containsKey);

            Integer requestCount = requestCounts.getOrDefault(printerId, 0);
            newValue.setStat(requestCount.doubleValue());

            returnList.add(newValue);
        }

        return returnList;
    }

   
    @Override
    public List<ChartValue> profitByMonth(YearMonth from, YearMonth to) {
        ArrayList<ChartValue> returnList = new ArrayList<>();
        while (!from.isAfter(to)) {
            // Xác định khoảng thời gian bắt đầu và kết thúc trong tháng
            LocalDateTime fromDate = from.atDay(1).atStartOfDay();
            LocalDateTime toDate = from.atEndOfMonth().atTime(23, 59, 59);

            // Sử dụng truy vấn trong repository để tính tổng `printingCost` theo tháng
            Double monthlyProfit = printingLogRepository.calculateTotalProfit(fromDate, toDate);
            if (monthlyProfit == null) {
                monthlyProfit = 0.0; // Nếu không có log in nào, lợi nhuận bằng 0
            }

            // Tạo đối tượng ChartValue
            String label = from.getMonth().toString() + " " + from.getYear();
            ChartValue value = new ChartValue(label, monthlyProfit);
            returnList.add(value);

            // Chuyển sang tháng tiếp theo
            from = from.plusMonths(1);
        }
        return returnList;
    }

  
    @Override
    public List<ChartValue> profitByMonthByPrinter(YearMonth from, YearMonth to, String printerId) {
        ArrayList<ChartValue> returnList = new ArrayList<>();
        while (!from.isAfter(to)) {
            // Xác định khoảng thời gian bắt đầu và kết thúc trong tháng
            LocalDateTime fromDate = from.atDay(1).atStartOfDay();
            LocalDateTime toDate = from.atEndOfMonth().atTime(23, 59, 59);

            // Sử dụng truy vấn trong repository để tính tổng `printingCost` theo tháng cho
            // printer
            Double monthlyProfit = printingLogRepository.calculateTotalProfitByPrinter(fromDate, toDate, printerId);
            if (monthlyProfit == null) {
                monthlyProfit = 0.0; // Nếu không có log in nào, lợi nhuận bằng 0
            } // Giá trị mặc định nếu Optional rỗng

            // Tạo đối tượng ChartValue
            String label = from.getMonth().toString() + " " + from.getYear();
            ChartValue value = new ChartValue(label, monthlyProfit);
            returnList.add(value);

            // Chuyển sang tháng tiếp theo
            from = from.plusMonths(1);
        }
        return returnList;
    }

  

    @Override
    public List<ChartValueByType> paperUsageByMonth(YearMonth from, YearMonth to) {
        ArrayList<ChartValueByType> returnList = new ArrayList<>();
        while (!from.isAfter(to)) {
            // Xác định khoảng thời gian bắt đầu và kết thúc trong tháng
            LocalDateTime fromDate = from.atDay(1).atStartOfDay();
            LocalDateTime toDate = from.atEndOfMonth().atTime(23, 59, 59);

            // Lấy kết quả từ repository
            List<PrintingLog> result = printingLogRepository.calculateTotalPagesByType(fromDate, toDate);

            // Tạo danh sách loại giấy và số lượng tương ứng
            List<PaperTypeUsage> paperTypes = new ArrayList<>();
            for (PrintingLog doc : result) {
                PageSize paperSize = doc.getPageSize(); // A3, A4
                Integer totalPages = doc.getTotalPages();
                paperTypes.add(new PaperTypeUsage(paperSize, totalPages));
            }

            // Tạo đối tượng ChartValueByType
            String label = from.getMonth().toString() + " " + from.getYear();
            ChartValueByType value = new ChartValueByType(label, paperTypes);
            returnList.add(value);

            // Chuyển sang tháng tiếp theo
            from = from.plusMonths(1);
        }
        return returnList;
    }

    

    @Autowired
    public AdminRepository adminRepository;

    @Override
    public Admin registerAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            throw new RuntimeException("Username đã tồn tại!");
        }
        return adminRepository.save(admin);
    }

}
