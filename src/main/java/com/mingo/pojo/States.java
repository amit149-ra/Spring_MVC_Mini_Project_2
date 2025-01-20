package com.mingo.pojo;

import com.mingo.entiry.CitiesMaster;
import com.mingo.entiry.CountryMaster;

import lombok.Data;

@Data
public class States {
	private int stateId;

	private String stateName;
	
	private CountryMaster country;

	private CitiesMaster city;
}
