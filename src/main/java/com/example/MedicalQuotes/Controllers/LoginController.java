package com.example.MedicalQuotes.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MedicalQuotes.Models.Login;
import com.example.MedicalQuotes.Services.LoginService;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	LoginService LoginService;
	
	@GetMapping("/login/{login}/{password}")
	public ResponseEntity<?> checkCredentials( @PathVariable String login , @PathVariable String password) {
				
			Login logina=LoginService.checkCredentials(login, password);

		Map<String, Object> response=new HashMap<>();
		if(logina == null) {
			response.put("mensaje", "El usuario o contraseña es incorrecta");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Login>(logina,HttpStatus.OK);

	}
	


	

	
	
}
