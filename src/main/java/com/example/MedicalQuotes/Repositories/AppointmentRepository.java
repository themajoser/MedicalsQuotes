package com.example.MedicalQuotes.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MedicalQuotes.Models.Appointment;
import com.example.MedicalQuotes.Models.Doctor;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
 
	@Query("select u  from Appointment  u where u.id_doctor=?1")
	public   List<Appointment>  findByDoctor(Long id);
	
	@Query("select u  from Appointment  u where u.id_patient=?1")
	public   List<Appointment>  findByPatient(Long id);
		
}
