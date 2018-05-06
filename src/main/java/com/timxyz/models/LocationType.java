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
public class LocationType extends BaseModel {
    private String name;
    private String description;
    private Collection<Location> locations;

    public LocationType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public LocationType() {
    }

    @Basic
    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    @Size(max = 255) 
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }
}
