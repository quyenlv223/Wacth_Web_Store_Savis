package com.example.smart.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders_history", schema = "watch_store", catalog = "")
public class OrderHistoryEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACTION")
    private String action; // Hanh dong

    @Column(name = "STATUS")
    private String status; // Trang thai don hang

    @Column(name = "ORDERS_ID")
    private Long orders_id; // id don hang

    @Column(name = "NOTE")
    private String note; // id don hang
}
