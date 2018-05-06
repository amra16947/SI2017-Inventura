package com.timxyz.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Item extends BaseModel {
    private String skuNumber;
    private String name;
    private String unitOfMeasurement;
    private String purchasedBy;
    private String personResponsible;
    private Date dateOfPurchase;
    private BigDecimal value;
    private Collection<AuditItem> auditItems;
    private Category category;
    private Location location;
    private Collection<PastAuditItem> pastAuditItems;

    public Item(String skuNumber, String name, String unitOfMeasurement, String purchasedBy, String personResponsible, Date dateOfPurchase, BigDecimal value, Category category, Location location) {
        this.skuNumber = skuNumber;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.purchasedBy = purchasedBy;
        this.personResponsible = personResponsible;
        this.dateOfPurchase = dateOfPurchase;
        this.value = value;
        this.category = category;
        this.location = location;
    }

    public Item() {
    }

    @Basic
    @Column(name = "skuNumber", nullable = false)
    @Size(min = 1, max = 1000) @NotNull
    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unitOfMeasurement", nullable = false)
    @Size(min = 1, max = 20) @NotNull
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Basic
    @Column(name = "purchasedBy", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Basic
    @Column(name = "personResponsible", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    @Basic
    @Column(name = "dateOfPurchase", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Basic
    @Column(name = "value", nullable = false)
    @Min(0) 
    @Max((long) 9999999999999.99) @NotNull
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
