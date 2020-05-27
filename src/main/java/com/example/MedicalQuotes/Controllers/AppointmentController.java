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
	public ResponseEntity<?> createPatient(@RequestBody AppointmentDTO appointment) {
		Map<String, Object> response=new HashMap<>();
		AppointmentDTO	appointmentC=null;
		try {
			appointmentC=appointmentService.createAppointment(appointment);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "La cita ha sido creado con éxito!");
			response.put("appointment", appointmentC);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED); 

	}

	@PutMapping("/appointments/{id}")
	public AppointmentDTO updateAppointment(@RequestBody AppointmentDTO appointment, @PathVariable Long id) {
			System.out.println(appointment.getMedicines().size());
		return appointmentService.updateAppointment(appointment, id);

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
