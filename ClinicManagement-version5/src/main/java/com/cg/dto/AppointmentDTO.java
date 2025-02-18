package com.cg.dto;

import java.util.Date;

import com.cg.entity.Appointment;
import com.cg.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class AppointmentDTO {
	private Integer appointmentId;
	private Date appointmentDate;
	private String status;
	private Integer patientId;
	private Integer doctorId;
	@JsonIgnoreProperties("appointment")
	private DoctorDTO doctorDTO;
	
	@JsonIgnoreProperties("appointment")
	private PatientDTO patientDTO;
	public AppointmentDTO(Integer appointmentId, Date appointmentDate, String status, DoctorDTO doctorDTO,
			PatientDTO patientDTO) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.status = status;
		this.doctorDTO = doctorDTO;
		this.patientDTO = patientDTO;
	}
	public AppointmentDTO() {
		super();
	}
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}
	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}
	public PatientDTO getPatientDTO() {
		return patientDTO;
	}
	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}
	
	public static AppointmentDTO fromEntity(Appointment appointment) {
		PatientDTO patientDTO=new PatientDTO().fromEntity(appointment.getPatient());
		DoctorDTO doctorDTO=new DoctorDTO().fromEntity(appointment.getDoctor());
        return new AppointmentDTO(appointment.getAppointmentId(),appointment.getAppointmentDate(),
        		appointment.getStatus(),doctorDTO,patientDTO);
    }
 
    public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Appointment toEntity() {
        return new Appointment(this.appointmentId,this.appointmentDate
        		,this.status,this.doctorDTO.toEntity(),this.patientDTO.toEntity());
    }

}
