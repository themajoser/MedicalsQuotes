package com.example.MedicalQuotes.ServicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.PatientDTO;
import com.example.MedicalQuotes.Models.Doctor;
import com.example.MedicalQuotes.Models.Patient;
import com.example.MedicalQuotes.Models.Role;
import com.example.MedicalQuotes.Repositories.PatientRepository;
import com.example.MedicalQuotes.Services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Collection<PatientDTO> getPatients() {

		try {
			ModelMapper modelMapper = new ModelMapper();
			List<Patient> patientsModelo = patientRepository.findAll();
			Collection<PatientDTO> patientsDTOList = new ArrayList<>();
			patientsModelo.forEach(patient -> patientsDTOList.add(modelMapper.map(patient, PatientDTO.class)));

			return patientsDTOList;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PatientDTO getPatientById(Long id) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Patient patientModel = patientRepository.findById(id).get();
			PatientDTO PatientDTO = modelMapper.map(patientModel, PatientDTO.class);

			return PatientDTO;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PatientDTO createPatient(PatientDTO patientDTO) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Patient patientModel = modelMapper.map(patientDTO, Patient.class);
			Patient patient = patientRepository.save(addAttributes(patientModel, "create"));
			return modelMapper.map(patient, PatientDTO.class);

		} catch (Exception e) {
			return null;
		}
	}

	public void removePatient(Long id) {

		Patient patient = new Patient();
		patient = patientRepository.findById(id).get();
		if (patient != null) {
			patientRepository.save(addAttributes(patient, "delete"));
		}

	}

	private Patient addAttributes(Patient patient, String accion) {
		switch (accion) {
		case "create":
			patient.setCreated_at(new Date());
			patient.setDeleted(false);
			return patient;
		case "delete":
			patient.setDeleted_at(new Date());
			patient.setDeleted(true);
			return patient;

		}
		return null;
	}

	public Patient checkAttributes(PatientDTO patient, Patient patientBBDD) {
		if (patient.getName() != null) {
			patientBBDD.setName(patient.getName());
		}
		if (patient.getLastname() != null) {
			patientBBDD.setLastname(patient.getLastname());
		}
		if (patient.getLogin() != null) {
			patientBBDD.setLogin(patient.getLogin());
		}
		if (!patient.getPassword().isEmpty()   ) {
			patientBBDD.setPassword(patient.getPassword());
		}
		if (patient.getDate_of_birth() != null) {
			patientBBDD.setDate_of_birth(patient.getDate_of_birth());
		}
		if (patient.getAllergies() != null) {
			patientBBDD.setAllergies(patient.getAllergies());
		}
		if (patient.getDoctor() != null) {
			ModelMapper modelMapper = new ModelMapper();
			patientBBDD.setDoctor( modelMapper.map(patient.getDoctor(), Doctor.class));
			
		}
		if (patient.getCancer() != null) {
			patientBBDD.setCancer(patient.getCancer());
		}
		if (patient.getDiseases() != null) {
			patientBBDD.setDiseases(patient.getDiseases());
		}
		if (patient.getRole() != null) {
			ModelMapper modelMapper = new ModelMapper();
			patientBBDD.setRole(modelMapper.map(patient.getRole(), Role.class));
			
		}

		return patientBBDD;

	}

	@Override
	public PatientDTO updatePatient(PatientDTO patient, Long id) {

		ModelMapper modelMapper = new ModelMapper();
		Patient patientBBDD = new Patient();
		patientBBDD = patientRepository.findById(id).get();
		if (patientBBDD == null) {
			return null;
		}
		Patient Patient = patientRepository.save(checkAttributes(patient, patientBBDD));

		return modelMapper.map(Patient, PatientDTO.class);

	}


	@Override
	public PatientDTO getPatientByLogin(String login) {
		try {
			
	ModelMapper modelMapper = new ModelMapper();
		Patient patientModel = patientRepository.getPatientByLogin(login);
		return modelMapper.map(patientModel, PatientDTO.class);

		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public  Collection<PatientDTO> getPatientByDoctor(Long id) {
		try {
			
	ModelMapper modelMapper = new ModelMapper();
	List<Patient> patientsModelo = patientRepository.getPatientsByDoctor(id);
		Collection<PatientDTO> patientsDTOList = new ArrayList<>();
		
		patientsModelo.forEach(patient -> patientsDTOList.add(modelMapper.map(patient, PatientDTO.class)));
		
		return patientsDTOList;

		} catch (Exception e) {
			return null;
		}
	}

}
