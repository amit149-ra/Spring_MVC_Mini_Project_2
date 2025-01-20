package com.mingo.entiry;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "country_master")
@Data
@Entity
public class CountryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	@Column(name = "COUNTRY_CODE")
	private Integer countryCode;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StateMaster> stateList;
}