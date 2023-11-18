package com.example.smart.until;

import org.springframework.stereotype.Service;

@Service
public class ListOrderExcel {
    public String UtilStatusOrder(String data){
        String status = "";
        System.out.println(data);
        switch (data){
            case "-1" :
                status= "Hủy"; //trang thai
                break;
            case "0" :
                status= "Chờ xác nhận"; //trang thai
                break;
            case "1" :
                status="Chờ xuất hàng"; //trang thai
                break;
            case "2" :
                status="Chờ giao hàng"; //trang thai
                break;
            case "3" :
                status= "Đang giao hàng"; //trang thai
                break;
            case "4" :
                status="Hoàn thành"; //trang thai
                break;
        }
        return status;
    }
}
