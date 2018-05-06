package com.timxyz.controllers.forms.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Dario on 5/6/2017.
 */
public class StatusCreateForm {
    @Size(min = 4, max = 255) @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
