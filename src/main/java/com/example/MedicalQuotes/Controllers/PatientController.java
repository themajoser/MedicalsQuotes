package com.example.MedicalQuotes.Controllers;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalQuotes.Dto.PatientDTO;
import com.example.MedicalQuotes.Models.Patient;
import com.example.MedicalQuotes.Services.PatientService;

@RestController
@CrossOrigin(origins = "*")
public class PatientController {
	@Autowired
	PatientService patientService;

	@GetMapping("/patients")
	public Collection<PatientDTO> patients() {
		return patientService.getPatients();
	}

	@GetMapping("/patients/{id}")
	public PatientDTO getPatient(@PathVariable("id") Long id) {

		return patientService.getPatientById(id);

	}

	@PostMapping("/patients/create")
	public ResponseEntity<?> createPatient(@RequestBody PatientDTO patient) {
		Map<String, Object> response=new HashMap<>();
		PatientDTO	patientC=null;
		System.out.println("hola");
		try {
		patientC=patientService.createPatient(patient);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(patientC == null) {
			response.put("mensaje", "No se ha podido crear el paciente");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "El paciente ha sido creado con éxito!");
			response.put("patient", patientC);
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
		 

	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<?> updatePatient(@RequestBody PatientDTO patientDTO, @PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
		PatientDTO	patientC=null;
		try {
			patientC= patientService.updatePatient(patientDTO, id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error! No se ha podido guardar en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(patientC == null) {
			response.put("mensaje", "No se ha encontrado el paciente, revise los valores obligatorio o intentelo más tarde");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El paciente ha sido actulizado con éxito!");
		response.put("patient", patientC);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	

	}

	@DeleteMapping("/patients/{id}")
	public void deletePatient(@PathVariable("id") Long id) {

		patientService.removePatient(id);

	}
	
	@GetMapping("/patients/getPatientBylogin/{login}")
	public PatientDTO getPatientBylogin (@PathVariable("login")  String login) {
			
		return patientService.getPatientByLogin(login);

	}
	
	@GetMapping("/patients/getPatientByDoctor/{id}")
	public  Collection<PatientDTO> getPatientByDoctor (@PathVariable("id")  Long id) {
			
		return patientService.getPatientByDoctor(id);

	}
	
	
	
}
