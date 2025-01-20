package com.mingo.pojo;

import lombok.Data;

@Data
public class UnlockAccount {

	private String email;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
}
