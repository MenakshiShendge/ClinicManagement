package com.cg.service;
import java.util.List;
import java.util.Optional;

import com.cg.dto.PatientAppointmentDTO;
import com.cg.entity.Patient;
import com.cg.exception.PatientNotFoundException;
public interface IPatientService {

	public List<Patient> findAllPatient();
	public Patient findPatientById(Integer id) throws PatientNotFoundException;
	public Patient createPatient(Patient patient);
	public Patient updatePatient(Integer id,Patient patient) throws PatientNotFoundException;
	public String deletePatientById(Integer id);
	public Optional<Patient> findAllPatientByName(String pname);
	public PatientAppointmentDTO findAppointmentsByPatientNameAndStatus(String pname,String status);
}
