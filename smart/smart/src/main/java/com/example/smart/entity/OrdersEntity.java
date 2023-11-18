package com.example.smart.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
@Table(name = "orders", schema = "watch_store", catalog = "")
public class OrdersEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    private CustomerEntity customerEntity;

    @Basic
    @Column(name = "STAFF_ID")
    private Long  staffEntity;

    @Basic
    @Column(name = "NOTE")
    private String note;

    @Basic
    @Column(name = "TOTAL_MONEY")
    private Long totalMoney;


    @Basic
    @Column(name = "VOUCHER_ID")
    private Long voucherID;

    @ManyToOne
    @JoinColumn(name = "PROMOTION_ID", referencedColumnName = "ID")
    private PromotionEnity promotionEnity;

    @Basic
    @Column(name = "ADDRESS")
    private String address;

    @Basic
    @Column(name = "CODE_ORDER")
    private String codeOrder;

    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NAME_SHIP")
    private String nameShip;
    @Basic
    @Column(name = "PHONE_SHIP")
    private String phoneShip;
    @Basic
    @Column(name = "NOTE_SHIP")
    private String noteShip;


    @Basic
    @Column(name = "STATUS_PAY")
    private Integer statusPay;

    @Basic
    @Column(name = "TYPE_ORDER")
    private Integer typeOrder;

    @OneToMany(mappedBy = "ordersEntity")
    private List<OrdersDetailEntity> ordersDetailEntities;

    @Basic
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;


    public Integer getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(Integer typeOrder) {
        this.typeOrder = typeOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Long getStaffEntity() {
        return staffEntity;
    }

    public void setStaffEntity(Long staffEntity) {
        this.staffEntity = staffEntity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(Long voucherID) {
        this.voucherID = voucherID;
    }

    public PromotionEnity getPromotionEnity() {
        return promotionEnity;
    }

    public void setPromotionEnity(PromotionEnity promotionEnity) {
        this.promotionEnity = promotionEnity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrdersDetailEntity> getOrdersDetailEntities() {
        return ordersDetailEntities;
    }

    public void setOrdersDetailEntities(List<OrdersDetailEntity> ordersDetailEntities) {
        this.ordersDetailEntities = ordersDetailEntities;
    }

    public Integer getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(Integer statusPay) {
        this.statusPay = statusPay;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getNameShip() {
        return nameShip;
    }

    public void setNameShip(String namShip) {
        this.nameShip = namShip;
    }

    public String getPhoneShip() {
        return phoneShip;
    }

    public void setPhoneShip(String phoneShip) {
        this.phoneShip = phoneShip;
    }

    public String getNoteShip() {
        return noteShip;
    }

    public void setNoteShip(String noteShip) {
        this.noteShip = noteShip;
    }
}
