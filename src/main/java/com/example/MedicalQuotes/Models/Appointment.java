package com.example.MedicalQuotes.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonCreator;

@Entity
@Table(name = "appointments")
@Where(clause = "deleted = 0")
public class Appointment  {


	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String assement;
	@Column
	private String status;
	@Column(insertable=false , updatable=false)
	private Long id_doctor;
	@Column(insertable=false , updatable=false)
	private Long id_patient;
	@Column
	private Date date;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "appointments_medicines", joinColumns = @JoinColumn(name = "id_quotes", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_medicine", referencedColumnName = "id"))
	private Set<Medicine> medicines;

	@Column
	private Boolean deleted;
	@Column
	private Date deleted_at;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_doctor", referencedColumnName = "id")
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_patient", referencedColumnName = "id")
	private Patient patient;

	@JsonCreator
	public Appointment(String assement, String status, Long id_doctor, Long id_patient, String date, String hour)
			throws ParseException {

		setAssement(assement);
		setStatus(status);
		setId_doctor(id_doctor);
		setId_patient(id_patient);
		setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").parse(date));
		setDeleted(new Boolean(false));
		medicines = new HashSet<>();
	}

	public Appointment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getAssement() {
		return assement;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(Set<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(
				"%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n",
				"id:", this.id, "assement:", this.assement, "status:", this.status, "id_doctor:", this.doctor, "date:",
				this.date, "created_at:", this.deleted_at, "deleted:", this.deleted);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (assement == null) {
			if (other.assement != null)
				return false;
		} else if (!assement.equals(other.assement))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (deleted_at == null) {
			if (other.deleted_at != null)
				return false;
		} else if (!deleted_at.equals(other.deleted_at))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_doctor == null) {
			if (other.id_doctor != null)
				return false;
		} else if (!id_doctor.equals(other.id_doctor))
			return false;
		if (id_patient == null) {
			if (other.id_patient != null)
				return false;
		} else if (!id_patient.equals(other.id_patient))
			return false;
		if (medicines == null) {
			if (other.medicines != null)
				return false;
		} else if (!medicines.equals(other.medicines))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
