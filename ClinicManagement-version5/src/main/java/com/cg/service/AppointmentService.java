package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Appointment;
import com.cg.entity.Doctor;
import com.cg.entity.Patient;
import com.cg.exception.AppointmentNotFoundException;
import com.cg.exception.DoctorNotFoundException;
import com.cg.repository.AppointmentRepository;
import com.cg.repository.DoctorRepository;
import com.cg.repository.PatientRepository;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public List<Appointment> findAllAppointment() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment createAppointment(Appointment appointment,Integer did,Integer pid) {
		Doctor doctor =doctorRepository.findById(did)
				.orElseThrow(() -> new RuntimeException("Doctor not found"));
		
		
//		Patient patient=patientRepository.findById(appointment.getPatient().getPatientId())
//				.orElseGet(()->{
//				Patient newpPatient=new Patient();
//				newpPatient.setPname(appointment.getPatient().getPname());
//				newpPatient.setAge(appointment.getPatient().getAge());
//				newpPatient.setGender(appointment.getPatient().getGender());
//				newpPatient.setPhone(appointment.getPatient().getPhone());
//				newpPatient.setEmail(appointment.getPatient().getEmail());
//				newpPatient.setAddress(appointment.getPatient().getAddress());
//				return patientRepository.save(newpPatient);
//				});
		
		Patient patient =patientRepository.findById(pid)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
				
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment findAppointmentById(Integer id) throws AppointmentNotFoundException {
		
		return appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for id : " + id));
	}

	@Override
	public String deleteAppointmentById(Integer id) {
		Appointment a=appointmentRepository.findById(id).get();
		if(a==null) {
			return "Appointment not found";
		}
		appointmentRepository.deleteById(id);
		return "Appointment deleted";
	}

	@Override
	public Appointment updateAppointmentById(Integer id, Appointment appointment,Integer did,Integer pid) throws AppointmentNotFoundException {
		
		Appointment a=appointmentRepository.findById(id)
				.orElseThrow(() -> new AppointmentNotFoundException("appointment not found for id : " + id));
		
		Doctor doctor =doctorRepository.findById(did)
				.orElseThrow(() -> new RuntimeException("Doctor not found"));
		Patient patient=patientRepository.findById(pid)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		a.setDoctor(doctor);
		a.setPatient(patient);
		a.setAppointmentDate(appointment.getAppointmentDate());
		a.setStatus(appointment.getStatus());
		
		return appointmentRepository.save(a);
	}

	@Override
	public List<Appointment> findAppointmentByStatus(String status) {
		
		return appointmentRepository.findByStatus(status);
	}

	@Override
	public List<Appointment> findScheduledAppointmentOnly() {
		
		return appointmentRepository.scheduledAppointment();
	}

}
