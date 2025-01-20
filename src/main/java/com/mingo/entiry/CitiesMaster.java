package com.mingo.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "cities_master")
@Data
@Entity
public class CitiesMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@ManyToOne
	@JoinColumn(name="STATE_ID",nullable = false)
	private StateMaster state;
	

}
