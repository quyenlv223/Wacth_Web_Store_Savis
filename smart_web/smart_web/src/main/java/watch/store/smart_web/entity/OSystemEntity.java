package watch.store.smart_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "osystem", schema = "watch_store", catalog = "")
public class OSystemEntity extends BaseEntity{ // Hệ điều hành

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;



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

}
