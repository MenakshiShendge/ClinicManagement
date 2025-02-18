package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientAppointmentDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Appointment;
import com.cg.entity.Patient;
import com.cg.exception.DoctorNotFoundException;
import com.cg.exception.PatientNotFoundException;
import com.cg.repository.AppointmentRepository;
import com.cg.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public List<Patient> findAllPatient() {
		return patientRepository.findAll();
	}

	@Override
	public Patient findPatientById(Integer id) throws PatientNotFoundException {
		return patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found for id : " + id));
	}

	@Override
	public Patient createPatient(Patient patient) {
		
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(Integer id, Patient patient) throws PatientNotFoundException{
		Patient existingpatient=patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("patient not found for id : " + id));
		
		existingpatient.setPname(patient.getPname());
		existingpatient.setAge(patient.getAge());
		existingpatient.setGender(patient.getGender());
		existingpatient.setPhone(patient.getPhone());
		existingpatient.setEmail(patient.getEmail());
		existingpatient.setAddress(patient.getAddress());
		
		return patientRepository.save(existingpatient);
	}

	@Override
	public String deletePatientById(Integer id) {
		Patient p=patientRepository.findById(id).get();
		if(p==null) {
			return "patient not found";
		}
		patientRepository.deleteById(id);
		return "patient deleted";
	}

	@Override
	public Optional<Patient> findAllPatientByName(String pname) {
		
		return patientRepository.findByPname(pname);
	}

	@Override
	public PatientAppointmentDTO findAppointmentsByPatientNameAndStatus(String pname, String status) {
		Optional<Patient> p=patientRepository.findByPname(pname);
		if(!p.isPresent()) {
			throw new RuntimeException("Pateint not found");
		}
		PatientDTO pdto=new PatientDTO();
		pdto=pdto.fromEntity(p.get());
		
		List<Appointment> alist=appointmentRepository.findAppointmentsByPatientIdAndStatus(pdto.getPatientId(), status);
		List<AppointmentDTO> adtolist=new ArrayList<>();
		for(Appointment a:alist) {
			AppointmentDTO adto=new AppointmentDTO();
			adto.setAppointmentId(a.getAppointmentId());
			adto.setAppointmentDate(a.getAppointmentDate());
			adto.setStatus(a.getStatus());
			
			DoctorDTO ddto=new DoctorDTO();
			ddto.setDoctorId(a.getDoctor().getDoctorId());
			ddto.setDname(a.getDoctor().getDname());
			ddto.setSpecialization(a.getDoctor().getSpecialization());
			
			adto.setDoctorDTO(ddto);
			adtolist.add(adto);
		}
		PatientAppointmentDTO patientAppointmentDTO=new PatientAppointmentDTO();
		patientAppointmentDTO.setPatientDTO(pdto);
		patientAppointmentDTO.setAppointmentDTOs(adtolist);
		return patientAppointmentDTO;
	}
	
	

}
