package com.cg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.DoctorAppointmentsDTO;
import com.cg.dto.DoctorDTO;
import com.cg.entity.Doctor;
import com.cg.exception.DoctorNotFoundException;
import com.cg.service.DoctorService;
import com.cg.service.IDoctorService;

@RestController
@RequestMapping("/dc")
public class DoctorController {
	
	@Autowired
	IDoctorService doctorService;
	
	@GetMapping("/hello")
	public String hellodoctor() {
		return "Hello Doctor v6";
	}
	
	@GetMapping("/doctors")
	public List<DoctorDTO> getAllDoctors(){
		List<Doctor> dlist=doctorService.findAllDoctors();
		List<DoctorDTO> doctordtolist=dlist.stream()
				.map(DoctorDTO::fromEntity)
                .collect(Collectors.toList());
		return doctordtolist;
	}
	
	@PostMapping("/doctors")
	public DoctorDTO newDoctor(@RequestBody DoctorDTO doctorDTO) {
		Doctor d=doctorDTO.toEntity();
		Doctor d1=doctorService.createDoctor(d);
		return doctorDTO.fromEntity(d1);
	}
	
	@GetMapping("/doctors/{id}")
	public DoctorDTO getDoctorById(@PathVariable Integer id) throws DoctorNotFoundException {
		Doctor d= doctorService.findDoctorById(id);
		DoctorDTO doctordto=new DoctorDTO();
		return doctordto.fromEntity(d);
		
	}
	
	@DeleteMapping("/doctors/{id}")
	public String deleteDoctor(@PathVariable Integer id) {
		return doctorService.deleteDoctorById(id);
	}
	
	@PutMapping("/doctors/{id}")
	public DoctorDTO updateDoctor(@PathVariable Integer id,@RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException
	{
		Doctor d=doctorDTO.toEntity();
		return doctorDTO.fromEntity(doctorService.updateDoctorById(id, d));
	}
	
	@GetMapping("/doctors/specialization/{specialization}")
	public List<DoctorDTO> getAllDoctorsBySpecialization(@PathVariable String specialization){
		List<Doctor> dlist=doctorService.findAllDoctorsBySpecialization(specialization);
		List<DoctorDTO> doctordtolist=dlist.stream()
				.map(DoctorDTO::fromEntity)
                .collect(Collectors.toList());
		return doctordtolist;
	}
	
	@GetMapping("/doctors/name/{dname}")
	public DoctorDTO findDoctorByName(@PathVariable String dname) {
		Doctor d=doctorService.findDoctorByName(dname).get();
		DoctorDTO ddto=new DoctorDTO();
		return ddto.fromEntity(d);
	}

	@GetMapping("/doctors/name/{dname}/status/{status}")
	public DoctorAppointmentsDTO findAppointmentByDoctorNameAndStatus(@PathVariable String dname,@PathVariable String status)
	{
		System.out.println(dname);
		System.out.println(status);
		return doctorService.findAppointmentByDoctorNameAndStatus(dname, status);
	}
}
