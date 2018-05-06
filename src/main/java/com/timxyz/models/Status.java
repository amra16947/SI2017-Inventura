package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Status extends BaseModel {
    private String name;
    private Collection<AuditItem> auditItems;
    private Collection<PastAuditItem> pastAuditItems;

    public Status() {
    }

    public Status(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 255)  @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @OneToMany(mappedBy = "status")
    @JsonIgnore
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
