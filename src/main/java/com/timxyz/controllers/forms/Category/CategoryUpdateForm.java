package com.timxyz.controllers.forms.Category;

import com.timxyz.models.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by smusic on 5/24/17.
 */
public class CategoryUpdateForm {

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
