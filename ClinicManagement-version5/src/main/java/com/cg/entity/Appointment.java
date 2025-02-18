package com.cg.entity;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AppointmentV2")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appointmentId;
	
	@Column(name = "appointmentDate")
	private Date appointmentDate;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private Doctor doctor;
	
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(Integer appointmentId, Date appointmentDate, String status, Doctor doctor, Patient patient) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDate = appointmentDate;
		this.status = status;
		this.doctor = doctor;
		this.patient = patient;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	

}
