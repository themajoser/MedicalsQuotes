package com.example.MedicalQuotes.Dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model represents the appointment")

public class AppointmentDTO {
	
	@JsonProperty("id")
	private Long id;

	@ApiModelProperty(required = true)
	@JsonProperty("assement")
	@NotBlank(message = "assement must be required")
	private String assement;

	@ApiModelProperty(required = true)
	@JsonProperty("status")
	@NotBlank(message = "status must be required")
	private String status;

	@ApiModelProperty(required = false)
	@JsonProperty("id_doctor")
	private Long id_doctor;

	@ApiModelProperty(required = false)
	@JsonProperty("id_patient")
	private Long id_patient;

	@ApiModelProperty(required = true)
	@JsonProperty("date")
	@NotBlank(message = "date must be required")
	private Date date;

	
	
	
	@JsonProperty("medicines")
	
	private Set<MedicineDTO> medicines;

	@JsonProperty("doctor")
	@ApiModelProperty(required = true)
	private DoctorDTO doctor;

	@JsonProperty("patient")
	@ApiModelProperty(required = true)
	private PatientDTO patient;
	
	
	
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getAssement() {
		return assement;
	}

	public Set<MedicineDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<MedicineDTO> medicines) {
		this.medicines = medicines;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}

	public void setAssement(String assement) {
		this.assement = assement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(Long id_doctor) {
		this.id_doctor = id_doctor;
	}

	public Long getId_patient() {
		return id_patient;
	}

	public void setId_patient(Long id_patient) {
		this.id_patient = id_patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
