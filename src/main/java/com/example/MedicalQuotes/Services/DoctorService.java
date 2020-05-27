package com.example.MedicalQuotes.Services;

import java.util.Collection;

import com.example.MedicalQuotes.Dto.DoctorDTO;

public interface DoctorService {

	public abstract Collection<DoctorDTO> getDoctors();

	public abstract DoctorDTO getDoctorById(Long id);

	public abstract DoctorDTO createDoctor(DoctorDTO doctor);

	public abstract void removeDoctor(Long id);

	public abstract DoctorDTO updateDoctor(DoctorDTO doctor, Long id);
	
	public abstract DoctorDTO findByLogin(String login);

}
