package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.dto.AppointmentDTO;
import com.cg.dto.DoctorDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Appointment;
import com.cg.entity.Doctor;
import com.cg.exception.AppointmentNotFoundException;
import com.cg.exception.DoctorNotFoundException;
import com.cg.service.IAppointmentService;

@RestController
@RequestMapping("/ac")
public class AppointmentControler {
	@Autowired
	IAppointmentService appointmentService;
	
	@GetMapping("/hello")
	public String helloAppointment() {
		return "hello Appointment security";
	}
	
	@GetMapping("/appointments")
	public List<AppointmentDTO> getAllAppointments(){
		List<Appointment> alist=appointmentService.findAllAppointment();
		List<AppointmentDTO> adtolist=alist.stream()
				.map(AppointmentDTO::fromEntity)
				 .peek(adto -> adto.setDoctorId(adto.getDoctorDTO().getDoctorId()))
				 .peek(adto -> adto.setPatientId(adto.getPatientDTO().getPatientId()))
				.collect(Collectors.toList());
		return adtolist;
	}

	@PostMapping("/appointments")
	public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		DoctorDTO d=new DoctorDTO();
		PatientDTO p=new PatientDTO();
		appointmentDTO.setDoctorDTO(d);
		appointmentDTO.setPatientDTO(p);
		Appointment appointment=appointmentDTO.toEntity();
		Appointment a1=appointmentService.createAppointment(appointment,appointmentDTO.getDoctorId(),appointmentDTO.getPatientId());
		appointmentDTO=appointmentDTO.fromEntity(a1);
		appointmentDTO.setPatientId(a1.getPatient().getPatientId());
		appointmentDTO.setDoctorId(a1.getDoctor().getDoctorId());
		return appointmentDTO;
	}
	
	@GetMapping("/appointments/{id}")
	public AppointmentDTO getAppointmentsById(@PathVariable Integer id) throws AppointmentNotFoundException {
		Appointment a= appointmentService.findAppointmentById(id);
		AppointmentDTO appointmentDTO=new AppointmentDTO();
		appointmentDTO=appointmentDTO.fromEntity(a);
		appointmentDTO.setPatientId(a.getPatient().getPatientId());
		appointmentDTO.setDoctorId(a.getDoctor().getDoctorId());
		return appointmentDTO;
		
	}
	
	@DeleteMapping("/appointments/{id}")
	public String deleteAppointment(@PathVariable Integer id) {
		return appointmentService.deleteAppointmentById(id);
	}
	
	@PutMapping("/appointments/{id}")
	public AppointmentDTO updateAppointmentsById(@PathVariable Integer id,@RequestBody AppointmentDTO appointmentDTO)throws AppointmentNotFoundException {
		DoctorDTO d=new DoctorDTO();
		PatientDTO p=new PatientDTO();
		appointmentDTO.setDoctorDTO(d);
		appointmentDTO.setPatientDTO(p);
		Appointment a=appointmentDTO.toEntity();
		Appointment a1=appointmentService.updateAppointmentById(id, a,appointmentDTO.getDoctorId(),appointmentDTO.getPatientId());
		appointmentDTO=appointmentDTO.fromEntity(a1);
		appointmentDTO.setPatientId(a1.getPatient().getPatientId());
		appointmentDTO.setDoctorId(a1.getDoctor().getDoctorId());
		
		return appointmentDTO;
	}
	
	@GetMapping("/appointments/status/{status}")
	public List<AppointmentDTO> getAllAppointmentsByStatus(@PathVariable String status){
		List<Appointment> alist=appointmentService.findAppointmentByStatus(status);
		List<AppointmentDTO> adtolist=alist.stream()
				.map(AppointmentDTO::fromEntity)
				 .peek(adto -> adto.setDoctorId(adto.getDoctorDTO().getDoctorId()))
				 .peek(adto -> adto.setPatientId(adto.getPatientDTO().getPatientId()))
				.collect(Collectors.toList());
		return adtolist;
	}
	@GetMapping("/appointments/scheduled")
	public List<AppointmentDTO> getAllScheduledAppointment(){
		List<Appointment> alist=appointmentService.findScheduledAppointmentOnly();
		List<AppointmentDTO> adtolist=alist.stream()
				.map(AppointmentDTO::fromEntity)
				 .peek(adto -> adto.setDoctorId(adto.getDoctorDTO().getDoctorId()))
				 .peek(adto -> adto.setPatientId(adto.getPatientDTO().getPatientId()))
				.collect(Collectors.toList());
		return adtolist;
	}
}
