package com.example.MedicalQuotes.Services;

import java.util.Collection;

import com.example.MedicalQuotes.Dto.AppointmentDTO;
import com.example.MedicalQuotes.Dto.DoctorDTO;

public interface AppointmentService {

	public abstract Collection<AppointmentDTO> getAppointments();

	public abstract AppointmentDTO getAppointmentById(Long id);

	public abstract AppointmentDTO createAppointment(AppointmentDTO appointment);

	public abstract void removeAppointment(Long id);

	public abstract AppointmentDTO updateAppointment(AppointmentDTO appointment, Long id);
	
	public abstract Collection<AppointmentDTO> findByDoctor(Long id);
	
	public abstract Collection<AppointmentDTO> findByPatient(Long id);

}
