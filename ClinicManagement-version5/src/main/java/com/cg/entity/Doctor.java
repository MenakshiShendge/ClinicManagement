package com.cg.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "DoctorV2")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	
	@NotNull(message = "name cannot be null")
	@Column(name = "dname")
	private String dname;

	@Column(name = "specialization")
	private String specialization;
	

    @Size(min=10 ,max = 10, message = "phone no have only 10 digits")
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
	private String email;
    
    @Column(name = "address")
	private String address;
    
    @Column(name="consultationFees")
    private double consultationFees;
    
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Appointment> appointment=new HashSet<>();

	public Doctor() {
		super();
	}

	public Doctor(Integer doctorId, @NotNull(message = "name cannot be null") String dname, String specialization,
			@Size(max = 10, message = "phone no have only 10 digits") String phone, String email, String address,
			double consultationFees) {
		super();
		this.doctorId = doctorId;
		this.dname = dname;
		this.specialization = specialization;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.consultationFees = consultationFees;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getConsultationFees() {
		return consultationFees;
	}

	public void setConsultationFees(double consultationFees) {
		this.consultationFees = consultationFees;
	}

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}
	
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", dname=" + dname + ", specialization=" + specialization + ", phone="
				+ phone + ", email=" + email + ", address=" + address + ", consultationFees=" + consultationFees + "]";
	}
    
	
	
    
    
    
	
	
}
