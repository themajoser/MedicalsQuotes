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
import com.example.MedicalQuotes.Dto.DoctorDTO;
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
	public ResponseEntity<?> createDoctor(@RequestBody DoctorDTO doctor) {
		Map<String, Object> response=new HashMap<>();
		DoctorDTO	doctorC=null;
		try {
		doctorC=doctorService.createDoctor(doctor);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(doctorC == null) {
			response.put("mensaje", "No se ha podido crear el doctor");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "El doctor ha sido creado con éxito!");
			response.put("doctor", doctorC);
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
 

	}


	@PutMapping("/doctors/{id}")
	public ResponseEntity<?> updateDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
		DoctorDTO	doctorC=null;
		try {
			doctorC= doctorService.updateDoctor(doctorDTO, id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error! No se ha podido guardar en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(doctorC == null) {
			response.put("mensaje", "No se ha encontrado el doctor, revise los valores obligatorio o intentelo más tarde");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El doctor ha sido actulizado con éxito!");
		response.put("doctor", doctorC);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	

	}

	@DeleteMapping("/doctors/{id}")
	public void deleteDoctor(@PathVariable("id") Long id) {

		doctorService.removeDoctor(id);

	}
	

	public DoctorDTO getDoctorBylogin(@PathVariable("login") String login) {

		return doctorService.findByLogin(login);

	}
}
