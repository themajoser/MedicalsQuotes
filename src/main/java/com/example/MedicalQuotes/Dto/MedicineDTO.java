package com.example.MedicalQuotes.Dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class MedicineDTO {
	
	@JsonProperty("id")
	private Long id;

	@ApiModelProperty(required = true)
	@JsonProperty("name")
	@NotBlank(message = "name must be required")
	private String name;

	@ApiModelProperty(required = true)
	@JsonProperty("brand")
	@NotBlank(message = "brand must be required")
	private String brand;

	@JsonProperty("appointments")
	@JsonBackReference(value = "medicines")
	private Set<AppointmentDTO> appointments;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Set<AppointmentDTO> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentDTO> appointments) {
		this.appointments = appointments;
	}

}
