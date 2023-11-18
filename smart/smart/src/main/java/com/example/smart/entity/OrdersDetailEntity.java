package com.example.smart.entity;


import com.example.smart.dto.respone.ThongKeDto;

import javax.persistence.*;


@Entity
@Table(name = "ordersdetail", schema = "watch_store", catalog = "")
@NamedNativeQuery(
        name = "thong_ke",
        query =
                "select p.IMAGE_KEY as img, p.NAME as nameProduct, r.NAME as romProduct, c.value_color as colorProduct, pp.PRICE as priceProduct,SUM(o.QUANTITY) as quantityDaBan, a.create_date as dateOrder, (pp.PRICE * o.QUANTITY) as totalPrice from orders a\n" +
                        "inner join ordersdetail o on a.ID = o.ORDER_ID\n" +
                        "inner join product_detail_status pp on o.PRODUCT_DETAIL_STATUS_ID = pp.ID\n" +
                        "inner join rom r on pp.ROM_ID = r.ID\n" +
                        "inner join color c on pp.COLOR_ID = c.ID\n" +
                        "inner join product p on r.PRODUCT_ID = p.ID where o.DELETE_FLAG = false and MONTH(o.CREATE_DATE) = :month and YEAR(o.CREATE_DATE) = :year and a.status = 4 GROUP BY o.PRICE, p.IMAGE_KEY, p.NAME, r.NAME, pp.PRICE,o.QUANTITY,c.value_color, dateOrder, totalPrice limit 10;\n",
        resultSetMapping = "stock_akhir_dto"

        /*name = "thong_ke",
        query =  "select distinct  p.IMAGE_KEY as img, p.NAME as nameProduct, r.NAME as romProduct, c.value_color as colorProduct, pp.PRICE as priceProduct, SUM(o.QUANTITY) as quantityDaBan from orders a\n" +
                "inner join ordersdetail o on a.ID = o.ORDER_ID\n" +
                "inner join property_product pp on o.PRODUCT_PROPERTY_ID = pp.ID\n" +
                "inner join rom r on pp.ROM_ID = r.ID\n" +
                "inner join color c on pp.COLOR_ID = c.ID\n" +
                "inner join product p on r.PRODUCT_ID = p.ID where o.DELETE_FLAG = false and MONTH(o.CREATE_DATE) = :month and YEAR(o.CREATE_DATE) = :year group by o.PRICE limit 10;\n",
        resultSetMapping = "stock_akhir_dto"*/


)
@SqlResultSetMapping(
        name = "stock_akhir_dto",
        classes = @ConstructorResult(
                targetClass = ThongKeDto.class,
                columns = {
                        @ColumnResult(name = "img", type = String.class),
                        @ColumnResult(name = "nameProduct", type = String.class),
                        @ColumnResult(name = "romProduct", type = String.class),
                        @ColumnResult(name = "colorProduct", type = String.class),
                        @ColumnResult(name = "priceProduct", type = String.class),
                        @ColumnResult(name = "quantityDaBan", type = String.class),
                        @ColumnResult(name = "totalPrice", type = String.class),
                        @ColumnResult(name = "dateOrder", type = String.class)
                }
        )
)
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
