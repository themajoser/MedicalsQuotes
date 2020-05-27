package com.example.MedicalQuotes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MedicalQuotes.Models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	
	@Query("select u  from Doctor  u where u.login=?1")
	public   Doctor findByLogin(String login);
		
		

}
