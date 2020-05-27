package com.example.MedicalQuotes.Dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class RoleDTO {
	@ApiModelProperty(required = false)
	@JsonProperty("id")
	private Long id;
	
	@ApiModelProperty(required = true)
	@JsonProperty("name")
	@NotBlank(message = "Name must be required")
	private String name;
	
	@JsonProperty("doctors")
	@JsonBackReference
	private Set<DoctorDTO> doctors;
	
	
	@JsonProperty("patients")
	@JsonBackReference(value="role")
	private Set<PatientDTO> patients;
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

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

	/**
	 * @return the doctors
	 */
	public Set<DoctorDTO> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors the doctors to set
	 */
	public void setDoctors(Set<DoctorDTO> doctors) {
		this.doctors = doctors;
	}

	/**
	 * @return the patients
	 */
	public Set<PatientDTO> getPatients() {
		return patients;
	}

	/**
	 * @param patients the patients to set
	 */
	public void setPatients(Set<PatientDTO> patients) {
		this.patients = patients;
	}



}
