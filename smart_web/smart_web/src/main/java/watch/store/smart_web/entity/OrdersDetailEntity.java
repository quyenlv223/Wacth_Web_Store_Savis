package watch.store.smart_web.entity;

import javax.persistence.*;


@Entity
@Table(name = "ordersdetail", schema = "watch_store", catalog = "")
public class OrdersDetailEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private OrdersEntity ordersEntity;

    @Basic
    @Column(name = "PRODUCT_DETAIL_STATUS_ID")
    private Long idPropertyProduct;

    @Basic
    @Column(name = "QUANTITY")
    private Long quantity;

    @Basic
    @Column(name = "PRICE")
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public Long getIdPropertyProduct() {
        return idPropertyProduct;
    }

    public void setIdPropertyProduct(Long idPropertyProduct) {
        this.idPropertyProduct = idPropertyProduct;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
