package com.example.MedicalQuotes.Services;

import java.util.Collection;

import com.example.MedicalQuotes.Dto.MedicineDTO;

public interface MedicineService {

	public abstract Collection<MedicineDTO> getMedicines();

	public abstract MedicineDTO getMedicineById(Long id);

	public abstract MedicineDTO createMedicine(MedicineDTO medicine);

	public abstract void removeMedicine(Long id);

	public abstract MedicineDTO updateMedicine(MedicineDTO medicine, Long id);

}
