package com.example.MedicalQuotes.Dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This model represents the doctor")
public class DoctorDTO {


	@JsonProperty("id")
	private Long id;

	@ApiModelProperty(required = true)
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

	@ApiModelProperty(required = true)
	@JsonProperty("date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotBlank(message = "Date of birth must be required")
	private Date date_of_birth;

	@ApiModelProperty(required = true)
	@JsonProperty("speciality")
	@NotBlank(message = "speciality must be required")
	private String speciality;
	
	@ApiModelProperty(required = false)
	@JsonProperty("id_role")
	//@NotBlank(message = "id_doctor must be required")
	private Long id_role;
	
	
	
	
	

	@JsonProperty("appointments")
	@JsonBackReference(value="doctor")
	private Set<AppointmentDTO> appointments;

	@JsonProperty("patients")
	@JsonBackReference( value = "doctor2")
	private Set<PatientDTO> patients;
	
	@JsonProperty("role")
	private RoleDTO role;
	
	
	
	
	
	

	@JsonProperty("deleted")
	@ApiModelProperty(required = true)
	private Boolean deleted;

	@JsonProperty("deleted_at")
	private Date deleted_at;

	@JsonProperty("created_at")
	@ApiModelProperty(required = true)
	private Date created_at;



	public Long getId() {
		return id;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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

	public Set<AppointmentDTO> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<AppointmentDTO> appointment) {
		this.appointments = appointment;
	}

	public Set<PatientDTO> getPatients() {
		return patients;
	}

	public void setPatients(Set<PatientDTO> patients) {
		this.patients = patients;
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
