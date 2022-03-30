package com.huloteam.kbe.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "kbestorage")
@NamedQuery(name = "ProductEntity.findOneProduct", query = "SELECT p FROM ProductEntity p WHERE p.name = :name")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "provider")
    private String provider;
    @Basic
    @Column(name = "providerprice")
    private Integer providerprice;
    @Basic
    @Column(name = "storedsince")
    private Timestamp storedsince;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getProviderprice() {
        return providerprice;
    }

    public void setProviderprice(Integer providerprice) {
        this.providerprice = providerprice;
    }

    public Timestamp getStoredsince() {
        return storedsince;
    }

    public void setStoredsince(Timestamp storedsince) {
        this.storedsince = storedsince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(provider, that.provider) && Objects.equals(providerprice, that.providerprice) && Objects.equals(storedsince, that.storedsince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, provider, providerprice, storedsince);
    }

}
