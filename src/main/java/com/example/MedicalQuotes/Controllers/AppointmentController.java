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

import com.example.MedicalQuotes.Dto.AppointmentDTO;
import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Services.AppointmentService;

@RestController
public class AppointmentController {
	@Autowired
	AppointmentService appointmentService;

	@GetMapping("/appointments")
	public Collection<AppointmentDTO> appointments() {
		return appointmentService.getAppointments();
	}

	@GetMapping("/appointments/{id}")
	public AppointmentDTO getAppointment(@PathVariable("id") Long id) {

		return appointmentService.getAppointmentById(id);

	}


	@PostMapping("/appointments/create")
	public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointment) {
		Map<String, Object> response=new HashMap<>();
		AppointmentDTO	appointmentC=null;
		try {
		appointmentC=appointmentService.createAppointment(appointment);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(appointmentC == null) {
			response.put("mensaje", "No se ha podido crear el appointment");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "El appointment ha sido creado con éxito!");
			response.put("appointment", appointmentC);
			
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
 

	}


	@PutMapping("/appointments/{id}")
	public ResponseEntity<?> updateAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
		AppointmentDTO	appointmentC=null;
		try {
			appointmentC= appointmentService.updateAppointment(appointmentDTO, id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error! No se ha podido guardar en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(appointmentC == null) {
			response.put("mensaje", "No se ha encontrado la cita, revise los valores obligatorio o intentelo más tarde");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "La cita ha sido actulizado con éxito!");
		response.put("appointment", appointmentC);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	

	}

	@DeleteMapping("/appointments/{id}")
	public void deleteAppointment(@PathVariable("id") Long id) {

		appointmentService.removeAppointment(id);

	}
	
	@GetMapping("/appointments/doctor/{id}")
	public Collection<AppointmentDTO> getAppointmentByDoctor(@PathVariable("id") Long id) {
			
		return appointmentService.findByDoctor(id);

	}
	
	@GetMapping("/appointments/patient/{id}")
	public Collection<AppointmentDTO> getAppointmentByPatient(@PathVariable("id") Long id) {
			
		return appointmentService.findByPatient(id);

	}
}
