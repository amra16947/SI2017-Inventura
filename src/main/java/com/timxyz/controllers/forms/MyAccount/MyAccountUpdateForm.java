package com.timxyz.controllers.forms.MyAccount;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MyAccountUpdateForm {
	@Size(min = 8)
	private String newPassword;
	
	@Size(min = 8) @NotNull
	private String currentPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public boolean isPasswordUpdated() {
	    return newPassword != null && !newPassword.isEmpty();
    }
}
