package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles") // "role" is a reserved word in postgres
public class Role extends BaseModel {
    private String name;
    private Collection<Account> accounts;

    public Role() {
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Size(max = 45) @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
}
