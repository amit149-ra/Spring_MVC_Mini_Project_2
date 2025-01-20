package com.mingo.pojo;

import java.util.List;

import com.mingo.entiry.StateMaster;

import lombok.Data;

@Data
public class Country {

	private int countryId;
	private String countryName;
	private int countryCode;
	private List<StateMaster> stateList;
}
