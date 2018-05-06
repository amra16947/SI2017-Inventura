package com.timxyz.controllers.forms.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LocationCreateForm {
    
    @Size(min = 4, max = 500) @NotNull
    private String name;

    private Long parentId;

    @NotNull
    private Long typeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
