package watch.store.smart_web.entity;

import javax.persistence.*;


@Entity
@Table(name = "series", schema = "watch_store", catalog = "")
public class SeriesEntity extends BaseEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "STATUS")
    private String status;

    @Basic
    @Column(name = "VALUE")
    private String value;


    @Basic
    @Column(name = "PRODUCT_DETAIL_ID")
    private Long  productDetailID;

    @Basic
    @Column(name = "ORDER_DETAIL_STATUS_ID")
    private Long orderDetailId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getProductDetailID() {
        return productDetailID;
    }

    public void setProductDetailID(Long propertyProductId) {
        this.productDetailID = propertyProductId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
}
