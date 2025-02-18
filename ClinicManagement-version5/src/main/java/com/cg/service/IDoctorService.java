package com.cg.service;
import java.util.List;
import java.util.Optional;

import com.cg.dto.DoctorAppointmentsDTO;
import com.cg.entity.Doctor;
import com.cg.exception.DoctorNotFoundException;
public interface IDoctorService {
	
	public List<Doctor> findAllDoctors();
	public Doctor findDoctorById(Integer id) throws DoctorNotFoundException;
	public String deleteDoctorById(Integer id);
	public Doctor updateDoctorById(Integer id,Doctor doctor) throws DoctorNotFoundException;
	public Doctor createDoctor(Doctor doctor);
	public List<Doctor> findAllDoctorsBySpecialization(String specialization);
	public Optional<Doctor> findDoctorByName(String dname);
	public DoctorAppointmentsDTO findAppointmentByDoctorNameAndStatus(String dname,String status);
	
}
