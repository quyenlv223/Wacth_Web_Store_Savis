package watch.store.smart_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "image", schema = "watch_store", catalog = "")
public class ImageEntity extends BaseEntity{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_image")
    private String link_image;

    @Basic
    @Column(name = "PRODUCT_ID")
    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLing_image() {
        return link_image;
    }

    public void setLing_image(String ling_image) {
        this.link_image = ling_image;
    }




}
