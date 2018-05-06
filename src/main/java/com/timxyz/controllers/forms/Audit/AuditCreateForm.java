package com.timxyz.controllers.forms.Audit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by smusic on 5/28/17.
 */
public class AuditCreateForm {

    @Size(min = 4, max = 255) @NotNull
    private String name;

    private Long locationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
