/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timxyz.controllers.forms.Location;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ena
 */
public class LocationUpdateForm {
    
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
