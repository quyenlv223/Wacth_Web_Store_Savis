package watch.store.smart_web.entity;

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
    @Column(name = "ROM_ID")
    private Long romId;

    @Basic
    @Column(name = "COLOR_ID")
    private Long colorId;

    @Basic
    @Column(name = "PRICE_PROMOTION")
    private long pricePromotion;

    @Basic
    @Column(name = "PROMOTION_ID")
    private Long promotionId;

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

    public Long getRomId() {
        return romId;
    }

    public void setRomId(Long romId) {
        this.romId = romId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
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
