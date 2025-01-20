package com.mingo.entiry;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "states_master")
@Data
@Entity
public class StateMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "STATE_NAME")
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID",nullable = false)
	private CountryMaster country;
	
	@OneToMany(mappedBy = "cityId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<CitiesMaster> city;
	
}
