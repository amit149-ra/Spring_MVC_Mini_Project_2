package com.mingo.pojo;

import com.mingo.entiry.StateMaster;
import lombok.Data;

@Data
public class Cities {

	private int cityId;
	
	private String cityName;
	
	private StateMaster state;
}
