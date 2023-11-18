package com.example.smart.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "voucher", schema = "watch_store", catalog = "")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoucherEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic
    @Column(name = "DISCOUNT")
    private long discount;
    @Basic
    @Column(name = "START_DATE")
    private Date startDate;
    @Basic
    @Column(name = "END_DATE")
    private Date endDate;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "TYPE_DISCOUNT")
    private String typeDiscount;
    // số tiền đã dùng tối thiểu của người dùng có thể sử dụng mã giảm giá
    @Basic
    @Column(name = "TYPE_DISCOUNT_MONEY_MIN")
    private Long typeDiscountMoneyMin;
    @Basic
    @Column(name = "MIN_AMOUNT")
    private int minAmount;
    @Basic
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;

    @Basic
    @Column(name = "accompany_promo")
    private String accompanyPromo;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherEntity that = (VoucherEntity) o;
        return quantity == that.quantity && discount == that.discount && minAmount == that.minAmount && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(description, that.description) && Objects.equals(categoryId, that.categoryId) && Objects.equals(status, that.status) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, quantity, discount, startDate, endDate, description, minAmount, categoryId, status, note);
    }

}
