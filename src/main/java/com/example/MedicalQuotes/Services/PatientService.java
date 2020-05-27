package com.example.MedicalQuotes.Services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.PatientDTO;
import com.example.MedicalQuotes.Models.Patient;

public interface PatientService {

	public abstract Collection<PatientDTO> getPatients();

	public abstract PatientDTO getPatientById(Long id);

	public abstract PatientDTO createPatient(PatientDTO patient);

	public abstract void removePatient(Long id);

	public abstract PatientDTO updatePatient(PatientDTO patient, Long id);
	
	public abstract  PatientDTO getPatientByLogin(String login);
	
	public abstract Collection<PatientDTO> getPatientByDoctor(Long id);

}
