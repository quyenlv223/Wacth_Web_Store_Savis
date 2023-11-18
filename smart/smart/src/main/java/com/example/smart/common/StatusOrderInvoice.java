package com.example.smart.common;


public enum StatusOrderInvoice {
    HUY(0),
    DOI_DUYET(-1),
    DA_DAT(1),
    DA_NHAN(2),
    GIAO_THIEU(3);


    private final int index;

    StatusOrderInvoice(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
