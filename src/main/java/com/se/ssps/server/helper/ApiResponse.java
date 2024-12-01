package com.se.ssps.server.helper;


public class ApiResponse {
    private String status; // Trạng thái (success, error, ...)
    private String message; // Thông báo
    private Object data; // Dữ liệu bổ sung (nếu cần)

    public ApiResponse(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String status, String message) {
        this(status, message, null);
    }

    // Getter & Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
