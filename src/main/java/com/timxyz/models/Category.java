package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category extends BaseModel {
    private String name;
    private Category parent;
    private Collection<Category> children;
    private Collection<Item> items;

    public Category() {
    }

    public Category(String name, Category parent) {
        this.setName(name);
        this.setParent(parent);
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

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    public Collection<Category> getChildren() {
        return children;
    }

    public void setChildren(Collection<Category> children) {
        this.children = children;
    }

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
