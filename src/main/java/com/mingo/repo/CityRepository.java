package com.mingo.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mingo.entiry.CitiesMaster;

@Repository
public interface CityRepository extends JpaRepository<CitiesMaster,Serializable > {

	public List<CitiesMaster> findByStateStateId(Integer stateId);
}
