package com.cg.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	public List<Appointment> findByStatus(String status);
	
	@Query("SELECT a FROM Appointment a WHERE a.status = 'Scheduled'")
	public List<Appointment> scheduledAppointment();
	
	@Query("SELECT a FROM Appointment a WHERE a.doctor.doctorId= :doctorId AND a.status= :status ")
	public List<Appointment> findAppointmentsByDocterIdAndStatus(Integer doctorId,String status);
	
	
	@Query("SELECT a FROM Appointment a WHERE a.patient.patientId= :patientId AND a.status= :status ")
	public List<Appointment> findAppointmentsByPatientIdAndStatus(Integer patientId,String status);

}
