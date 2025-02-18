package com.cg.dto;

import java.util.Set;

import com.cg.entity.Appointment;
import com.cg.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PatientDTO {
	private Integer patientId;
	private String pname;
	private Integer age;
	private String gender;
	private String phone;
	private String email;
	private String address;
	
//	@JsonIgnoreProperties("patient")
//	private Set<AppointmentDTO> appointment;
	
	

	public PatientDTO(Integer patientId, String pname, Integer age, String gender, String phone, String email,
			String address) {
		super();
		this.patientId = patientId;
		this.pname = pname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public PatientDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		return "PatientDTO [patientId=" + patientId + ", pname=" + pname + ", age=" + age + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + "]";
	}
	
	public static PatientDTO fromEntity(Patient patient) {
        return new PatientDTO(patient.getPatientId(),patient.getPname(),patient.getAge(),patient.getGender()
        		,patient.getPhone(),patient.getEmail(),patient.getAddress());
    }
 
    public Patient toEntity() {
        return new Patient(this.patientId,this.pname,this.age,this.gender,this.phone
        		,this.email,this.address);
    }
	
//    public Set<AppointmentDTO> getAppointment() {
//		return appointment;
//	}
//
//	public void setAppointment(Set<AppointmentDTO> appointment) {
//		this.appointment = appointment;
//	}
}
