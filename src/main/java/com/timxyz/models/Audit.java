package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Audit extends BaseModel {
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private boolean finished;
    private Account account;
    private Location location;
    private Collection<AuditItem> auditItems;
    private Collection<PastAudit> pastAudits;

    public Audit(String name, Account account, Location location) {
        this.name = name;
        this.account = account;
        this.location = location;
    }

    public Audit() {
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

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "finished", nullable = false)
    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "audit", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @OneToMany(mappedBy = "audit")
    @JsonIgnore
    public Collection<PastAudit> getPastAudits() {
        return pastAudits;
    }

    public void setPastAudits(Collection<PastAudit> pastAudits) {
        this.pastAudits = pastAudits;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
