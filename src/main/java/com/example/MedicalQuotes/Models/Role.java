package com.example.MedicalQuotes.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "roles")
public class Role {
	
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column
	private String name;
	

	
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Patient> patients;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Doctor> doctors;
	
	public Role(String name) {
		this.setName(name);
		patients = new HashSet<>();
		doctors = new HashSet<>();
		
	}
	public Role() {
		patients = new HashSet<>();
	doctors = new HashSet<>();
		
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
	 * @return the patients
	 */
	public Set<Patient> getPatients() {
		return patients;
	}
	/**
	 * @param patients the patients to set
	 */
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
	/**
	 * @return the doctors
	 */
	public Set<Doctor> getDoctors() {
		return doctors;
	}
	/**
	 * @param doctors the doctors to set
	 */
	public void setDoctors(Set<Doctor> doctors) {
		this.doctors = doctors;
	}
	@Override
	public String toString() {
		return super.toString() + String.format(
				"%-16s %s\n" + "%-16s %s\n", "id:", this.id, "name:",
				this.name);
	}

}
