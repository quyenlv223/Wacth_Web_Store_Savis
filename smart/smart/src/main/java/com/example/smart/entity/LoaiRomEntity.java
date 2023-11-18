package com.example.smart.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "rom_detail", schema = "watch_store", catalog = "")
public class LoaiRomEntity extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "LoaiRomEntity")
    private List<RomValueEntity> romValueEntities;

    public LoaiRomEntity(Long id, String name, List<RomValueEntity> romValueEntities) {
        this.id = id;
        this.name = name;
        this.romValueEntities = romValueEntities;
    }

    public LoaiRomEntity() {
    }

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

    public List<RomValueEntity> getRomValueEntities() {
        return romValueEntities;
    }

    public void setRomValueEntities(List<RomValueEntity> romValueEntities) {
        this.romValueEntities = romValueEntities;
    }
}
