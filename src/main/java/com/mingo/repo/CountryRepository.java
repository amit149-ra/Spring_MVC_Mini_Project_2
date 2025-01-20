package com.mingo.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mingo.entiry.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Serializable> {

}
