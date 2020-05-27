package com.example.MedicalQuotes.Models;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "doctors")

public class Doctor extends User  {


	

	@Column
	private String speciality;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Appointment> appointment;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Patient> patients;
	
	@Column(insertable = false, updatable = false)
	private Long id_role;
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role", referencedColumnName = "id")
	private Role role;
	
	
	public Doctor(String login, String password, String name, String lastname, String date_of_birth, String speciality, Long role)
			throws ParseException {
		super(login, password, name, lastname, date_of_birth);
		setSpeciality(speciality);
		setId_role(role);
		appointment = new HashSet<>();
		patients = new HashSet<>();
		
		
	}

	public Doctor() {
		appointment = new HashSet<>();
		patients = new HashSet<>();
		
		
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
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
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("%-16s %s\n"+"%-16s %s\n", "speciality:", this.speciality,"id_role:", this.id_role);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		return true;
	}

}
