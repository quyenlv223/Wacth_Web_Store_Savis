package com.example.smart.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "watch_store", catalog = "")
public class ProductEntity extends BaseEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "IMAGE_KEY")
    private String image_key;

    @Basic
    @Column(name = "STATUS")
    private String status;

    @Basic
    @Column(name = "NOTE")
    private String note;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private CategoryEntity category;

    @OneToMany(mappedBy = "productEntity")
    private List<ImageEntity> imageEntities;

    @OneToMany(mappedBy = "productEntity")
    private List<RomEntity> romEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_key() {
        return image_key;
    }

    public void setImage_key(String image_key) {
        this.image_key = image_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RomEntity> getRomEntities() {
        return romEntities;
    }

    public void setRomEntities(List<RomEntity> romEntities) {
        this.romEntities = romEntities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(image_key, that.image_key) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(category, that.category) && Objects.equals(imageEntities, that.imageEntities) && Objects.equals(romEntities, that.romEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image_key, status, note, category, imageEntities, romEntities);
    }
}
