/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timxyz.controllers.forms.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ena
 */
public class StatusUpdateForm {
    
    @Size(min = 4, max = 500) @NotNull
    private String name;
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
