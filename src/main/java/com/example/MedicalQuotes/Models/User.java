package com.example.MedicalQuotes.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Where(clause = "deleted = 0")
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	private String login;
	@Column
	private String password;
	@Column
	private String name;
	@Column
	private String lastname;
	@Column
	// @JsonFormat(pattern="yyyy-MM-dd");
	private Date date_of_birth;
	
	
	// @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss");
	private Date created_at;
	@Column
	private Boolean deleted;
	@Column
	private Date deleted_at;
	
	




	public User(String login, String password, String name, String lastname, String date_of_birth)
			throws ParseException {
		setLogin(login);
		setPassword(password);
		setName(name);
		setLastname(lastname);
		setDate_of_birth(new SimpleDateFormat("yyyy-MM-dd").parse(date_of_birth));
		setCreated_at(new Date());
		setDeleted(false);
		setDeleted(new Boolean(false));
		
		
	}

	public User() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.password = BCrypt.hashpw(password, BCrypt.gensalt(10));
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

	public void setDate_of_birth(Date date) {
		this.date_of_birth = date;
	}






	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
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
		return String.format(
				"%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n" + "%-16s %s\n"
						+ "%-16s %s\n",
				"id:", this.id, "login:", this.login, "password:", this.password, "name:", this.name, "lastname:",
				this.lastname, "date_of_birth:", this.date_of_birth, "created_at:", this.created_at, "deleted:",
				this.deleted);
	}

}
