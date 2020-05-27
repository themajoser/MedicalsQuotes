package com.example.MedicalQuotes.Controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Dto.PatientDTO;
import com.example.MedicalQuotes.Services.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	DoctorService doctorService;

	@GetMapping("/doctors")
	public Collection<DoctorDTO> doctors() {
		return doctorService.getDoctors();
	}

	@GetMapping("/doctors/{id}")
	public DoctorDTO getDoctor(@PathVariable("id") Long id) {

		return doctorService.getDoctorById(id);

	}


	@PostMapping("/doctors/create")
	public ResponseEntity<?> createPatient(@RequestBody DoctorDTO doctor) {
		Map<String, Object> response=new HashMap<>();
		DoctorDTO	doctorC=null;
		try {
		doctorC=doctorService.createDoctor(doctor);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "El doctor ha sido creado con éxito!");
			response.put("doctor", doctorC);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED); 

	}

	@PutMapping("/doctors/{id}")
	public DoctorDTO updateDoctor(@RequestBody DoctorDTO doctor, @PathVariable Long id) {

		return doctorService.updateDoctor(doctor, id);

	}

	@DeleteMapping("/doctors/{id}")
	public void deleteDoctor(@PathVariable("id") Long id) {

		doctorService.removeDoctor(id);

	}
	

	public DoctorDTO getDoctorBylogin(@PathVariable("login") String login) {

		return doctorService.findByLogin(login);

	}
}
