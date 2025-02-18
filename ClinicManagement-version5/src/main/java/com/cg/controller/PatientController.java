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
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.dto.PatientAppointmentDTO;
import com.cg.dto.PatientDTO;
import com.cg.entity.Patient;
import com.cg.exception.PatientNotFoundException;
import com.cg.service.IPatientService;

@RestController
@RequestMapping("/pc")
public class PatientController {
	
	@Autowired
	IPatientService patientService;
	
	@GetMapping("/hello")
	public String helloPatient() {
		return "hello Patient v6";
	}
	
	@GetMapping("/patients")
	public List<PatientDTO> findAllPatient(){
		List<Patient> plist=patientService.findAllPatient();
		List<PatientDTO> pdtolist=plist.stream()
				.map(PatientDTO::fromEntity)
				.collect(Collectors.toList());
		return pdtolist;
	}
	
	@PostMapping("/patients")
	public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
		Patient p=patientDTO.toEntity();
		Patient p1=patientService.createPatient(p);
		return patientDTO.fromEntity(p1);
	}
	
	@GetMapping("/patients/{id}")
	public PatientDTO findpatientsById(@PathVariable Integer id) throws PatientNotFoundException{
		Patient p=patientService.findPatientById(id);
		PatientDTO patientDTO =new PatientDTO();
		return patientDTO.fromEntity(p);
	}
	@DeleteMapping("/patients/{id}")
	public String deletePatients(@PathVariable Integer id) {
		return patientService.deletePatientById(id);
	}
	
	@PutMapping("/patients/{id}")
	public PatientDTO updatePatients(@PathVariable Integer id,@RequestBody PatientDTO patientDTO) throws PatientNotFoundException
	{
		Patient p=patientDTO.toEntity();
		return patientDTO.fromEntity(patientService.updatePatient(id, p));
	}

	@GetMapping("/patients/name/{pname}")
	public PatientDTO findPatientByName(@PathVariable String pname){
		Patient p=patientService.findAllPatientByName(pname).get();
		PatientDTO pdto =new PatientDTO();
		return pdto.fromEntity(p);
	}
	@GetMapping("/patients/name/{pname}/status/{status}")
	public PatientAppointmentDTO findAppointmentsByPatientNameAndStatus(@PathVariable String pname,@PathVariable String status) {
		return patientService.findAppointmentsByPatientNameAndStatus(pname, status);
	}
}
