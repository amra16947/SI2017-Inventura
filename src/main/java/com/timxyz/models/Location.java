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
public class Location extends BaseModel {
    private String name;
    private Collection<Audit> audits;
    private Collection<Item> items;
    private Location parent;
    private Collection<Location> children;
    private LocationType type;

    public Location() {
    }

    public Location(String name, Location parent, LocationType type) {
        this.setName(name);
        this.setParent(parent);
        this.setType(type);
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 500) @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    public Collection<Audit> getAudits() {
        return audits;
    }

    public void setAudits(Collection<Audit> audits) {
        this.audits = audits;
    }

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    public Collection<Location> getChildren() {
        return children;
    }

    public void setChildren(Collection<Location> children) {
        this.children = children;
    }

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id", nullable = false)
    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }
}
