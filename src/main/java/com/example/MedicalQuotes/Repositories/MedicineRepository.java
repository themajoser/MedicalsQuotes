package com.example.MedicalQuotes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MedicalQuotes.Models.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
