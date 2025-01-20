package com.mingo.pojo;

import lombok.Data;

@Data
public class User {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String userEmail;
	private String contactNumber;
	private String userPassword;
	private String userDOB;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private String upadateDate;
	private String createDate;
	private String accStatus;
}
