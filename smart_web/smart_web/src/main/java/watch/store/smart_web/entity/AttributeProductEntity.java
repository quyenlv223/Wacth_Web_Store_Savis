package watch.store.smart_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "attribute_product", schema = "watch_store", catalog = "")
public class AttributeProductEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "OSYSTEM_ID")
    private Long osystemId; // he dieu hanh

    @Basic
    @Column(name = "SCREEN_ID")
    private Long screenId; // man hinh

    @Basic
    @Column(name = "CHIP_ID")
    private Long chipId;


    @Basic
    @Column(name = "PIN_ID")
    private Long pinId;

    @Basic
    @Column(name = "RAM_ID")
    private Long ramID;

    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOsystemId() {
        return osystemId;
    }

    public void setOsystemId(Long osystemId) {
        this.osystemId = osystemId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }


    public Long getChipId() {
        return chipId;
    }

    public void setChipId(Long chipId) {
        this.chipId = chipId;
    }

    public Long getPinId() {
        return pinId;
    }

    public void setPinId(Long pinId) {
        this.pinId = pinId;
    }

    public Long getRamID() {
        return ramID;
    }

    public void setRamID(Long ramID) {
        this.ramID = ramID;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
