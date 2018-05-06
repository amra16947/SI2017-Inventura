package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class PastAudit extends BaseModel {
    private Date createdAt;
    private Audit audit;
    private Collection<PastAuditItem> pastAuditItems;

    public PastAudit() {
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @OneToMany(mappedBy = "pastAudit")
    @JsonIgnore
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
