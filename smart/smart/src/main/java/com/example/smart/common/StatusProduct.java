package com.example.smart.common;

public enum StatusProduct {
    DANG_KINH_DOANH("ON"),
    NGUNG_KINH_DOANH("OFF");

    private final String status;

    StatusProduct(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
