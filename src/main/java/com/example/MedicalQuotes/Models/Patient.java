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

import org.hibernate.annotations.Where;

@Entity

@Table(name = "patients")

public class Patient extends User  {


	
	@Column
	private String SSCode;
	@Column
	private String allergies;
	@Column
	private String cancer;
	@Column
	private String diseases;
	@Column(insertable = false, updatable = false)
	private Long id_doctor;
	
	@Column(insertable = false, updatable = false)
	private Long id_role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_doctor", referencedColumnName = "id")
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_role", referencedColumnName = "id")
	private Role role;
	
	


	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Appointment> appointment;

	public Patient(String login, String password, String name, String lastname, String date_of_birth, String allergies,
			String cancer, String diseases, Long id_doctor, Long id_role) throws ParseException {
		super(login, password, name, lastname, date_of_birth);
		setAllergies(allergies);
		setCancer(cancer);
		setDiseases(diseases);
		setId_doctor(id_doctor);
		setId_role(id_role);
		appointment = new HashSet<>();
		
	}

	public Patient() {
		appointment = new HashSet<>();
	}

	public Set<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(Set<Appointment> appointment) {
		this.appointment = appointment;
	}

	public Long getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(Long id_doctor) {
		this.id_doctor = id_doctor;
	}

	public String getSSCode() {
		return SSCode;
	}

	public void setSSCode(String sSCode) {
		this.SSCode = sSCode;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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
		return super.toString()
				+ String.format("%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n", "SSCode:",
						this.SSCode, "allergies:", this.allergies, "cancer:", this.cancer, "diseases:", this.diseases,"id_role:", this.id_role);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (SSCode == null) {
			if (other.SSCode != null)
				return false;
		} else if (!SSCode.equals(other.SSCode))
			return false;
		if (allergies == null) {
			if (other.allergies != null)
				return false;
		} else if (!allergies.equals(other.allergies))
			return false;
		if (appointment == null) {
			if (other.appointment != null)
				return false;
		} else if (!appointment.equals(other.appointment))
			return false;
		if (cancer == null) {
			if (other.cancer != null)
				return false;
		} else if (!cancer.equals(other.cancer))
			return false;
		if (diseases == null) {
			if (other.diseases != null)
				return false;
		} else if (!diseases.equals(other.diseases))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (id_doctor == null) {
			if (other.id_doctor != null)
				return false;
		} else if (!id_doctor.equals(other.id_doctor))
			return false;
		return true;
	}

}
