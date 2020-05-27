package com.example.MedicalQuotes.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MedicalQuotes.Models.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {



    @Query("FROM Patient WHERE login = ?1")
    public Patient getPatientByLogin(String login);
    

    
    @Query("FROM Patient WHERE id_doctor = ?1")
    public List<Patient> getPatientsByDoctor(Long id);


}
