package com.example.smart.dto.respone.promotion;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PromotionResponseDTO {

    private String id;

    private String name;

    private Long discount;

    private Date startDate;

    private Date endDate;

    private String description;

    private String typeDiscount;

    private Boolean status;

    private String note;

    private List<String> idProduct;

}
