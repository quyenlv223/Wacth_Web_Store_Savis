package com.example.smart.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_detail_status", schema = "watch_store", catalog = "")
public class ProductDetailStatusEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "PRICE")
    private long price;


    @Basic
    @Column(name = "STATUS")
    private String status;


    @Basic
    @Column(name = "QUANTITY")
    private long quantity;

    @Basic
    @Column(name = "PRICE_PROMOTION")
    private long pricePromotion;

    @Basic
    @Column(name = "PROMOTION_ID")
    private long promotionId;


    @ManyToOne
    @JoinColumn(name = "ROM_ID", referencedColumnName = "ID")
    private RomEntity romEntity;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID", referencedColumnName = "ID")
    private ColorEntity colorEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public RomEntity getRomEntity() {
        return romEntity;
    }

    public void setRomEntity(RomEntity romEntity) {
        this.romEntity = romEntity;
    }

    public ColorEntity getColorEntity()  {
        return colorEntity;
    }

    public void setColorEntity(ColorEntity colorEntity) {
        this.colorEntity = colorEntity;
    }

    public long getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(long pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }
}
