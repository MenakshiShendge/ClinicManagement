package com.cg.dto;

import java.util.Set;


import com.cg.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class DoctorDTO {
	private Integer doctorId;
	private String dname;
	private String specialization;
	private String phone;
	private String email;
	private String address;
	private double consultationFees;
//	@JsonIgnoreProperties("doctor")
//	private Set<AppointmentDTO> appointment;
	
	public DoctorDTO() {
		super();
	}




	public DoctorDTO(Integer doctorId, String dname, String specialization, String phone, String email, String address,
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


	@Override
	public String toString() {
		return "DoctorDTO [doctorId=" + doctorId + ", dname=" + dname + ", specialization=" + specialization
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", consultationFees="
				+ consultationFees + "]";
	}
	
	// Optionally, you can add methods to convert from Entity to DTO and vice versa.
    public static DoctorDTO fromEntity(Doctor doctor) {
        return new DoctorDTO(doctor.getDoctorId(), doctor.getDname(),doctor.getSpecialization(),doctor.getPhone()
        		,doctor.getEmail(),doctor.getAddress(),doctor.getConsultationFees());
    }
 
    public Doctor toEntity() {
        return new Doctor(this.doctorId, this.dname, this.specialization
        		,this.phone,this.email,this.address,this.consultationFees);
    }
	

//	public Set<AppointmentDTO> getAppointment() {
//		return appointment;
//	}
//
//
//	public void setAppointment(Set<AppointmentDTO> appointment) {
//		this.appointment = appointment;
//	}
	

}
