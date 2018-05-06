package com.timxyz.controllers.forms.Item;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import java.util.Date;

/**
 * Created by Dario on 5/9/2017.
 */
public class ItemCreateForm {

    private Long Id;

    @NotNull
    private String skuNumber;

    @Size(min = 4, max = 255) @NotNull
    private String name;

    @Size(min = 1, max = 4) @NotNull
    private String unitOfMeasurement;

    @Size(min = 4, max = 255) @NotNull
    private String purchasedBy;

    @Size(min = 4, max = 255) @NotNull
    private String personResponsible;

    @NotNull
    private Date dateOfPurchase;

    @Min(0) 
    @Max((long) 9999999999999.99) @NotNull
    private BigDecimal value;

    @NotNull
    private Long categoryID;

    @NotNull
    private Long locationID;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Long getLocationID() {
        return locationID;
    }

    public void setLocationID(Long locationID) {
        this.locationID = locationID;
    }
}
