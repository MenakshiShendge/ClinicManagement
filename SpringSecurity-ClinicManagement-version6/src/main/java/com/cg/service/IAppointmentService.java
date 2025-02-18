package com.cg.service;
import java.util.List;

import com.cg.entity.Appointment;
import com.cg.exception.AppointmentNotFoundException;
public interface IAppointmentService {
	
	public List<Appointment> findAllAppointment();
	public Appointment createAppointment(Appointment appointment,Integer did,Integer pid);
	public Appointment findAppointmentById(Integer id) throws AppointmentNotFoundException;
	public String deleteAppointmentById(Integer id);
	public Appointment updateAppointmentById(Integer id,Appointment appointment,Integer did,Integer pid) throws AppointmentNotFoundException;
	public List<Appointment> findAppointmentByStatus(String status);
	public List<Appointment> findScheduledAppointmentOnly();
}
