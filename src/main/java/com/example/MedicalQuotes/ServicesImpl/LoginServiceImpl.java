package com.example.MedicalQuotes.ServicesImpl;

import javax.annotation.Resource;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.MedicalQuotes.Controllers.PatientController;
import com.example.MedicalQuotes.Dto.DoctorDTO;
import com.example.MedicalQuotes.Dto.PatientDTO;
import com.example.MedicalQuotes.Models.Login;
import com.example.MedicalQuotes.Models.Patient;
import com.example.MedicalQuotes.Services.DoctorService;
import com.example.MedicalQuotes.Services.LoginService;
import com.example.MedicalQuotes.Services.PatientService;
@Service
public class LoginServiceImpl implements LoginService {
	
	
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService  ;

	
	public Login checkCredentials(String login, String password)
	{	
		
		PatientDTO patient=patientService.getPatientByLogin(login);
		
		
		if( patient!=null && patient.getPassword().equals(password) ) {
			
			
			return new Login(patient.getId(),patient.getLogin(),patient.getPassword(),patient.getRole().getName());
		}
		
		DoctorDTO doctor=doctorService.findByLogin(login);
		
	
		if( doctor !=null && doctor.getPassword().equals(password )) {
		
			return new Login(doctor.getId(),doctor.getLogin(),doctor.getPassword(),doctor.getRole().getName());
		}
		
		return null;
		
	}

}
