package com.example.MedicalQuotes.ServicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MedicalQuotes.Dto.MedicineDTO;
import com.example.MedicalQuotes.Models.Medicine;
import com.example.MedicalQuotes.Repositories.MedicineRepository;
import com.example.MedicalQuotes.Services.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public Collection<MedicineDTO> getMedicines() {

		try {
			ModelMapper modelMapper = new ModelMapper();
			List<Medicine> medicinesModelo = medicineRepository.findAll();
			Collection<MedicineDTO> medicinesDTOList = new ArrayList<>();
			medicinesModelo.forEach(medicine -> medicinesDTOList.add(modelMapper.map(medicine, MedicineDTO.class)));

			return medicinesDTOList;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public MedicineDTO getMedicineById(Long id) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Medicine medicineModel = medicineRepository.findById(id).get();
			MedicineDTO MedicineDTO = modelMapper.map(medicineModel, MedicineDTO.class);

			return MedicineDTO;

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public MedicineDTO createMedicine(MedicineDTO medicineDTO) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			Medicine medicine = medicineRepository.save(modelMapper.map(medicineDTO, Medicine.class));
			return modelMapper.map(medicine, MedicineDTO.class);

		} catch (Exception e) {
			return null;
		}
	}

	public Medicine checkAttributes(MedicineDTO medicine, Medicine medicineBBDD) {
		if (medicine.getName() != null) {
			medicineBBDD.setName(medicine.getName());
		}
		if (medicine.getBrand() != null) {
			medicineBBDD.setBrand(medicine.getBrand());
		}

		return medicineBBDD;

	}

	@Override
	public MedicineDTO updateMedicine(MedicineDTO medicine, Long id) {

		ModelMapper modelMapper = new ModelMapper();
		Medicine medicineBBDD = new Medicine();
		medicineBBDD = medicineRepository.findById(id).get();
		if (medicineBBDD == null) {
			return null;
		}
		Medicine Medicine = medicineRepository.save(checkAttributes(medicine, medicineBBDD));

		return modelMapper.map(Medicine, MedicineDTO.class);

	}

	public void removeMedicine(Long id) {

		Medicine medicine = new Medicine();

		medicine = medicineRepository.findById(id).get();
		if (medicine != null) {
			medicine.setDeleted_at(new Date());
			medicine.setDeleted(true);
			medicineRepository.save(medicine);
		}

	}

}
