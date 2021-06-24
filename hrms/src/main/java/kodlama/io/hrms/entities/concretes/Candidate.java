package kodlama.io.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name="id")
public class Candidate extends User {
	
	@Column(name ="id")
	private int id;
	@Column(name ="first_name")
	private String firstName;
	@Column(name ="last_name")
	private String lastName;
	@Column(name ="identity_number")
	private String identityNumber;
	@Column(name ="birth_year")
	private LocalDate birthYear;
	
	public Candidate() {
		
	}

	
	public Candidate(int id, String firstName, String lastName, String identityNumber, LocalDate birthYear) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
		this.birthYear = birthYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public LocalDate getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(LocalDate birthYear) {
		this.birthYear = birthYear;
	}

}
