package com.cg.dto;
import java.util.*;
public class DoctorAppointmentsDTO {
	private DoctorDTO doctorDTO;
	private List<AppointmentDTO> appointmentDTOs;
	public DoctorAppointmentsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorAppointmentsDTO(DoctorDTO doctorDTO, List<AppointmentDTO> appointmentDTOs) {
		super();
		this.doctorDTO = doctorDTO;
		this.appointmentDTOs = appointmentDTOs;
	}
	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}
	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}
	public List<AppointmentDTO> getAppointmentDTOs() {
		return appointmentDTOs;
	}
	public void setAppointmentDTOs(List<AppointmentDTO> appointmentDTOs) {
		this.appointmentDTOs = appointmentDTOs;
	}
	
	
}
