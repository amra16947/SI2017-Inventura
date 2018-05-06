package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AccessLog extends BaseModel {
    private Date date;
    private String type;
    private String description;
    private String tableName;
    private Long objectId;
    private Account account;

    public AccessLog() {
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type", nullable = false)
    @Size(max = 45) @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "description", nullable = false)
    @Size (max = 500) @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "tableName", nullable = false)
    @Size(max = 45) @NotNull
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "objectId", nullable = false)
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }
}
