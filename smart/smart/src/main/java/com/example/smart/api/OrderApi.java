package com.example.smart.api;

import com.example.smart.dto.request.order.OrderRequest;
import com.example.smart.dto.respone.order.OrderRespone;
import com.example.smart.service.IOrderDetailService;
import com.example.smart.service.IOrderService;
import com.example.smart.until.ConvertUtil;
import com.example.smart.until.ListOrderExcel;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderApi {
    private final IOrderService orderService;

    private final IOrderDetailService detailService;

    private final ConvertUtil convertUtil;


    @Autowired
    private HttpServletResponse response;

    @Autowired
    ListOrderExcel utils;

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest request){
         String a = orderService.addOrder(request);
        if(a.equals("ok")){
            return ResponseEntity.ok().body(request);
        }else {
            return ResponseEntity.badRequest().body(a);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> getorder(@PathVariable("id") String id){
        OrderRespone respone = orderService.findByOrder(id);
        return ResponseEntity.ok().body(respone);
    }


    @PostMapping("edit-order-details")
    public ResponseEntity<?> editDetails(@RequestBody OrderRequest request){
        String respone = orderService.updateOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("confirm-order-sale-person")
    public ResponseEntity<?> confirmSaleOrders(@RequestBody OrderRequest request) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(request.getDeliveryDate().toString());

//        if (convertUtil.strToDate(formatter1.format(formatter.parse(request.getDeliveryDate().toString())), "dd-MM-yyyy").compareTo(new Date()) < 0) {
//            throw new WorldPhoneExp(ConstansErrorCode.VOUCHER_DATE_NOT_PAST);
//        }
        String respone = orderService.confirmOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("confirm-export-order")
    public ResponseEntity<?> confirmExportOrders(@RequestBody OrderRequest request){
        String respone = orderService.exportOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @PostMapping("shipping")
    public ResponseEntity<?> confirmShipping(@RequestBody OrderRequest request){
        String respone = orderService.shippingOrder(request);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }


    @GetMapping("/{id}/{code}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id, @PathVariable("code") String codeOrder){
        String respone = detailService.deleteDetail(id, codeOrder);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @GetMapping("pay-the-bill/{id}")
    public ResponseEntity<?> doneOrder(@PathVariable("id") String id){
        String respone = orderService.doneOrder(id);
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @GetMapping("/cancel-order/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") String id){
        String respone = orderService.deleteOrder(String.valueOf(id));
        if(respone.equals("ok")){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body(respone);
    }

    @GetMapping(value = "/export-list-orders")
    @ResponseBody
    public Object exportList() {
        try {

            List<OrderRespone> list = orderService.findAllOrder();

            Resource resource = new ClassPathResource("excel_template/list_orders.xlsx");
            InputStream ips = resource.getInputStream();

            Workbook wb  = WorkbookFactory.create(ips);
            Sheet sheet = wb.getSheetAt(0);
            Row defaultRow = sheet.getRow(5); // lấy row thứ 5 từ file excel
            CellStyle cellStyle = defaultRow.getRowStyle(); // lấy style từ row gốc
            for (int i = 0; i < list.size(); i++) {
                int rowindex = 5 + i;
                Row row = sheet.getRow(rowindex);
                if(row == null) {
                    row = sheet.createRow(rowindex); //tao
                    row.setRowStyle(cellStyle);
                    for (int j = 0; j < 7; j++) {
                        Cell newCell =  row.createCell(j);
                        newCell.setCellStyle(defaultRow.getCell(j).getCellStyle());
                    }

                }
                row.getCell(0).setCellValue(i + 1);
                row.getCell(1).setCellValue(utils.UtilStatusOrder(list.get(i).getShipping())); //trang thai

                row.getCell(2).setCellValue(list.get(i).getCodeOrder()); //ma HD
                row.getCell(3).setCellValue(list.get(i).getTotalString()); //tong tien hang
                row.getCell(4).setCellValue(list.get(i).getOrderCreate()); //ngay dat don
//                System.out.println("loai don hang : " + list.get(i).getOrderType());
                switch (list.get(i).getOrderType()){
                    case "1":
                        row.getCell(5).setCellValue("Online"); // loai don hang
                        break;
                    case "2":
                        row.getCell(5).setCellValue("Gọi đặt hàng"); // loai don hang
                        break;
                    case "0":
                        row.getCell(5).setCellValue("Tại quầy"); // loai don hang
                        break;
                }
//                System.out.println("phuong thuc thanh toan : " +list.get(i).getTypeOrder());
                switch (list.get(i).getTypeOrder().toString()){
                    case "1":
                        row.getCell(6).setCellValue("Thẻ");// phuong thuc thanh toan
                        break;
                    case "0":
                        row.getCell(6).setCellValue("Tiền mặt");// phuong thuc thanh toan
                        break;
                }

            }
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename="+ "Danh_sach_cac_don_hang"+ ".xlsx");
            wb.write(out);
            wb.close();
            out.close();

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("false");
        }
    }
}