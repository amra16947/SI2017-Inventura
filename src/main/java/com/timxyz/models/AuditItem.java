package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AuditItem extends BaseModel {
    private boolean present;
    private boolean skuCorrect;
    private String note;
    private Item item;
    private Audit audit;
    private Status status;

    public AuditItem(Item item, Audit audit) {
        this.item = item;
        this.audit = audit;
    }

    public AuditItem() {
    }

    @Basic
    @Column(name = "present", nullable = false)
    @NotNull
    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Basic
    @Column(name = "skuCorrect", nullable = false)
    @NotNull
    public Boolean getSkuCorrect() {
        return skuCorrect;
    }

    public void setSkuCorrect(Boolean skuCorrect) {
        this.skuCorrect = skuCorrect;
    }

    @Basic
    @Column(name = "note")
    @Size(max = 500)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "auditId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
