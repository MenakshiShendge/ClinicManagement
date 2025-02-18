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
@Table(name = "PatientV2")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;
	
	@NotNull(message = "name cannot be null")
	@Column(name="pname")
	private String pname;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Size(min=10 ,max = 10, message = "phone no have only 10 digits")
    @Column(name = "phone")
    private String phone;
	
	@Column(name = "email")
	private String email;
    
    @Column(name = "address")
	private String address;
    
    

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Appointment> appointment=new HashSet<>();

	public Patient() {
		super();
	}

	public Patient(Integer patientId, @NotNull(message = "name cannot be null") String pname, Integer age,
			String gender, @Size(min = 10, max = 10, message = "phone no have only 10 digits") String phone,
			String email, String address) {
		super();
		this.patientId = patientId;
		this.pname = pname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
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

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", pname=" + pname + ", age=" + age + ", gender=" + gender
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + "]";
	}
	
	
    
    

}
