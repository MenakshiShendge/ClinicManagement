package com.cg.dto;

import java.util.List;

public class PatientAppointmentDTO {

	private PatientDTO patientDTO;
	private List<AppointmentDTO> appointmentDTOs;
	public PatientAppointmentDTO(PatientDTO patientDTO, List<AppointmentDTO> appointmentDTOs) {
		super();
		this.patientDTO = patientDTO;
		this.appointmentDTOs = appointmentDTOs;
	}
	public PatientAppointmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientDTO getPatientDTO() {
		return patientDTO;
	}
	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}
	public List<AppointmentDTO> getAppointmentDTOs() {
		return appointmentDTOs;
	}
	public void setAppointmentDTOs(List<AppointmentDTO> appointmentDTOs) {
		this.appointmentDTOs = appointmentDTOs;
	}
	
	
}
