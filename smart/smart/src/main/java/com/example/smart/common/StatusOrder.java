package com.example.smart.common;


public enum StatusOrder {
    CHO_XAC_NHAN(0),
    CHO_XUAT_HANG(1),
    CHO_GIAO_HANG(2),
    DANG_GIAO_HANG(3),
    HOAN_THANH(4),
    HUY(-1);

    private final int index;

    StatusOrder(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
