package com.timxyz.controllers.forms.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryCreateForm {

    @Size(min = 4, max = 500) @NotNull
    private String name;
    private Long parentId;

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
}
