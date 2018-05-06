package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PastAuditItem extends BaseModel {
    private boolean present;
    private boolean skuCorrect;
    private String note;
    private Item item;
    private PastAudit pastAudit;
    private Status status;

    public PastAuditItem() {
    }

    @Basic
    @Column(name = "present", nullable = false)
    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Basic
    @Column(name = "skuCorrect", nullable = false)
    public Boolean getSkuCorrect() {
        return skuCorrect;
    }

    public void setSkuCorrect(Boolean skuCorrect) {
        this.skuCorrect = skuCorrect;
    }
    
    @Basic
    @Column(name = "note")
    @Size(max = 500) @NotNull
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "pastAuditId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public PastAudit getPastAudit() {
        return pastAudit;
    }

    public void setPastAudit(PastAudit pastAudit) {
        this.pastAudit = pastAudit;
    }

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    @JsonIgnore
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
