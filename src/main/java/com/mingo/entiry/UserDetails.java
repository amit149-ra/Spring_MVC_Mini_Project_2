package com.mingo.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "user_details")
@Data
@Entity
public class UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="gender")
	private String gender;
	@Column(name="email")
	private String userEmail;
	@Column(name="c_number")
	private String contactNumber;
	@Column(name="password")
	private String userPassword;
	@Column(name="dob")
	private String userDOB;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="update_date")
	private String upadateDate;
	@Column(name="create_date")
	private String createDate;
	@Column(name="acc_status")
	private String accStatus;
	
}
