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
import com.example.MedicalQuotes.Dto.MedicineDTO;
import com.example.MedicalQuotes.Services.MedicineService;

@RestController
public class MedicineController {
	@Autowired
	MedicineService medicineService;

	@GetMapping("/medicines")
	public Collection<MedicineDTO> medicines() {
		return medicineService.getMedicines();
	}

	@GetMapping("/medicines/{id}")
	public MedicineDTO getMedicine(@PathVariable("id") Long id) {

		return medicineService.getMedicineById(id);

	}


	
	@PostMapping("/medicines/create")
	public ResponseEntity<?> createMedicine(@RequestBody MedicineDTO medicine) {
		Map<String, Object> response=new HashMap<>();
		MedicineDTO	medicineC=null;
		try {
			medicineC=medicineService.createMedicine(medicine);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			response.put("mensaje", "El medicamento ha sido creado con éxito!");
			response.put("medicine", medicineC);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED); 

	}

	@PutMapping("/medicines/{id}")
	public MedicineDTO updateMedicine(@RequestBody MedicineDTO medicine, @PathVariable Long id) {

		return medicineService.updateMedicine(medicine, id);

	}

	@DeleteMapping("/medicines/{id}")
	public void deleteMedicine(@PathVariable("id") Long id) {

		medicineService.removeMedicine(id);

	}
}
