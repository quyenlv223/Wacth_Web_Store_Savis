package com.example.smart.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "color", schema = "watch_store")
public class ColorEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "value_color")
    private String valueColor;


    @OneToMany(mappedBy = "colorEntity")
    private List<ProductDetailStatusEntity> productProperties;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueColor() {
        return valueColor;
    }

    public void setValueColor(String valueColor) {
        this.valueColor = valueColor;
    }

    public List<ProductDetailStatusEntity> getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(List<ProductDetailStatusEntity> productProperties) {
        this.productProperties = productProperties;
    }

}
