package com.example.MedicalQuotes.ServicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Models.Doctor;
import com.example.MedicalQuotes.Models.Doctor;
import com.example.MedicalQuotes.Repositories.DoctorRepository;
import com.example.MedicalQuotes.Services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Collection<DoctorDTO> getDoctors() {

		try {
			ModelMapper modelMapper = new ModelMapper();
			List<Doctor> doctorsModelo = doctorRepository.findAll();
			Collection<DoctorDTO> doctorsDTOList = new ArrayList<>();
			doctorsModelo.forEach(doctor -> doctorsDTOList.add(modelMapper.map(doctor, DoctorDTO.class)));

			return doctorsDTOList;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public DoctorDTO getDoctorById(Long id) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Doctor doctorModel = doctorRepository.findById(id).get();
			DoctorDTO DoctorDTO = modelMapper.map(doctorModel, DoctorDTO.class);

			return DoctorDTO;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Doctor doctorModel = modelMapper.map(doctorDTO, Doctor.class);
			Doctor doctor = doctorRepository.save(addAttributes(doctorModel, "create"));
			return modelMapper.map(doctor, DoctorDTO.class);

		} catch (Exception e) {
			return null;
		}
	}

	private Doctor addAttributes(Doctor doctor, String accion) {
		switch (accion) {
		case "create":
			doctor.setCreated_at(new Date());
			doctor.setDeleted(false);
			return doctor;
		case "delete":
			doctor.setDeleted_at(new Date());
			doctor.setDeleted(true);
			return doctor;

		}
		return null;
	}

	public Doctor checkAttributes(DoctorDTO doctor, Doctor doctorBBDD) {
		if (doctor.getName() != null) {
			doctorBBDD.setName(doctor.getName());
		}
		if (doctor.getLastname() != null) {
			doctorBBDD.setLastname(doctor.getLastname());
		}
		if (doctor.getLogin() != null) {
			doctorBBDD.setLogin(doctor.getLogin());
		}
		if (!doctor.getPassword().isEmpty()   ) {
			doctorBBDD.setPassword(doctor.getPassword());
		}
		if (doctor.getDate_of_birth() != null) {
			doctorBBDD.setDate_of_birth(doctor.getDate_of_birth());
		}
		if (doctor.getSpeciality() != null) {
			doctorBBDD.setSpeciality(doctor.getSpeciality());
		}
		if (doctor.getDeleted() != null) {
			doctorBBDD.setDeleted(doctor.getDeleted());
		}
		if (doctor.getDeleted_at() != null) {
			doctorBBDD.setDeleted_at(doctor.getDeleted_at());
		}
		if (doctor.getCreated_at() != null) {
			doctorBBDD.setCreated_at(doctor.getCreated_at());
		}
		if (doctor.getId_role() != null) {
			doctorBBDD.setId_role(doctor.getId_role());
		}

		return doctorBBDD;

	}

	@Override
	public DoctorDTO updateDoctor(DoctorDTO doctor, Long id) {

		ModelMapper modelMapper = new ModelMapper();
		Doctor doctorBBDD = new Doctor();
		doctorBBDD = doctorRepository.findById(id).get();
		if (doctorBBDD == null) {
			return null;
		}
		Doctor Doctor = doctorRepository.save(checkAttributes(doctor, doctorBBDD));

		return modelMapper.map(Doctor, DoctorDTO.class);

	}

	public void removeDoctor(Long id) {

		Doctor doctor = new Doctor();
		doctor = doctorRepository.findById(id).get();
		if (doctor != null) {
			doctorRepository.save(addAttributes(doctor, "delete"));
		}

	}
	
	public DoctorDTO findByLogin(String login) {
		try {
		
			ModelMapper modelMapper = new ModelMapper();
		
			Doctor doctorModel = doctorRepository.findByLogin(login);
				
				return modelMapper.map(doctorModel, DoctorDTO.class);


		} catch (Exception e) {
			return null;
		}
	
	}
	


}
