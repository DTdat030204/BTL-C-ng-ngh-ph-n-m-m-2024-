package com.se.ssps.server.service.user;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.ssps.server.entity.user.Admin;
//import com.se.ssps.server.repository.AdminRepository;
//import com.se.ssps.server.dto.PrinterDto;
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
import com.se.ssps.server.stat.ChartValue;
import com.se.ssps.server.stat.ChartValueByType;

@Service
public interface AdminService {
    // ================================================================================
    // Thao tác đối với máy in
    public List<Printer> findAllPrinter();

    Printer addPrinter(String roomName, String buildingName, Printer newPrinter);

    public Printer findPrinterById(String id);

    public Map<String, Boolean> deletePrinter(Integer id);

    public Map<String, Boolean> updatePrinter(Printer newPrinter, String roomId);

    //public List<PrinterDto> findAllPrinterStat();

    // =====================================================================================
    // ================================================================================
    // Thao tác đối với cơ sởx
    public List<Campus> findAllCampus();

    public Campus addCampus(Campus newCampus);

    public Map<String, Boolean> deleteCampus(Integer id);

    // ================================================================================
    // Thao tác đối với tòa
    public List<Building> findAllBuilding();

    // public Building addBuilding(Integer campus_id, Building newBuilding);
    public Building addBuilding(String campusName, Building newBuilding);

    public Map<String, Boolean> deleteBuilding(Integer id);

    // ================================================================================
    // Thao tác đối với phòng
    public List<Room> findAllRoom();

    // public Room addRoom(Integer building_id, Room newRoom);
    public Room addRoom(String buildingName, Room room);

    public Map<String, Boolean> deleteRoom(String id);

    // =====================================================================================
    // Danh sách file được cho phép
    public List<FileType> findAllType();

    public FileType addType(FileType fileType);

    public void deleteType(Integer fileTypeId);
    // ================================================================================
    // ================================================================================

    public MaxFileSize setMaxFileSize(double maxFileSize);

    // public PageUnitPrice setPagePrice(Integer pagePrice);
    public PageUnitPrice setPagePrice(Integer pagePrice, String fileSize);

    // =====================================================================================
    // ================================================================================
    // Thao tác đối với xem lịch sử
    // lịch sử mua hàng
    public List<PrintingLog> findAllPrintingLogs();

    // public boolean addPrintingLog(PrintingLog newPrintingLog);

    // lịch sử thanh toán

    public List<PaymentLog> findAllPaymentLog();

    // public boolean addPaymentLog(PaymentLog paymentLog);

    // ================================================================================
    // =====================================================================================
    // Thao tác đối với thêm lịch cấp phát trang
    public List<PageAllocation> findAllPageAllocations();

    public PageAllocation addPageAllocation(PageAllocation newPageAllocation);

    public boolean deletePageAllocation(Integer id);

    // ================================================================================
    // ================================================================================
    public Config getAllConfig();

    // ================================================================================
    // ================================================================================
    public List<ChartValue> totalSquare(YearMonth from, YearMonth to);

    public List<ChartValue> printingRequest(YearMonth from, YearMonth to);

    public List<ChartValue> pageSizeByMonth(YearMonth from, YearMonth to);

    public List<ChartValue> profitByMonth(YearMonth from, YearMonth to);

    public Admin registerAdmin(Admin admin);

    public Admin findAdminByUsername(String username);

    public List<ChartValue> profitByMonthByPrinter(YearMonth from, YearMonth to, String printerId);
    
    public List<ChartValueByType> paperUsageByMonth(YearMonth from, YearMonth to);

}
