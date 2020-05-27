package com.example.MedicalQuotes.ServicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.AppointmentDTO;
import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Dto.MedicineDTO;
import com.example.MedicalQuotes.Models.Appointment;
import com.example.MedicalQuotes.Models.Doctor;
import com.example.MedicalQuotes.Models.Medicine;
import com.example.MedicalQuotes.Models.Patient;
import com.example.MedicalQuotes.Models.Role;
import com.example.MedicalQuotes.Repositories.AppointmentRepository;
import com.example.MedicalQuotes.Services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public Collection<AppointmentDTO> getAppointments() {

		try {
			ModelMapper modelMapper = new ModelMapper();
			List<Appointment> appointmentsModelo = appointmentRepository.findAll();
			Collection<AppointmentDTO> appointmentsDTOList = new ArrayList<>();
			appointmentsModelo.forEach(
					appointment -> appointmentsDTOList.add(modelMapper.map(appointment, AppointmentDTO.class)));

			return appointmentsDTOList;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AppointmentDTO getAppointmentById(Long id) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Appointment appointmentModel = appointmentRepository.findById(id).get();
			AppointmentDTO AppointmentDTO = modelMapper.map(appointmentModel, AppointmentDTO.class);

			return AppointmentDTO;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointment) {
		try {
			ModelMapper modelMapper = new ModelMapper();

			appointmentRepository.save(modelMapper.map(appointment, Appointment.class));
				
			return appointment;

		} catch (Exception e) {
			return null;
		}
	}

	public void removeAppointment(Long id) {

		Appointment appointment = new Appointment();

		appointment = appointmentRepository.findById(id).get();
		if (appointment != null) {
			appointment.setDeleted_at(new Date());
			appointment.setDeleted(true);
			appointmentRepository.save(appointment);
		}

	}

	/**
	 * 
	 * @param appointment
	 * @param appointmentBBDD
	 * @return
	 */
	public Appointment checkAttributes(AppointmentDTO appointment, Appointment appointmentBBDD) {
		if (appointment.getAssement() != null) {
			appointmentBBDD.setAssement(appointment.getAssement());
		}
		if (appointment.getDate() != null) {
			appointmentBBDD.setDate(appointment.getDate());
		}
		if (appointment.getStatus() != null) {
			appointmentBBDD.setStatus(appointment.getStatus());
		}
		if (appointment.getId_patient() != null) {
			appointmentBBDD.setId_patient(appointment.getId_patient());
		}
		if (appointment.getDoctor() != null) {
			ModelMapper modelMapper = new ModelMapper();
			appointmentBBDD.setDoctor(modelMapper.map(appointment.getDoctor(), Doctor.class));
		}
		if (appointment.getPatient() != null) {
			ModelMapper modelMapper = new ModelMapper();
			appointmentBBDD.setPatient(modelMapper.map(appointment.getPatient(), Patient.class));
		}
		if (appointment.getMedicines() != null) {
			ModelMapper modelMapper = new ModelMapper();
			
			Set<Medicine> medicines= new HashSet<>();
			for(MedicineDTO medicine : appointment.getMedicines())
			{
				medicines.add(modelMapper.map(medicine, Medicine.class));
			}
			appointmentBBDD.setMedicines(medicines);
		}

		return appointmentBBDD;

	}

	@Override
	public AppointmentDTO updateAppointment(AppointmentDTO appointment, Long id) {

		ModelMapper modelMapper = new ModelMapper();
		Appointment appointmentBBDD = new Appointment();
		appointmentBBDD = appointmentRepository.findById(id).get();
		if (appointmentBBDD == null) {
			return null;
		}
		Appointment user = appointmentRepository.save(checkAttributes(appointment, appointmentBBDD));
		System.out.println(user.getMedicines().size());
		return modelMapper.map(user, AppointmentDTO.class);

	}
	
	public Collection<AppointmentDTO> findByDoctor(Long id) {
		try {
			
			ModelMapper modelMapper = new ModelMapper();
			List<Appointment>  appointmentModel = appointmentRepository.findByDoctor(id);
		
			Collection<AppointmentDTO> appointmentsDTOList = new ArrayList<>();
			appointmentModel.forEach(
					appointment -> appointmentsDTOList.add(modelMapper.map(appointment, AppointmentDTO.class)));
	

			return appointmentsDTOList;

		} catch (Exception e) {
			return null;
		}
	
	}
	public Collection<AppointmentDTO> findByPatient(Long id) {
		try {
			
			ModelMapper modelMapper = new ModelMapper();
			List<Appointment>  appointmentModel = appointmentRepository.findByPatient(id);
		
			Collection<AppointmentDTO> appointmentsDTOList = new ArrayList<>();
			appointmentModel.forEach(
					appointment -> appointmentsDTOList.add(modelMapper.map(appointment, AppointmentDTO.class)));
	

			return appointmentsDTOList;

		} catch (Exception e) {
			return null;
		}
	
	}

}
