package com.se.ssps.server.dto;

// import com.se.ssps.server.entity.Printer;
// import com.se.ssps.server.entity.configuration.Room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrinterStudentDto {
    private Boolean status; // Trạng thái máy in
    private String roomName; // Tên phòng máy in
    private String id;

    // Constructor nếu Lombok không được dùng
    public PrinterStudentDto(Boolean status, String roomName, String id) {
        this.status = status;
        this.roomName = roomName;
        this.id = id;

    }
}
