package com.example.MedicalQuotes.Dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.example.MedicalQuotes.Models.Doctor;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class PatientDTO {
	
	
	



	@ApiModelProperty(required = false)
	@JsonProperty("id")
	private Long id;

	@ApiModelProperty(required = true)
	@JsonProperty("SSCode")
	@NotBlank(message = "SSCode must be required")
	private String SSCode;

	@JsonProperty("login")
	@NotBlank(message = "login must be required")
	private String login;

	@ApiModelProperty(required = true)
	@JsonProperty("password")
	@NotBlank(message = "password must be required")
	private String password;

	@ApiModelProperty(required = true)
	@JsonProperty("name")
	@NotBlank(message = "Name must be required")
	private String name;

	@ApiModelProperty(required = true)
	@JsonProperty("lastname")
	@NotBlank(message = "lastname must be required")
	private String lastname;

	@ApiModelProperty(required = false)
	@JsonProperty("date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "Date of birth must be required")
	private Date date_of_birth;

	@ApiModelProperty(required = true)
	@JsonProperty("allergies")
	@NotBlank(message = "allergies must be required")
	private String allergies;

	@JsonProperty("cancer")
	private String cancer;

	@JsonProperty("diseases")
	private String diseases;

	@ApiModelProperty(required = false)
	@JsonProperty("id_doctor")
	//@NotBlank(message = "id_doctor must be required")
	private Long id_doctor;
	
	@ApiModelProperty(required = false)
	@JsonProperty("id_role")
	//@NotBlank(message = "id_doctor must be required")
	private Long id_role;
	


	@JsonProperty("role")
	
	private RoleDTO role;
	
	

	@JsonProperty("doctor")
	
	private DoctorDTO doctor;

	@JsonProperty("appointment")
	@JsonBackReference(value = "patient")
	private Set<AppointmentDTO> appointment;
	
	
	
	

	@JsonProperty("deleted")
	private Boolean deleted;

	@JsonProperty("deleted_at")
	private Date deleted_at;

	@JsonProperty("created_at")
	private Date created_at;

	public Long getId() {
		return id;
	}

	public String getSSCode() {
		return SSCode;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String cancer) {
		this.cancer = cancer;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}



	public Long getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(Long id_doctor) {
		this.id_doctor = id_doctor;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSSCode(String sSCode) {
		SSCode = sSCode;
	}

	public DoctorDTO getDoctor() {
		return doctor;
	}
	
	public void setDoctor(DoctorDTO doctor) {
		
		this.doctor = doctor;
	}

	public Set<AppointmentDTO> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<AppointmentDTO> appointment) {
		this.appointment = appointment;
	}
	/**
	 * @return the id_role
	 */
	public Long getId_role() {
		return id_role;
	}

	/**
	 * @param id_role the id_role to set
	 */
	public void setId_role(Long id_role) {
		this.id_role = id_role;
	}

	/**
	 * @return the role
	 */
	public RoleDTO getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}
