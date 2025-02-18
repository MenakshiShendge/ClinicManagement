package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorAppointmentsDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Appointment;
import com.cg.entity.Doctor;
import com.cg.exception.DoctorNotFoundException;
import com.cg.repository.AppointmentRepository;
import com.cg.repository.DoctorRepository;

@Service
public class DoctorService implements IDoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public List<Doctor> findAllDoctors() {
		
		return doctorRepository.findAll();
	}

	@Override
	public Doctor findDoctorById(Integer id) throws DoctorNotFoundException {
		
		return doctorRepository.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor not found for id : " + id));
	}

	@Override
	public String deleteDoctorById(Integer id) {
		Doctor d= doctorRepository.findById(id).get();
		if(d==null) {
			return "doctor not found";
		}
		doctorRepository.deleteById(id);
		return "doctor deleted";
	}

	@Override
	public Doctor updateDoctorById(Integer id, Doctor doctor) throws DoctorNotFoundException {
		Doctor existingdoctor=doctorRepository.findById(id)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor not found for id : " + id));
		
		existingdoctor.setDname(doctor.getDname());
		existingdoctor.setSpecialization(doctor.getSpecialization());
		existingdoctor.setPhone(doctor.getPhone());
		existingdoctor.setEmail(doctor.getEmail());
		existingdoctor.setAddress(doctor.getAddress());
		existingdoctor.setConsultationFees(doctor.getConsultationFees());
		
		return doctorRepository.save(existingdoctor);
	}

	@Override
	public Doctor createDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> findAllDoctorsBySpecialization(String specialization) {
	
		return doctorRepository.findBySpecialization(specialization);
	}

	@Override
	public Optional<Doctor> findDoctorByName(String dname) {
		
		return doctorRepository.findByDname(dname);
	}

	@Override
	public DoctorAppointmentsDTO findAppointmentByDoctorNameAndStatus(String dname, String status) {
		Optional<Doctor> d=doctorRepository.findByDname(dname);
		if(!d.isPresent()) {
			throw new RuntimeException("Doctor not found by name");
		}
		DoctorDTO ddto=new DoctorDTO();
		ddto=ddto.fromEntity(d.get());
		
		List<Appointment> alist=appointmentRepository.findAppointmentsByDocterIdAndStatus(ddto.getDoctorId(), status);
		List<AppointmentDTO> adtolist=new ArrayList<>();
		for(Appointment a:alist) {
			AppointmentDTO adto=new AppointmentDTO();
			adto.setAppointmentId(a.getAppointmentId());
			adto.setAppointmentDate(a.getAppointmentDate());
			adto.setStatus(a.getStatus());
			
			PatientDTO pdto=new PatientDTO();
			pdto.setPatientId(a.getPatient().getPatientId());
			pdto.setPname(a.getPatient().getPname());
			
			adto.setPatientDTO(pdto);
			adtolist.add(adto);
		}
		
		DoctorAppointmentsDTO doctorAppointmentsDTO=new DoctorAppointmentsDTO();
		doctorAppointmentsDTO.setDoctorDTO(ddto);
		doctorAppointmentsDTO.setAppointmentDTOs(adtolist);
				
	    return doctorAppointmentsDTO;
	}

}
