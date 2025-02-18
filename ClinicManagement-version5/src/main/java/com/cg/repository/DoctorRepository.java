package com.cg.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	public List<Doctor> findBySpecialization(String specialization);
	
	public Optional<Doctor> findByDname(String dname);

}
