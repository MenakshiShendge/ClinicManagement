package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Patient;
import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	public Optional<Patient> findByPname(String pname);

}
