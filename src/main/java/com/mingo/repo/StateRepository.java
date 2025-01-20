package com.mingo.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mingo.entiry.StateMaster;

@Repository
public interface StateRepository extends JpaRepository<StateMaster, Serializable> {
	public List<StateMaster> findByCountryCountryId(Integer countryId);
}
