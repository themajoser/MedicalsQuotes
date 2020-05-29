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

import com.example.MedicalQuotes.Dto.MedicineDTO;
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
	public ResponseEntity<?> updateMedicine(@RequestBody MedicineDTO medicineDTO, @PathVariable Long id) {
		Map<String, Object> response=new HashMap<>();
		MedicineDTO	medicineC=null;
		try {
			medicineC= medicineService.updateMedicine(medicineDTO, id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error! No se ha podido guardar en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(medicineC == null) {
			response.put("mensaje", "No se ha encontrado el  medicamento, revise los valores obligatorio o intentelo más tarde");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "El medicamento ha sido actulizado con éxito!");
		response.put("medicine", medicineC);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		
	

	}

	@DeleteMapping("/medicines/{id}")
	public void deleteMedicine(@PathVariable("id") Long id) {

		medicineService.removeMedicine(id);

	}
}
